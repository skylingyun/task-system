package com.ybz.task;

import com.ybz.dao.*;
import com.ybz.entity.*;
import com.ybz.service.IDataSysTenantService;
import com.ybz.utils.DateUtils;
import com.ybz.utils.TenantUtils;
import com.yonyou.iuap.context.InvocationInfoProxy;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 定时任务(整合记事）
 *
 * @author zhangybt
 * @email zhangybt@yonyou.com
 * @date 2018年1月17日 下午1:34:24
 */
@Component("mergeNotes")
public class MergeNotes {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IDataSysTenantService sysTenantService;

    //-------------------记事(begin)--------------------
    //餐饮(旧)
    @Autowired
    private NodeEatingMapper nodeEatingMapperDao;
    //餐饮(新)
    @Autowired
    private OcsEatingMapper ocsEatingMapperDao;
    //出行(旧)
    @Autowired
    private NodeTravelMapper nodeTravelMapperDao;
    //出行(新)
    @Autowired
    private OcsTravelMapper ocsTravelMapperDao;
    //通讯(旧)
    @Autowired
    private NodeCommunicateMapper nodeCommunicateMapperDao;
    //通讯(新)
    @Autowired
    private OcsCommunicateMapper ocsCommunicateMapperDao;
    //住宿(旧)
    @Autowired
    private NodeHotelMapper nodeHotelMapperDao;
    //住宿(新)
    @Autowired
    private OcsHotelMapper ocsHotelMapperDao;
    //其它(旧)
    @Autowired
    private NodeOtherMapper nodeOtherMapperDao;
    //其它(新)
    @Autowired
    private OcsOtherMapper ocsOtherMapperDao;
    //-------------------记事(end)----------------------

    /**
     * 整合餐饮记事
     */
    public void mergeNotes() {
        logger.info("我是带参数的mergeNotes方法，正在被执行");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Thread.sleep(1000L);
            InvocationInfoProxy.setTenantid(TenantUtils.DEFAULT_TENANT);
            List<SysTenant> sysTenantList = sysTenantService.selectTenantList();
            for (SysTenant sysTenant : sysTenantList) {
                String tenantId = sysTenant.getTenantId();
                //餐饮记事
                //目标中不存在数据
                InvocationInfoProxy.setTenantid(TenantUtils.DEFAULT_TENANT);
                List<OcsEating> ocsEatingList = new ArrayList<>();
                OcsEatingExample ocsEatingExample = new OcsEatingExample();
                OcsEatingExample.Criteria ocsCriteria = ocsEatingExample.createCriteria();
                ocsCriteria.andTenantIdEqualTo(tenantId);
                if (ocsEatingMapperDao.countByExample(ocsEatingExample) == 0) {
                    InvocationInfoProxy.setTenantid(tenantId);
                    ocsEatingList.clear();
                    List<NodeEating> nodeEatingList = nodeEatingMapperDao.selectByExample(new NodeEatingExample());
                    if (CollectionUtils.isNotEmpty(nodeEatingList)) {
                        ocsEatingList.clear();
                        for (NodeEating nodeEating : nodeEatingList) {
                            OcsEating ocsEating = getOcsEating(tenantId, nodeEating);
                            ocsEatingList.add(ocsEating);
                        }
                    }
                } else {
                    InvocationInfoProxy.setTenantid(TenantUtils.DEFAULT_TENANT);
                    String startDate = ocsEatingMapperDao.selectMaxDate();
                    InvocationInfoProxy.setTenantid(tenantId);
                    ocsEatingList.clear();
                    NodeEatingExample eatingExample = new NodeEatingExample();
                    NodeEatingExample.Criteria eatingCriteria = eatingExample.createCriteria();
                    eatingCriteria.andEatingDateBetween(DateUtils.format(startDate),DateUtils.format(sdf.format(new Date())));
                    List<NodeEating> nodeEatingList = nodeEatingMapperDao.selectByExample(eatingExample);
                    if (CollectionUtils.isNotEmpty(nodeEatingList)) {
                        InvocationInfoProxy.setTenantid(TenantUtils.DEFAULT_TENANT);
                        for (NodeEating nodeEating : nodeEatingList) {
                            OcsEatingExample example = new OcsEatingExample();
                            OcsEatingExample.Criteria criteria = example.createCriteria();
                            criteria.andPkEqualTo(nodeEating.getPk());
                            if (ocsEatingMapperDao.countByExample(example) == 0) {
                                OcsEating ocsEating = getOcsEating(tenantId, nodeEating);
                                ocsEatingList.add(ocsEating);
                            } else {
                                OcsEating ocsEating = getOcsEating(tenantId, nodeEating);
                                OcsEatingExample eatExample = new OcsEatingExample();
                                OcsEatingExample.Criteria eatCriteria = eatExample.createCriteria();
                                eatCriteria.andPkEqualTo(nodeEating.getPk());
                                ocsEatingMapperDao.updateByExample(ocsEating,eatExample);
                            }

                        }
                    }
                }
                InvocationInfoProxy.setTenantid(TenantUtils.DEFAULT_TENANT);
                if (CollectionUtils.isNotEmpty(ocsEatingList)) {
                    int batchCount = 1000;// 每批commit的个数
                    int batchLastIndex = batchCount;// 每批最后一个的下标
                    for (int index = 0; index < ocsEatingList.size(); ) {
                        if (batchLastIndex >= ocsEatingList.size()) {
                            batchLastIndex = ocsEatingList.size();
                            ocsEatingMapperDao.insertByBatch(ocsEatingList.subList(index, batchLastIndex));
                            logger.debug("餐饮记事添加成功");
                            break;// 数据插入完毕，退出循环
                        } else {
                            ocsEatingMapperDao.insertByBatch(ocsEatingList.subList(index, batchLastIndex));
                            index = batchLastIndex;// 设置下一批下标
                            batchLastIndex = index + (batchCount - 1);
                        }
                    }
                }

                Thread.sleep(1000L);
                //出行记事
                //目标中不存在数据
                List<OcsTravel> ocsTravelList = new ArrayList<>();
                OcsTravelExample ocsTravelExample  = new OcsTravelExample();
                OcsTravelExample.Criteria ocsTravelCriteria = ocsTravelExample.createCriteria();
                ocsTravelCriteria.andTenantIdEqualTo(tenantId);
                if (ocsTravelMapperDao.countByExample(ocsTravelExample) == 0) {
                    InvocationInfoProxy.setTenantid(tenantId);
                    ocsTravelList.clear();
                    List<NodeTravel> nodeTravelList = nodeTravelMapperDao.selectByExample(new NodeTravelExample());
                    if (CollectionUtils.isNotEmpty(nodeTravelList)) {
                        ocsTravelList.clear();
                        for (NodeTravel nodeTravel : nodeTravelList) {
                            OcsTravel ocsTravel = getOcsTravel(tenantId, nodeTravel);
                            ocsTravelList.add(ocsTravel);
                        }
                    }
                } else {
                    InvocationInfoProxy.setTenantid(TenantUtils.DEFAULT_TENANT);
                    String startDate = ocsTravelMapperDao.selectMaxDate();
                    InvocationInfoProxy.setTenantid(tenantId);
                    ocsTravelList.clear();
                    NodeTravelExample nodeTravelExample = new NodeTravelExample();
                    NodeTravelExample.Criteria nodeTravelCriteria = nodeTravelExample.createCriteria();
                    nodeTravelCriteria.andTsBetween(DateUtils.format(startDate),DateUtils.format(sdf.format(new Date())));
                    List<NodeTravel> nodeTravelList = nodeTravelMapperDao.selectByExample(nodeTravelExample);
                    if (CollectionUtils.isNotEmpty(nodeTravelList)) {
                        InvocationInfoProxy.setTenantid(TenantUtils.DEFAULT_TENANT);
                        ocsTravelList.clear();
                        for (NodeTravel nodeTravel : nodeTravelList) {
                            OcsTravelExample ocsTravelExample1 = new OcsTravelExample();
                            OcsTravelExample.Criteria ocsTravelExample1Criteria = ocsTravelExample1.createCriteria();
                            ocsTravelExample1Criteria.andPkEqualTo(nodeTravel.getPk());
                            if (ocsTravelMapperDao.countByExample(ocsTravelExample1) == 0) {
                                OcsTravel ocsTravel = getOcsTravel(tenantId, nodeTravel);
                                ocsTravelList.add(ocsTravel);
                            } else {
                                OcsTravel ocsTravel = getOcsTravel(tenantId, nodeTravel);
                                ocsTravelMapperDao.updateByExample(ocsTravel, ocsTravelExample1);
                            }
                        }
                    }
                }
                InvocationInfoProxy.setTenantid(TenantUtils.DEFAULT_TENANT);
                if (CollectionUtils.isNotEmpty(ocsTravelList)) {
                    int batchCount = 1000;// 每批commit的个数
                    int batchLastIndex = batchCount;// 每批最后一个的下标
                    for (int index = 0; index < ocsTravelList.size(); ) {
                        if (batchLastIndex >= ocsTravelList.size()) {
                            batchLastIndex = ocsTravelList.size();
                            ocsTravelMapperDao.insertByBatch(ocsTravelList.subList(index, batchLastIndex));
                            logger.debug("出行记事添加成功");
                            break;// 数据插入完毕，退出循环
                        } else {
                            ocsTravelMapperDao.insertByBatch(ocsTravelList.subList(index, batchLastIndex));
                            index = batchLastIndex;// 设置下一批下标
                            batchLastIndex = index + (batchCount - 1);
                        }
                    }
                } else {
                    logger.debug("出行记事无数据更新");
                }
                Thread.sleep(1000L);
                //通讯记事
                //目标中不存在数据
                List<OcsCommunicate> ocsCommunicateList = new ArrayList<>();
                OcsCommunicateExample ocsCommunicateExample = new OcsCommunicateExample();
                OcsCommunicateExample.Criteria ocsCommunicateExampleCriteria = ocsCommunicateExample.createCriteria();
                ocsCommunicateExampleCriteria.andTenantIdEqualTo(tenantId);
                if (ocsCommunicateMapperDao.countByExample(ocsCommunicateExample) == 0) {
                    InvocationInfoProxy.setTenantid(tenantId);
                    ocsCommunicateList.clear();
                    List<NodeCommunicate> nodeCommunicateList = nodeCommunicateMapperDao.selectByExample(new NodeCommunicateExample());
                    if (CollectionUtils.isNotEmpty(nodeCommunicateList)) {
                        ocsCommunicateList.clear();
                        for (NodeCommunicate nodeCommunicate : nodeCommunicateList) {
                            OcsCommunicate ocsCommunicate = getOcsCommunicate(tenantId, nodeCommunicate);
                            ocsCommunicateList.add(ocsCommunicate);
                        }
                    }
                } else {
                    //目标中存在数据
                    InvocationInfoProxy.setTenantid(TenantUtils.DEFAULT_TENANT);
                    String startDate = ocsCommunicateMapperDao.selectMaxDate();
                    InvocationInfoProxy.setTenantid(tenantId);
                    NodeCommunicateExample nodeCommunicateExample = new NodeCommunicateExample();
                    NodeCommunicateExample.Criteria nodeCommunicateExampleCriteria = nodeCommunicateExample.createCriteria();
                    nodeCommunicateExampleCriteria.andTsBetween(DateUtils.format(startDate),DateUtils.format(sdf.format(new Date())));
                    List<NodeCommunicate> nodeCommunicateList = nodeCommunicateMapperDao.selectByExample(nodeCommunicateExample);
                    if (CollectionUtils.isNotEmpty(nodeCommunicateList)) {
                        InvocationInfoProxy.setTenantid(TenantUtils.DEFAULT_TENANT);
                        ocsCommunicateList.clear();
                        for (NodeCommunicate nodeCommunicate : nodeCommunicateList) {
                            OcsCommunicateExample example = new OcsCommunicateExample();
                            OcsCommunicateExample.Criteria criteria= example.createCriteria();
                            criteria.andPkEqualTo(nodeCommunicate.getPk());
                            if (ocsCommunicateMapperDao.countByExample(example) == 0) {
                                OcsCommunicate ocsCommunicate = getOcsCommunicate(tenantId, nodeCommunicate);
                                ocsCommunicateList.add(ocsCommunicate);
                            } else {
                                OcsCommunicate ocsCommunicate = getOcsCommunicate(tenantId, nodeCommunicate);
                                ocsCommunicateMapperDao.updateByExample(ocsCommunicate,example);
                            }

                        }
                    }
                }
                InvocationInfoProxy.setTenantid(TenantUtils.DEFAULT_TENANT);
                if (CollectionUtils.isNotEmpty(ocsCommunicateList)) {
                    int batchCount = 1000;// 每批commit的个数
                    int batchLastIndex = batchCount;// 每批最后一个的下标
                    for (int index = 0; index < ocsCommunicateList.size(); ) {
                        if (batchLastIndex >= ocsCommunicateList.size()) {
                            batchLastIndex = ocsCommunicateList.size();
                            ocsCommunicateMapperDao.insertByBatch(ocsCommunicateList.subList(index, batchLastIndex));
                            logger.debug("通讯记事添加成功");
                            break;// 数据插入完毕，退出循环
                        } else {
                            ocsCommunicateMapperDao.insertByBatch(ocsCommunicateList.subList(index, batchLastIndex));
                            index = batchLastIndex;// 设置下一批下标
                            batchLastIndex = index + (batchCount - 1);
                        }
                    }
                } else {
                    logger.debug("通讯记事无更新数据");
                }
                Thread.sleep(1000L);
                //住宿记事
                List<OcsHotel> ocsHotelList = new ArrayList<>();
                OcsHotelExample ocsHotelExample = new OcsHotelExample();
                OcsHotelExample.Criteria ocsHotelExampleCriteria = ocsHotelExample.createCriteria();
                ocsHotelExampleCriteria.andTenantIdEqualTo(tenantId);
                if (ocsHotelMapperDao.countByExample(ocsHotelExample) == 0) {
                    InvocationInfoProxy.setTenantid(tenantId);
                    List<NodeHotel> nodeHotelList = nodeHotelMapperDao.selectByExample(new NodeHotelExample());
                    if (CollectionUtils.isNotEmpty(nodeHotelList)) {
                        ocsHotelList.clear();
                        for (NodeHotel nodeHotel : nodeHotelList) {
                            OcsHotel ocsHotel = getOcsHotel(tenantId, nodeHotel);
                            ocsHotelList.add(ocsHotel);
                        }
                    }
                } else {
                    //目标中存在数据
                    InvocationInfoProxy.setTenantid(TenantUtils.DEFAULT_TENANT);
                    String startDate = ocsHotelMapperDao.selectMaxDate();
                    InvocationInfoProxy.setTenantid(tenantId);
                    NodeHotelExample nodeHotelExample = new NodeHotelExample();
                    NodeHotelExample.Criteria nodeHotelExampleCriteria = nodeHotelExample.createCriteria();
                    nodeHotelExampleCriteria.andTsBetween(DateUtils.format(startDate),DateUtils.format(sdf.format(new Date())));
                    List<NodeHotel> nodeHotelList = nodeHotelMapperDao.selectByExample(nodeHotelExample);
                    if (CollectionUtils.isNotEmpty(nodeHotelList)) {
                        InvocationInfoProxy.setTenantid(TenantUtils.DEFAULT_TENANT);
                        ocsHotelList.clear();
                        for (NodeHotel nodeHotel : nodeHotelList) {
                            OcsHotelExample example = new OcsHotelExample();
                            OcsHotelExample.Criteria criteria = example.createCriteria();
                            criteria.andPkEqualTo(nodeHotel.getPk());
                            if (ocsHotelMapperDao.countByExample(example) == 0) {
                                OcsHotel ocsHotel = getOcsHotel(tenantId, nodeHotel);
                                ocsHotelList.add(ocsHotel);
                            } else {
                                OcsHotel ocsHotel = getOcsHotel(tenantId, nodeHotel);
                                ocsHotelMapperDao.updateByExample(ocsHotel,example);
                            }

                        }
                    }
                }
                InvocationInfoProxy.setTenantid(TenantUtils.DEFAULT_TENANT);
                if (CollectionUtils.isNotEmpty(ocsHotelList)) {
                    int batchCount = 1000;// 每批commit的个数
                    int batchLastIndex = batchCount;// 每批最后一个的下标
                    for (int index = 0; index < ocsHotelList.size(); ) {
                        if (batchLastIndex >= ocsHotelList.size()) {
                            batchLastIndex = ocsHotelList.size();
                            ocsHotelMapperDao.insertByBatch(ocsHotelList.subList(index, batchLastIndex));
                            logger.debug("住宿记事添加成功");
                            break;// 数据插入完毕，退出循环
                        } else {
                            ocsHotelMapperDao.insertByBatch(ocsHotelList.subList(index, batchLastIndex));
                            index = batchLastIndex;// 设置下一批下标
                            batchLastIndex = index + (batchCount - 1);
                        }
                    }
                } else {
                    logger.debug("住宿记事无更新数据");
                }
                Thread.sleep(1000L);
                //其它记事
                List<OcsOther> ocsOtherList = new ArrayList<>();
                OcsOtherExample ocsOtherExample = new OcsOtherExample();
                OcsOtherExample.Criteria ocsOtherExampleCriteria = ocsOtherExample.createCriteria();
                ocsOtherExampleCriteria.andTenantIdEqualTo(tenantId);
                if (ocsOtherMapperDao.countByExample(ocsOtherExample) == 0) {
                    InvocationInfoProxy.setTenantid(tenantId);
                    List<NodeOther> nodeOtherList = nodeOtherMapperDao.selectByExample(new NodeOtherExample());
                    if (CollectionUtils.isNotEmpty(nodeOtherList)) {
                        ocsOtherList.clear();
                        for (NodeOther nodeOther : nodeOtherList) {
                            OcsOther ocsOther = getOcsOther(tenantId, nodeOther);
                            ocsOtherList.add(ocsOther);
                        }
                    }
                } else {
                    //目标中存在数据
                    InvocationInfoProxy.setTenantid(TenantUtils.DEFAULT_TENANT);
                    String startDate = ocsOtherMapperDao.selectMaxDate();
                    InvocationInfoProxy.setTenantid(tenantId);
                    NodeOtherExample nodeOtherExample = new NodeOtherExample();
                    NodeOtherExample.Criteria nodeOtherExampleCriteria = nodeOtherExample.createCriteria();
                    nodeOtherExampleCriteria.andTsBetween(DateUtils.format(startDate),DateUtils.format(sdf.format(new Date())));
                    List<NodeOther> nodeOtherList = nodeOtherMapperDao.selectByExample(nodeOtherExample);
                    if (CollectionUtils.isNotEmpty(nodeOtherList)) {
                        InvocationInfoProxy.setTenantid(TenantUtils.DEFAULT_TENANT);
                        ocsOtherList.clear();
                        for (NodeOther nodeOther : nodeOtherList) {
                            OcsOtherExample example = new OcsOtherExample();
                            OcsOtherExample.Criteria criteria = example.createCriteria();
                            criteria.andPkEqualTo(nodeOther.getPk());
                            if (ocsOtherMapperDao.countByExample(example) == 0) {
                                OcsOther ocsOther = getOcsOther(tenantId, nodeOther);
                                ocsOtherList.add(ocsOther);
                            } else {
                                OcsOther ocsOther = getOcsOther(tenantId, nodeOther);
                                ocsOtherMapperDao.updateByExample(ocsOther,example);
                            }
                        }
                    }
                }
                InvocationInfoProxy.setTenantid(TenantUtils.DEFAULT_TENANT);
                if (CollectionUtils.isNotEmpty(ocsOtherList)) {
                    int batchCount = 1000;// 每批commit的个数
                    int batchLastIndex = batchCount;// 每批最后一个的下标
                    for (int index = 0; index < ocsOtherList.size(); ) {
                        if (batchLastIndex >= ocsOtherList.size()) {
                            batchLastIndex = ocsOtherList.size();
                            ocsOtherMapperDao.insertByBatch(ocsOtherList.subList(index, batchLastIndex));
                            logger.debug("其它记事添加成功");
                            break;// 数据插入完毕，退出循环
                        } else {
                            ocsOtherMapperDao.insertByBatch(ocsOtherList.subList(index, batchLastIndex));
                            index = batchLastIndex;// 设置下一批下标
                            batchLastIndex = index + (batchCount - 1);
                        }
                    }
                } else {
                    logger.debug("其它记事无更新数据");
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private OcsOther getOcsOther(String tenantId, NodeOther nodeOther) {
        OcsOther ocsOther = new OcsOther();
        BeanUtils.copyProperties(nodeOther, ocsOther);
        ocsOther.setTenantId(tenantId);
        ocsOther.setCreateTime(new Date());
        return ocsOther;
    }

    private OcsHotel getOcsHotel(String tenantId, NodeHotel nodeHotel) {
        OcsHotel ocsHotel = new OcsHotel();
        BeanUtils.copyProperties(nodeHotel, ocsHotel);
        ocsHotel.setTenantId(tenantId);
        ocsHotel.setCreateTime(new Date());
        return ocsHotel;
    }

    private OcsCommunicate getOcsCommunicate(String tenantId, NodeCommunicate nodeCommunicate) {
        OcsCommunicate ocsCommunicate = new OcsCommunicate();
        BeanUtils.copyProperties(nodeCommunicate, ocsCommunicate);
        ocsCommunicate.setTenantId(tenantId);
        ocsCommunicate.setCreateTime(new Date());
        return ocsCommunicate;
    }

    private OcsTravel getOcsTravel(String tenantId, NodeTravel nodeTravel) {
        OcsTravel ocsTravel = new OcsTravel();
        BeanUtils.copyProperties(nodeTravel, ocsTravel);
        ocsTravel.setTenantId(tenantId);
        ocsTravel.setCreateTime(new Date());
        return ocsTravel;
    }

    private OcsEating getOcsEating(String tenantId, NodeEating nodeEating) {
        OcsEating ocsEating = new OcsEating();
        BeanUtils.copyProperties(nodeEating, ocsEating);
        ocsEating.setTenantId(tenantId);
        ocsEating.setCreateTime(new Date());
        return ocsEating;
    }
}
