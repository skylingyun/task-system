package com.ybz.controller;

import com.ybz.entity.NodeNotes;
import com.ybz.service.IDataNotesService;
import com.ybz.service.IDataOcsTenantService;
import com.ybz.service.IDataOcsTravelService;
import com.ybz.service.IDataThirdPartyService;
import com.ybz.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 第三方
 *
 * @author zhangybt
 * @create 2017年08月09日 19:27
 **/
@RestController
@RequestMapping("/third")
public class DataThirdPartyController {

    @Autowired
    private IDataThirdPartyService dataThirdPartyService;

    @Autowired
    private IDataOcsTenantService dataOcsTenantService;

    @RequestMapping(value = "/thirdData", method = RequestMethod.GET)
    public DataResult selectThirdPartyData(HttpServletRequest request) throws Exception {
//        Map<String,Integer> condition = new HashMap<>();
//        condition.put("currentPage",request.getParameter("currentPage") == null ? 1 : Integer.parseInt(request.getParameter("currentPage")));
//        condition.put("pageSize",request.getParameter("pageSize") == null ? 10 : Integer.parseInt(request.getParameter("pageSize")));
//        Map<String,Date> conditionDate = new HashMap<>();
//        conditionDate.put("countBeginDate",request.getParameter("countBeginDate") == null ? null : DateUtils.format(request.getParameter("countBeginDate")));
//        conditionDate.put("countEndDate",request.getParameter("countEndDate") == null ? null : DateUtils.format(request.getParameter("countEndDate")));
//        conditionDate.put("openBeginDate",request.getParameter("openBeginDate") == null ? null : DateUtils.format(request.getParameter("openBeginDate")));
//        conditionDate.put("openEndDate",request.getParameter("openEndDate") == null ? null : DateUtils.format(request.getParameter("openEndDate")));
//        List<TenantInfo> tenantList = dataOcsTenantService.selectTenantDetail(conditionDate.get("countBeginDate"), conditionDate.get("countEndDate"),
//                conditionDate.get("openBeginDate"), conditionDate.get("openEndDate"), condition.get("currentPage"),
//                condition.get("pageSize"),request.getParameter("sorter"),request.getParameter("searchStr"));
//        int count = dataOcsTenantService.selectTenantCount(conditionDate.get("countBeginDate"), conditionDate.get("countEndDate"),
//                conditionDate.get("openBeginDate"), conditionDate.get("openEndDate"),request.getParameter("searchStr"));
//        Map<String,Integer> conditionMap = new HashMap<>();
//        conditionMap.put("current",condition.get("currentPage"));
//        conditionMap.put("pageSize",condition.get("pageSize"));
//        conditionMap.put("total",count);
//        PageUtil pageUtils = new PageUtil(tenantList, conditionMap);
        return DataResult.ok().put("data", null);
    }

}
