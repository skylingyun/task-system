package com.ybz.controller;

import com.ybz.entity.TenantInfo;
import com.ybz.service.IDataOcsTenantService;
import com.ybz.utils.DataResult;
import com.ybz.utils.DateUtils;
import com.ybz.utils.PageUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;

/**
 * Created by Administrator on 2018/1/22.
 */
@RestController
@RequestMapping("/api")
public class DataOcsTenantController {

    @Resource(name = "dataOcsTenantServiceImpl")
    private IDataOcsTenantService dataOcsTenantService;


    @RequestMapping(value = "/tenantData", method = RequestMethod.GET)
    public DataResult getDataTenantInfo( HttpServletRequest request) throws Exception {
        Map<String,Integer> condition = new HashMap<>();
        condition.put("currentPage",request.getParameter("currentPage") == null ? 1 : Integer.parseInt(request.getParameter("currentPage")));
        condition.put("pageSize",request.getParameter("pageSize") == null ? 10 : Integer.parseInt(request.getParameter("pageSize")));
        Map<String,Date> conditionDate = new HashMap<>();
        conditionDate.put("countBeginDate",request.getParameter("countBeginDate") == null ? null : DateUtils.format(request.getParameter("countBeginDate")));
        conditionDate.put("countEndDate",request.getParameter("countEndDate") == null ? null : DateUtils.format(request.getParameter("countEndDate")));
        conditionDate.put("openBeginDate",request.getParameter("openBeginDate") == null ? null : DateUtils.format(request.getParameter("openBeginDate")));
        conditionDate.put("openEndDate",request.getParameter("openEndDate") == null ? null : DateUtils.format(request.getParameter("openEndDate")));
        List<TenantInfo> tenantList = dataOcsTenantService.selectTenantDetail(conditionDate.get("countBeginDate"), conditionDate.get("countEndDate"),
        conditionDate.get("openBeginDate"), conditionDate.get("openEndDate"), condition.get("currentPage"),
                condition.get("pageSize"),request.getParameter("sorter"), request.getParameter("searchStr")== null ? null : new String (request.getParameter("searchStr").getBytes("iso-8859-1"),"utf-8"));
        int count = dataOcsTenantService.selectTenantCount(conditionDate.get("countBeginDate"), conditionDate.get("countEndDate"),
                conditionDate.get("openBeginDate"), conditionDate.get("openEndDate"),request.getParameter("searchStr")== null ? null : new String (request.getParameter("searchStr").getBytes("iso-8859-1"),"utf-8"));
        Map<String,Integer> conditionMap = new HashMap<>();
        conditionMap.put("current",condition.get("currentPage"));
        conditionMap.put("pageSize",condition.get("pageSize"));
        conditionMap.put("total",count);
        PageUtil pageUtils = new PageUtil(tenantList, conditionMap);
        return DataResult.ok().put("data", pageUtils);
    }

    @RequestMapping(value = "/exportData", method = RequestMethod.GET)
    public void exportData(HttpServletRequest request, HttpServletResponse response) throws Exception{
        List<String> tenantIdList = new ArrayList<>();
        if(request.getParameter("exportingData") == null){
            tenantIdList = null;
        }else {
            JSONObject jsonObject = JSONObject.fromObject(request.getParameter("exportingData"));
            JSONArray jsonArray = JSONArray.fromObject(jsonObject.optString("exportingData"));
            for (int i = 0; i <jsonArray.size() ; i++) {
                tenantIdList.add(jsonArray.optString(i));
            }
        }
        Map<String,Date> conditionDate = new HashMap<>();
        conditionDate.put("countBeginDate",request.getParameter("countBeginDate") == null ? null : DateUtils.format(request.getParameter("countBeginDate")));
        conditionDate.put("countEndDate",request.getParameter("countEndDate") == null ? null : DateUtils.format(request.getParameter("countEndDate")));
        HSSFWorkbook wb = dataOcsTenantService.export(tenantIdList,conditionDate.get("countBeginDate"),conditionDate.get("countEndDate"));
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename="+ URLEncoder.encode("租户信息详情.xls","utf-8"));
        OutputStream out = response.getOutputStream();
        wb.write(out);
        out.flush();
        out.close();
    }
}
