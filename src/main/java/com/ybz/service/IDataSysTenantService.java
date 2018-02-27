package com.ybz.service;

import com.ybz.entity.SysTenant;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/1/22.
 */
public interface IDataSysTenantService {
    List<SysTenant> selectTenantListByCondition(Date openBeginDate, Date openEndDate, int currPage, int pageSize, String sorter, String tenantName);
    int selectTenantCount(Date openBeginDate, Date openEndDate, String tenantName);
    List<SysTenant> selectTenantList();
    List<SysTenant> selectTenantListByTenantIds(List<String> tenantIdList);
    List<SysTenant> selectTenantListByTenantName(String tenantName);
    List<SysTenant> selectTenantListByPage(int offset, int limit);
    List<SysTenant> selectTenantByTenantId(String tenantId);
    int addTenant(SysTenant sysTenant);

}
