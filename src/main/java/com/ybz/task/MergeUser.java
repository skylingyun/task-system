package com.ybz.task;

import com.ybz.dao.OcsUserMapper;
import com.ybz.dao.UserMapper;
import com.ybz.entity.*;
import com.ybz.service.IDataSysTenantService;
import com.ybz.utils.DateUtils;
import com.ybz.utils.TenantUtils;
import com.yonyou.iuap.context.InvocationInfoProxy;
import com.yonyou.iuap.dynamicds.ds.DynamicDataSource;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 定时任务(整合用户）
 *
 * @author zhangybt
 * @email zhangybt@yonyou.com
 * @date 2018年1月17日 下午1:34:24
 */


@Component("mergeUser")
public class MergeUser {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IDataSysTenantService sysTenantService;

    //-------------------用户(begin)--------------------
    //用户(旧)
    @Autowired
    private UserMapper userMapperDao;
    //用户(新)
    @Autowired
    private OcsUserMapper ocsUserMapperDao;
    //--------------------用户(end)---------------------

    /**
     * 整合用户数据
     */
    public void mergeUser() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        logger.info("我是带参数的mergeUser方法，正在被执行");
        try {
            Thread.sleep(1000L);
            List<OcsUser> ocsUserList = new ArrayList<>();
            InvocationInfoProxy.setTenantid(TenantUtils.DEFAULT_TENANT);
            List<SysTenant> sysTenantList = sysTenantService.selectTenantList();
            for (SysTenant sysTenant : sysTenantList) {
                String tenantId = sysTenant.getTenantId();
                //目标中不存在数据
                InvocationInfoProxy.setTenantid(TenantUtils.DEFAULT_TENANT);
                OcsUserExample ocsUserExample = new OcsUserExample();
                OcsUserExample.Criteria ocsUserCriteria = ocsUserExample.createCriteria();
                ocsUserCriteria.andTenantIdEqualTo(tenantId);
                if (ocsUserMapperDao.countByExample(ocsUserExample) == 0) {
                    InvocationInfoProxy.setTenantid(tenantId);
                    ocsUserList.clear();
                    UserExample example = new UserExample();
                    UserExample.Criteria criteria = example.createCriteria();
                    criteria.andDrEqualTo(false);
                    List<User> userList = userMapperDao.selectByExample(example);
                    if (CollectionUtils.isNotEmpty(userList)) {
                        for (User user : userList) {
                            OcsUser ocsUser = getOcsUser(tenantId, user);
                            ocsUserList.add(ocsUser);
                        }
                    }
                } else {
                    //查询时间戳
                    InvocationInfoProxy.setTenantid(TenantUtils.DEFAULT_TENANT);
                    String startDate = ocsUserMapperDao.selectMaxDate(new OcsUserExample());
                    InvocationInfoProxy.setTenantid(tenantId);
                    UserExample userExample = new UserExample();
                    UserExample.Criteria criteria = userExample.createCriteria();
                    criteria.andDrEqualTo(false);
                    criteria.andRegisterdateBetween(DateUtils.format(startDate),DateUtils.format(sdf.format(new Date())));
                    List<User> userList = userMapperDao.selectByExample(userExample);
                    if (CollectionUtils.isNotEmpty(userList)) {
                        InvocationInfoProxy.setTenantid(TenantUtils.DEFAULT_TENANT);
                        ocsUserList.clear();
                        for (User user : userList) {
                            //目标中不存在
                            OcsUserExample example = new OcsUserExample();
                            OcsUserExample.Criteria userCriteria = ocsUserExample.createCriteria();
                            userCriteria.andUseridEqualTo(user.getUserid());
                            if(ocsUserMapperDao.countByExample(example) == 0){
                                OcsUser ocsUser = getOcsUser(tenantId, user);
                                ocsUserList.add(ocsUser);
                            } else {
                                //目标存在
                                OcsUser ocsUser = getOcsUser(tenantId, user);
                                ocsUserMapperDao.updateByExample(ocsUser,example);
                            }
                        }
                    }else {
                        continue;
                    }
                }

                InvocationInfoProxy.setTenantid(TenantUtils.DEFAULT_TENANT);
                if (CollectionUtils.isNotEmpty(ocsUserList)) {
                    int batchCount = 1000;// 每批commit的个数
                    int batchLastIndex = batchCount;// 每批最后一个的下标
                    for (int index = 0; index < ocsUserList.size(); ) {
                        if (batchLastIndex >= ocsUserList.size()) {
                            batchLastIndex = ocsUserList.size();
                            ocsUserMapperDao.insertByBatch(ocsUserList.subList(index, batchLastIndex));
                            logger.debug("用户融合成功");
                            break;// 数据插入完毕，退出循环
                        } else {
                            ocsUserMapperDao.insertByBatch(ocsUserList.subList(index, batchLastIndex));
                            index = batchLastIndex;// 设置下一批下标
                            batchLastIndex = index + (batchCount - 1);
                        }
                    }
                } else {
                    logger.info("用户数据无更新");
                    continue;
                }
            }
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
        }

    }

    private OcsUser getOcsUser(String tenantId, User user) {
        OcsUser ocsUser = new OcsUser();
        BeanUtils.copyProperties(user, ocsUser);
        ocsUser.setTenantId(tenantId);
        ocsUser.setCreateTime(new Date());
        return ocsUser;
    }
}
