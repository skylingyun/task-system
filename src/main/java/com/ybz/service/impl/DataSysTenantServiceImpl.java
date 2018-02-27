package com.ybz.service.impl;

import com.ybz.dao.OcsUserMapper;
import com.ybz.dao.SysTenantMapper;
import com.ybz.entity.OcsUserExample;
import com.ybz.entity.SysTenant;
import com.ybz.entity.SysTenantExample;
import com.ybz.entity.TenantInfo;
import com.ybz.service.IDataSysTenantService;
import com.ybz.utils.DateUtils;
import com.ybz.utils.TenantUtils;
import com.yonyou.iuap.context.InvocationInfoProxy;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/1/22.
 */
@Service
public class DataSysTenantServiceImpl implements IDataSysTenantService {

    @Autowired
    private SysTenantMapper sysTenantMapperDao;

    @Autowired
    private OcsUserMapper ocsUserMapper;

    @Override
    public List<SysTenant> selectTenantListByCondition(Date openBeginDate, Date openEndDate, int currPage, int pageSize, String sorter,String tenantName) {

        SysTenantExample example = new SysTenantExample();
        example.setStartRow((currPage - 1) * pageSize);
        example.setPageSize(pageSize);
//        if(StringUtils.isNotEmpty(sorter)){
//            String[] sorterSplit = sorter.split(":");
//            for (int i = 0, len = sorterSplit.length; i < len; i++) {
//                String orderDescOrAsc = sorterSplit[1];
//                if("descend".equalsIgnoreCase(orderDescOrAsc)){
//                    example.setOrderByClause(StringUtils.substringBeforeLast(sorter,":") + " " + "desc");
//                }else {
//                    example.setOrderByClause(StringUtils.substringBeforeLast(sorter,":") + " " + "asc");
//                }
//            }
//        }
        SysTenantExample.Criteria criteria = example.createCriteria();
        if(StringUtils.isNotEmpty(tenantName)){
            criteria.andTenantNameLike(tenantName);
        }
        if (openBeginDate != null && openEndDate != null) {
            criteria.andCreateTimeBetween(openBeginDate, openEndDate);
        }
        return sysTenantMapperDao.selectByExample(example);
    }

    public String formatStr(String str){
        StringBuffer  sbf=new StringBuffer("");
        for(int i=0;i<str.length();i++){
            char tempChr=str.charAt(i);
            if(tempChr>='A'&&tempChr<='Z'){
                sbf.append("_");//如果是大写字母，则在字符前面插入一个空格
            }
            sbf.append(tempChr);
        }
        return sbf.toString();
    }

    @Override
    public int selectTenantCount(Date openBeginDate, Date openEndDate,String tenantName) {
        InvocationInfoProxy.setTenantid(TenantUtils.DEFAULT_TENANT);
        SysTenantExample example = new SysTenantExample();
        SysTenantExample.Criteria criteria = example.createCriteria();
        if(StringUtils.isNotEmpty(tenantName)){
            criteria.andTenantNameLike(tenantName);
        }
        if (openBeginDate != null && openEndDate != null) {
            criteria.andCreateTimeBetween(openBeginDate, openEndDate);
        }
        return sysTenantMapperDao.countByExample(example);
    }

    @Override
    public List<SysTenant> selectTenantList() {
        InvocationInfoProxy.setTenantid(TenantUtils.DEFAULT_TENANT);
        return sysTenantMapperDao.selectByExample(new SysTenantExample());
    }


    /**
     * 根据租户条件查询
     *
     * @param tenantIdList
     * @return
     */
    @Override
    public List<SysTenant> selectTenantListByTenantIds(List<String> tenantIdList) {
        SysTenantExample example = new SysTenantExample();
        SysTenantExample.Criteria criteria = example.createCriteria();
        if(CollectionUtils.isNotEmpty(tenantIdList)){
            criteria.andTenantIdIn(tenantIdList);
        }
        return sysTenantMapperDao.selectByExample(example);
    }

    /**
     * 根据租户名称模糊查询
     */
    @Override
    public List<SysTenant> selectTenantListByTenantName(String tenantName) {
        SysTenantExample example = new SysTenantExample();
        SysTenantExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotEmpty(tenantName)) {
            criteria.andTenantNameLike(tenantName);
        }
        return sysTenantMapperDao.selectByExample(example);
    }

    /**
     * 分页查询本地租户信息
     * @param offset
     * @param limit
     * @return
     */
    @Override
    public List<SysTenant> selectTenantListByPage(int offset, int limit) {
        SysTenantExample example = new SysTenantExample();
        example.setStartRow((offset - 1) * limit);
        example.setPageSize(limit);
        List<SysTenant> sysTenantList = sysTenantMapperDao.selectByExample(example);
        for (SysTenant sysTenant : sysTenantList) {
            OcsUserExample userExample = new OcsUserExample();
            OcsUserExample.Criteria userCriteria = userExample.createCriteria();
            userCriteria.andTenantIdEqualTo(sysTenant.getTenantId());
            int userCount = ocsUserMapper.countByExample(userExample);
            sysTenant.setUserNum(userCount);
        }
        return sysTenantList;
    }

    @Override
    public List<SysTenant> selectTenantByTenantId(String tenantId) {
        SysTenantExample example = new SysTenantExample();
        SysTenantExample.Criteria criteria = example.createCriteria();
        criteria.andTenantIdEqualTo(tenantId);
        List<SysTenant> sysTenant = sysTenantMapperDao.selectByExample(example);
        return sysTenant;
    }

    public int addTenant(SysTenant sysTenant){
        int result = sysTenantMapperDao.insert(sysTenant);
        if(result == 1){
           return 1;
        }else {
            return 0;
        }
    }
}
