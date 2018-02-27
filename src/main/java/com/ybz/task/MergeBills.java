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
 * Created by Administrator on 2018/1/17.
 */
@Component("mergeBills")
public class MergeBills {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IDataSysTenantService sysTenantService;

    //-------------------单据(begin)--------------------
    //报销单(旧)
    @Autowired
    private NodeExpenseMapper nodeExpenseMapperDao;
    //报销单(新)
    @Autowired
    private OcsExpenseMapper ocsExpenseMapperDao;
    //申请单(旧)
    @Autowired
    private NodeBusinessTripMapper nodeBusinessTripMapper;
    //申请单(新)
    @Autowired
    private OcsApplyMapper ocsApplyMapperDao;
    //借款单(旧)
    @Autowired
    private NodeLoanBillMapper nodeLoanBillMapperDao;
    //借款单(新)
    @Autowired
    private OcsLoanMapper ocsLoanMapperDao;
    //还款单(旧)
    @Autowired
    private NodePaymentMapper nodePaymentMapperDao;
    //还款单(新)
    @Autowired
    private OcsPaymentMapper ocsPaymentMapperDao;
    //-------------------单据(end)----------------------

    /**
     * 整合单据数据
     */

    public void mergeBills() {
        logger.info("我是带参数的mergeBills方法，正在被执行");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Thread.sleep(1000L);
            List<SysTenant> sysTenantList = sysTenantService.selectTenantList();
            for (SysTenant sysTenant : sysTenantList) {
                String tenantId = sysTenant.getTenantId();
                //报销单据
                List<OcsExpense> ocsExpenseList = new ArrayList<>();
                OcsExpenseExample ocsExpenseExample = new OcsExpenseExample();
                OcsExpenseExample.Criteria ocsExpenseExampleCriteria = ocsExpenseExample.createCriteria();
                ocsExpenseExampleCriteria.andTenantIdEqualTo(tenantId);
                if (ocsExpenseMapperDao.countByExample(ocsExpenseExample) == 0) {
                    InvocationInfoProxy.setTenantid(tenantId);
                    List<NodeExpense> nodeExpenseList = nodeExpenseMapperDao.selectByExample(new NodeExpenseExample());
                    if (CollectionUtils.isNotEmpty(nodeExpenseList)) {
                        ocsExpenseList.clear();
                        for (NodeExpense nodeExpense : nodeExpenseList) {
                            OcsExpense ocsExpense = getOcsExpense(tenantId, nodeExpense);
                            ocsExpenseList.add(ocsExpense);
                        }
                    }
                } else {
                    //目标中存在数据
                    InvocationInfoProxy.setTenantid(TenantUtils.DEFAULT_TENANT);
                    String startDate = ocsExpenseMapperDao.selectMaxDate();
                    InvocationInfoProxy.setTenantid(tenantId);
                    NodeExpenseExample nodeExpenseExample = new NodeExpenseExample();
                    NodeExpenseExample.Criteria nodeExpenseExampleCriteria = nodeExpenseExample.createCriteria();
                    nodeExpenseExampleCriteria.andTsBetween(DateUtils.format(startDate),DateUtils.format(sdf.format(new Date())));
                    List<NodeExpense> nodeExpenseList = nodeExpenseMapperDao.selectByExample(nodeExpenseExample);
                    if (CollectionUtils.isNotEmpty(nodeExpenseList)) {
                        InvocationInfoProxy.setTenantid(TenantUtils.DEFAULT_TENANT);
                        ocsExpenseList.clear();
                        for (NodeExpense nodeExpense : nodeExpenseList) {
                            OcsExpenseExample example = new OcsExpenseExample();
                            OcsExpenseExample.Criteria criteria = example.createCriteria();
                            criteria.andPkEqualTo(nodeExpense.getPk());
                            if (ocsExpenseMapperDao.countByExample(example) == 0) {
                                OcsExpense ocsExpense = getOcsExpense(tenantId, nodeExpense);
                                ocsExpenseList.add(ocsExpense);
                            } else {
                                InvocationInfoProxy.setTenantid(TenantUtils.DEFAULT_TENANT);
                                OcsExpense ocsExpense = getOcsExpense(tenantId, nodeExpense);
                                ocsExpenseMapperDao.updateByExample(ocsExpense,example);
                            }

                        }
                    }
                }
                InvocationInfoProxy.setTenantid(TenantUtils.DEFAULT_TENANT);
                if (CollectionUtils.isNotEmpty(ocsExpenseList)) {
                    int batchCount = 1000;// 每批commit的个数
                    int batchLastIndex = batchCount;// 每批最后一个的下标
                    for (int index = 0; index < ocsExpenseList.size(); ) {
                        if (batchLastIndex >= ocsExpenseList.size()) {
                            batchLastIndex = ocsExpenseList.size();
                            ocsExpenseMapperDao.insertByBatch(ocsExpenseList.subList(index, batchLastIndex));
                            logger.debug("报销单据添加成功");
                            break;// 数据插入完毕，退出循环
                        } else {
                            ocsExpenseMapperDao.insertByBatch(ocsExpenseList.subList(index, batchLastIndex));
                            index = batchLastIndex;// 设置下一批下标
                            batchLastIndex = index + (batchCount - 1);
                        }
                    }
                }

                Thread.sleep(1000L);
                //申请单据
                List<OcsApply> ocsApplyList = new ArrayList<>();
                OcsApplyExample ocsApplyExample = new OcsApplyExample();
                OcsApplyExample.Criteria ocsApplyExampleCriteria = ocsApplyExample.createCriteria();
                ocsApplyExampleCriteria.andTenantIdEqualTo(tenantId);
                if (ocsApplyMapperDao.countByExample(ocsApplyExample) == 0) {
                    InvocationInfoProxy.setTenantid(tenantId);
                    List<NodeBusinessTrip> nodeBusinessTripList = nodeBusinessTripMapper.selectByExample(new NodeBusinessTripExample());
                    if (CollectionUtils.isNotEmpty(nodeBusinessTripList)) {
                        ocsApplyList.clear();
                        for (NodeBusinessTrip nodeBusinessTrip : nodeBusinessTripList) {
                            OcsApply ocsApply = getOcsApply(tenantId, nodeBusinessTrip);
                            ocsApplyList.add(ocsApply);
                        }
                    }
                } else {
                    //目标中存在数据
                    InvocationInfoProxy.setTenantid(TenantUtils.DEFAULT_TENANT);
                    String startDate = ocsApplyMapperDao.selectMaxDate();
                    InvocationInfoProxy.setTenantid(tenantId);
                    NodeBusinessTripExample nodeBusinessTripExample = new NodeBusinessTripExample();
                    NodeBusinessTripExample.Criteria nodeBusinessTripExampleCriteria = nodeBusinessTripExample.createCriteria();
                    nodeBusinessTripExampleCriteria.andTsBetween(DateUtils.format(startDate),DateUtils.format(sdf.format(new Date())));
                    List<NodeBusinessTrip> nodeBusinessTripList = nodeBusinessTripMapper.selectByExample(nodeBusinessTripExample);
                    if (CollectionUtils.isNotEmpty(nodeBusinessTripList)) {
                        InvocationInfoProxy.setTenantid(TenantUtils.DEFAULT_TENANT);
                        ocsApplyList.clear();
                        for (NodeBusinessTrip nodeBusinessTrip : nodeBusinessTripList) {
                            OcsApplyExample example = new OcsApplyExample();
                            OcsApplyExample.Criteria criteria = example.createCriteria();
                            criteria.andPkEqualTo(nodeBusinessTrip.getPk());
                            if (ocsApplyMapperDao.countByExample(example) == 0) {
                                OcsApply ocsApply = getOcsApply(tenantId, nodeBusinessTrip);
                                ocsApplyList.add(ocsApply);
                            } else {
                                OcsApply ocsApply = getOcsApply(tenantId, nodeBusinessTrip);
                                ocsApplyMapperDao.updateByExample(ocsApply,example);
                            }
                        }
                    }
                }
                InvocationInfoProxy.setTenantid(TenantUtils.DEFAULT_TENANT);
                if (CollectionUtils.isNotEmpty(ocsApplyList)) {
                    int batchCount = 1000;// 每批commit的个数
                    int batchLastIndex = batchCount;// 每批最后一个的下标
                    for (int index = 0; index < ocsApplyList.size(); ) {
                        if (batchLastIndex >= ocsApplyList.size()) {
                            batchLastIndex = ocsApplyList.size();
                            ocsApplyMapperDao.insertByBatch(ocsApplyList.subList(index, batchLastIndex));
                            logger.debug("申请单据添加成功");
                            break;// 数据插入完毕，退出循环
                        } else {
                            ocsApplyMapperDao.insertByBatch(ocsApplyList.subList(index, batchLastIndex));
                            index = batchLastIndex;// 设置下一批下标
                            batchLastIndex = index + (batchCount - 1);
                        }
                    }
                } else {
                    logger.debug("申请单无数据更新");
                }
                Thread.sleep(1000L);
                //借款单据
                List<OcsLoan> ocsLoanList = new ArrayList<>();
                OcsLoanExample ocsLoanExample = new OcsLoanExample();
                OcsLoanExample.Criteria ocsLoanExampleCriteria = ocsLoanExample.createCriteria();
                ocsLoanExampleCriteria.andTenantIdEqualTo(tenantId);
                if (ocsLoanMapperDao.countByExample(ocsLoanExample) == 0) {
                    InvocationInfoProxy.setTenantid(tenantId);
                    List<NodeLoanBill> nodeLoanBillList = nodeLoanBillMapperDao.selectByExample(new NodeLoanBillExample());
                    if (CollectionUtils.isNotEmpty(nodeLoanBillList)) {
                        ocsLoanList.clear();
                        for (NodeLoanBill nodeLoanBill : nodeLoanBillList) {
                            OcsLoan ocsLoan = getOcsLoan(tenantId, nodeLoanBill);
                            ocsLoanList.add(ocsLoan);
                        }
                    }
                } else {
                    //目标中存在数据
                    InvocationInfoProxy.setTenantid(TenantUtils.DEFAULT_TENANT);
                    String startDate = ocsLoanMapperDao.selectMaxDate();
                    InvocationInfoProxy.setTenantid(tenantId);
                    NodeLoanBillExample nodeLoanBillExample = new NodeLoanBillExample();
                    NodeLoanBillExample.Criteria nodeLoanBillExampleCriteria = nodeLoanBillExample.createCriteria();
                    nodeLoanBillExampleCriteria.andTsBetween(DateUtils.format(startDate),DateUtils.format(sdf.format(new Date())));
                    List<NodeLoanBill> nodeLoanBillList = nodeLoanBillMapperDao.selectByExample(nodeLoanBillExample);
                    if (CollectionUtils.isNotEmpty(nodeLoanBillList)) {
                        InvocationInfoProxy.setTenantid(TenantUtils.DEFAULT_TENANT);
                        ocsLoanList.clear();
                        for (NodeLoanBill nodeLoanBill : nodeLoanBillList) {
                            OcsLoanExample example = new OcsLoanExample();
                            OcsLoanExample.Criteria criteria = example.createCriteria();
                            criteria.andPkEqualTo(nodeLoanBill.getPk());
                            if (ocsLoanMapperDao.countByExample(example) == 0) {
                                OcsLoan ocsLoan = getOcsLoan(tenantId, nodeLoanBill);
                                ocsLoanList.add(ocsLoan);
                            } else {
                                OcsLoan ocsLoan = getOcsLoan(tenantId, nodeLoanBill);
                                ocsLoanMapperDao.updateByExample(ocsLoan,example);
                            }
                        }
                    }
                }
                InvocationInfoProxy.setTenantid(TenantUtils.DEFAULT_TENANT);
                if (CollectionUtils.isNotEmpty(ocsLoanList)) {
                    int batchCount = 1000;// 每批commit的个数
                    int batchLastIndex = batchCount;// 每批最后一个的下标
                    for (int index = 0; index < ocsLoanList.size(); ) {
                        if (batchLastIndex >= ocsLoanList.size()) {
                            batchLastIndex = ocsLoanList.size();
                            ocsLoanMapperDao.insertByBatch(ocsLoanList.subList(index, batchLastIndex));
                            logger.debug("借款单据添加成功");
                            break;// 数据插入完毕，退出循环
                        } else {
                            ocsLoanMapperDao.insertByBatch(ocsLoanList.subList(index, batchLastIndex));
                            index = batchLastIndex;// 设置下一批下标
                            batchLastIndex = index + (batchCount - 1);
                        }
                    }
                } else {
                    logger.debug("借款单无更新数据");
                }
                Thread.sleep(1000L);
                //还款单据
                List<OcsPayment> ocsPaymentList = new ArrayList<>();
                OcsPaymentExample ocsPaymentExample = new OcsPaymentExample();
                OcsPaymentExample.Criteria ocsPaymentExampleCriteria = ocsPaymentExample.createCriteria();
                ocsPaymentExampleCriteria.andTenantIdEqualTo(tenantId);
                if (ocsPaymentMapperDao.countByExample(ocsPaymentExample) == 0) {
                    InvocationInfoProxy.setTenantid(tenantId);
                    List<NodePayment> nodePaymentList = nodePaymentMapperDao.selectByExample(new NodePaymentExample());
                    if (CollectionUtils.isNotEmpty(nodePaymentList)) {
                        ocsPaymentList.clear();
                        for (NodePayment nodePayment : nodePaymentList) {
                            OcsPayment ocsPayment = getOcsPayment(tenantId, nodePayment);
                            ocsPaymentList.add(ocsPayment);
                        }
                    }
                } else {
                    //目标中存在数据
                    InvocationInfoProxy.setTenantid(TenantUtils.DEFAULT_TENANT);
                    String startDate = ocsPaymentMapperDao.selectMaxDate();
                    InvocationInfoProxy.setTenantid(tenantId);
                    NodePaymentExample nodePaymentExample = new NodePaymentExample();
                    NodePaymentExample.Criteria nodePaymentExampleCriteria = nodePaymentExample.createCriteria();
                    nodePaymentExampleCriteria.andTsBetween(DateUtils.format(startDate),DateUtils.format(sdf.format(new Date())));
                    List<NodePayment> nodePaymentList = nodePaymentMapperDao.selectByExample(nodePaymentExample);
                    if (CollectionUtils.isNotEmpty(nodePaymentList)) {
                        InvocationInfoProxy.setTenantid(TenantUtils.DEFAULT_TENANT);
                        ocsPaymentList.clear();
                        for (NodePayment nodePayment : nodePaymentList) {
                            OcsPaymentExample example = new OcsPaymentExample();
                            OcsPaymentExample.Criteria criteria = example.createCriteria();
                            criteria.andPkEqualTo(nodePayment.getPk());
                            if (ocsPaymentMapperDao.countByExample(example) == 0) {
                                OcsPayment ocsPayment = getOcsPayment(tenantId, nodePayment);
                                ocsPaymentList.add(ocsPayment);
                            } else {
                                OcsPayment ocsPayment = getOcsPayment(tenantId, nodePayment);
                                ocsPaymentMapperDao.updateByExample(ocsPayment,example);
                            }
                        }
                    }
                }
                InvocationInfoProxy.setTenantid(TenantUtils.DEFAULT_TENANT);
                if (CollectionUtils.isNotEmpty(ocsPaymentList)) {
                    int batchCount = 1000;// 每批commit的个数
                    int batchLastIndex = batchCount;// 每批最后一个的下标
                    for (int index = 0; index < ocsPaymentList.size(); ) {
                        if (batchLastIndex >= ocsPaymentList.size()) {
                            batchLastIndex = ocsPaymentList.size();
                            ocsPaymentMapperDao.insertByBatch(ocsPaymentList.subList(index, batchLastIndex));
                            logger.debug("还款单据添加成功");
                            break;// 数据插入完毕，退出循环
                        } else {
                            ocsPaymentMapperDao.insertByBatch(ocsPaymentList.subList(index, batchLastIndex));
                            index = batchLastIndex;// 设置下一批下标
                            batchLastIndex = index + (batchCount - 1);
                        }
                    }
                } else {
                    logger.debug("还款单据无数据更新");
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private OcsPayment getOcsPayment(String tenantId, NodePayment nodePayment) {
        OcsPayment ocsPayment = new OcsPayment();
        BeanUtils.copyProperties(nodePayment, ocsPayment);
        ocsPayment.setTenantId(tenantId);
        ocsPayment.setCreateTime(new Date());
        return ocsPayment;
    }

    private OcsLoan getOcsLoan(String tenantId, NodeLoanBill nodeLoanBill) {
        OcsLoan ocsLoan = new OcsLoan();
        BeanUtils.copyProperties(nodeLoanBill, ocsLoan);
        ocsLoan.setTenantId(tenantId);
        ocsLoan.setCreateTime(new Date());
        return ocsLoan;
    }

    private OcsApply getOcsApply(String tenantId, NodeBusinessTrip nodeBusinessTrip) {
        OcsApply ocsApply = new OcsApply();
        BeanUtils.copyProperties(nodeBusinessTrip, ocsApply);
        ocsApply.setTenantId(tenantId);
        ocsApply.setCreateTime(new Date());
        return ocsApply;
    }


    private OcsExpense getOcsExpense(String tenantId, NodeExpense nodeExpense) {
        OcsExpense ocsExpense = new OcsExpense();
        BeanUtils.copyProperties(nodeExpense, ocsExpense);
        ocsExpense.setTenantId(tenantId);
        ocsExpense.setCreateTime(new Date());
        return ocsExpense;
    }

}
