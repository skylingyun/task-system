package com.ybz.service;

import com.ybz.entity.NodeTravel;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 出行接口
 *
 * @author zhangybt
 * @create 2017年08月04日 10:18
 **/
public interface IDataOcsTravelService {

    List<NodeTravel> getNodeTravelList(String currentTime, Integer pageSize, Integer currPage);

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
