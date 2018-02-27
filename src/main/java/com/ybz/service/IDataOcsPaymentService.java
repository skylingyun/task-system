package com.ybz.service;

import java.util.Date;

/**
 * Created by Administrator on 2018/1/22.
 */
public interface IDataOcsPaymentService {

    int countByCondition(String tenantId, Date countBeginDate, Date countEndDate, String flag);

    Double queryMoneyByCondition(String tenantId, Date countBeginDate, Date countEndDate);
}
