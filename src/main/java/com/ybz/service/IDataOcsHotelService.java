package com.ybz.service;

import com.ybz.entity.NodeHotel;

import java.util.Date;
import java.util.List;

/**
 * 住宿记事接口
 *
 * @author zhangybt
 * @create 2017年08月05日 10:15
 **/
public interface IDataOcsHotelService {

    List<NodeHotel> getNodeHotelList(String currentTime, Integer pageSize, Integer currPage);

    int countByExample(String beginTime, String endTime);

    Double queryMoneyByDate(String beginTime, String endTime);

    int countByCondition(String tenantId, Date countBeginDate, Date countEndDate);

    Double queryMoneyByCondition(String tenantId, Date countBeginDate, Date countEndDate);

    /**
     * 第三方统计数据
     */
    int thirdCountByCondition(String tenantId, Date countBeginDate, Date countEndDate, List<String> list, boolean flag);

    /**
     * 第三方统计金额
     */
    Double queryThirdMoneyByCondition(String tenantId, Date countBeginDate, Date countEndDate, List<String> list, boolean flag);
}
