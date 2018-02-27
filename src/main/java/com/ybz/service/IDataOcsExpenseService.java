package com.ybz.service;

import com.ybz.entity.NodeExpense;

import java.util.Date;
import java.util.List;

/**
 * 报销单据接口
 *
 * @author zhangybt
 * @create 2017年08月02日 15:17
 **/
public interface IDataOcsExpenseService {

    List<NodeExpense> getNodeExpenseList(String currentTime);

    int countByExample(String beginTime, String endTime);

    Double queryMoneyByDate(String beginTime, String endTime);

    int countByCondition(String tenantId, Date countBeginDate, Date countEndDate, String flag);

    Double queryMoneyByCondition(String tenantId, Date countBeginDate, Date countEndDate);
}
