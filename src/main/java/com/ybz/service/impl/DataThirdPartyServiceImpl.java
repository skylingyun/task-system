package com.ybz.service.impl;

import com.ybz.entity.OcsEatingExample;
import com.ybz.entity.SysTenant;
import com.ybz.entity.ThirdParty;
import com.ybz.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/1/20.
 */
@Service
public class DataThirdPartyServiceImpl implements IDataThirdPartyService {

    @Autowired
    private IDataSysTenantService dataSysTenantService;

    @Autowired
    private IDataOcsEatingService ocsEatingService;

    @Autowired
    private IDataOcsTravelService ocsTravelService;

    @Autowired
    private IDataOcsHotelService ocsHotelService;

    public List<ThirdParty> selectThirdPartyData() {
        return null;
    }

    @Override
    public List<ThirdParty> selectThirdPartyList(Date countBeginDate, Date countEndDate, int currPage, int pageSize, String role, String tenantName) {
        //获取租户的列表
        List<ThirdParty> thirdPartyList = new ArrayList<>();
        List<SysTenant> tenantList = dataSysTenantService.selectTenantListByTenantName(tenantName);
        for (SysTenant tenant : tenantList) {
            ThirdParty thirdParty = new ThirdParty();
            String tenantId = tenant.getTenantId();
            List<String> list = new ArrayList<>();
            list.add("");
            //查询订单总数 1.餐饮 2.住宿 3.出行
            int eatingCount = ocsEatingService.thirdCountByCondition(tenantId,countBeginDate,countEndDate,list,false);
            int travelCount = ocsTravelService.thirdCountByCondition(tenantId,countBeginDate,countEndDate,list,false);
            int hotelCount = ocsHotelService.thirdCountByCondition(tenantId,countBeginDate,countEndDate,list,false);
            thirdParty.setOrdersNumber(eatingCount+travelCount+hotelCount);
//            thirdParty.set
        }
        return null;
    }
}
