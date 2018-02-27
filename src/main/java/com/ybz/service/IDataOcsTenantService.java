package com.ybz.service;

import com.ybz.entity.TenantInfo;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.Date;
import java.util.List;

/**
 * Created by zhangybt on 2018/1/22.
 */
public interface IDataOcsTenantService {


    int selectTenantCount(Date countBeginDate, Date countEndDate, Date openBeginDate, Date openEndDate, String tenantName);

    List<TenantInfo> selectTenantDetail(Date countBeginDate, Date countEndDate, Date openBeginDate, Date openEndDate, int currPage, int pageSize, String sorter, String tenantName);

    HSSFWorkbook export(List<String> tenantId, Date countBeginDate, Date countEndDate);
}
