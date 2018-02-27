package com.ybz.service;


import java.util.Date;

/**
 * Created by Administrator on 2017/12/3.
 */
public interface IDataOcsCommunicateService {

    int countByExample(String beginTime, String endTime);

    Double queryMoneyByDate(String beginTime, String endTime);

    int countByCondition(String tenantId, Date countBeginDate, Date countEndDate);

    Double queryMoneyByCondition(String tenantId, Date countBeginDate, Date countEndDate);

}
