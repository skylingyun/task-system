package com.ybz.service;

import com.ybz.entity.NodeLoanBill;

import java.util.Date;
import java.util.List;

/**
 * 借款还款单据接口
 * Created by Guoru on 2017/8/3.
 */
public interface IDataOcsLoanService {
    List<NodeLoanBill> getNodeLoanBillList(String currentTime);

    int countByExample(String beginTime, String endTime);

    Double queryMoneyByDate(String beginTime, String endTime);

    int countByCondition(String tenantId, Date countBeginDate, Date countEndDate, String flag);

    Double queryMoneyByCondition(String tenantId, Date countBeginDate, Date countEndDate);

}
