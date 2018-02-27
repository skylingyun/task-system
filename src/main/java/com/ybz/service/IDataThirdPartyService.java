package com.ybz.service;

import com.ybz.entity.ThirdParty;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/1/20.
 */
public interface IDataThirdPartyService {

    List<ThirdParty> selectThirdPartyList(Date countBeginDate, Date countEndDate, int currPage, int pageSize, String role, String tenantName);
}
