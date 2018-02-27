package com.ybz.service;

import com.ybz.entity.NodeOther;

import java.util.Date;
import java.util.List;

/**
 * 其它记事接口
 *
 * @author zhangybt
 * @create 2017年08月05日 9:32
 **/
public interface IDataOcsOtherService {

    List<NodeOther> getOtherList(String currentTime);

    int countByExample(String beginTime, String endTime);

    Double queryMoneyByDate(String beginTime, String endTime);

    int countByCondition(String tenantId, Date countBeginDate, Date countEndDate);

    Double queryMoneyByCondition(String tenantId, Date countBeginDate, Date countEndDate);

}
