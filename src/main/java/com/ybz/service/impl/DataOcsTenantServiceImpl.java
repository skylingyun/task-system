package com.ybz.service.impl;

import com.ybz.dao.*;
import com.ybz.entity.*;
import com.ybz.service.*;
import com.ybz.utils.ArithUtils;
import com.ybz.utils.DateUtils;
import com.ybz.utils.ExportExcel;
import com.ybz.utils.LoggerHelper;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by Administrator on 2018/1/22.
 */
@Service
public class DataOcsTenantServiceImpl implements IDataOcsTenantService {

    private static LoggerHelper logger = new LoggerHelper(DataOcsTenantServiceImpl.class);

    @Autowired
    private IDataSysTenantService sysTenantService;

    @Autowired
    private OcsUserMapper ocsUserMapperDao;

    @Autowired
    private IDataOcsExpenseService ocsExpenseService;

    @Autowired
    private IDataOcsApplyService ocsApplyService;

    @Autowired
    private IDataOcsLoanService ocsLoanService;

    @Autowired
    private IDataOcsPaymentService ocsPaymentService;

    @Autowired
    private IDataOcsEatingService ocsEatingService;

    @Autowired
    private IDataOcsTravelService ocsTravelService;

    @Autowired
    private IDataOcsHotelService ocsHotelService;

    @Autowired
    private IDataOcsCommunicateService ocsCommunicateService;

    @Autowired
    private IDataOcsOtherService ocsOtherService;

    @Override
    public int selectTenantCount(Date countBeginDate, Date countEndDate, Date openBeginDate, Date openEndDate, String tenantName) {
        return sysTenantService.selectTenantCount(openBeginDate, openEndDate, tenantName);
    }

    @Override
    public List<TenantInfo> selectTenantDetail(Date countBeginDate, Date countEndDate, Date openBeginDate, Date openEndDate, int currPage, int pageSize, String sorter, String tenantName) {
        List<SysTenant> sysTenantList = sysTenantService.selectTenantListByCondition(openBeginDate, openEndDate, currPage, pageSize, sorter, tenantName);
        return buildTenantData(countBeginDate, countEndDate, sysTenantList);
    }

    /**
     * 组合租户信息数据
     *
     * @param countBeginDate
     * @param countEndDate
     * @param sysTenantList
     * @return
     */
    private List<TenantInfo> buildTenantData(Date countBeginDate, Date countEndDate, List<SysTenant> sysTenantList) {
        List<TenantInfo> tenantInfoList = new ArrayList<>();
        for (SysTenant sysTenant : sysTenantList) {
            String tenantId = sysTenant.getTenantId();
            TenantInfo tenantInfo = new TenantInfo();
            //1.租户名称
            tenantInfo.setTenant_name(sysTenant.getTenantName());
            //2.租户ID
            tenantInfo.setTenant_id(tenantId);
            //3.租户开通时间
            tenantInfo.setOpen_date(DateUtils.format(sysTenant.getCreateTime()));
            //4.开通总人数
            OcsUserExample ocsUserExample = new OcsUserExample();
            OcsUserExample.Criteria ocsUserExampleCriteria = ocsUserExample.createCriteria();
            if (countBeginDate != null && countEndDate != null) {
                ocsUserExampleCriteria.andRegisterdateBetween(countBeginDate, countEndDate);
            }
            ocsUserExampleCriteria.andTenantIdEqualTo(tenantId);
            int totalUserCount = ocsUserMapperDao.countByExample(ocsUserExample);
            tenantInfo.setUsers_number(totalUserCount);
            //5.报销人数
            int submitUsersNumber = ocsExpenseService.countByCondition(tenantId, countBeginDate, countEndDate, "reimburse");
            tenantInfo.setSubmit_users_number(submitUsersNumber);
            //6.报销占比
            if (totalUserCount == 0) {
                tenantInfo.setSubmit_radio(0.0);
            } else {
                tenantInfo.setSubmit_radio(ArithUtils.div((double) submitUsersNumber, (double) totalUserCount));
            }
            //7.单据总数量
            //报销单
            int totalExpenseCount = ocsExpenseService.countByCondition(tenantId, countBeginDate, countBeginDate, null);
            //申请单
            int totalTripCount = ocsApplyService.countByCondition(tenantId, countBeginDate, countEndDate, null);
            //借款单
            int totalLoanCount = ocsLoanService.countByCondition(tenantId, countBeginDate, countEndDate, null);
            //还款单
            int totalPayCount = ocsPaymentService.countByCondition(tenantId, countBeginDate, countEndDate, null);
            tenantInfo.setBills_number(totalExpenseCount + totalTripCount + totalLoanCount + totalPayCount);
            //8.单据总金额
            Double expenseMoney = ocsExpenseService.queryMoneyByCondition(tenantId, countBeginDate, countEndDate);
            Double tripMoney = ocsApplyService.queryMoneyByCondition(tenantId, countBeginDate, countEndDate);
            Double loanMoney = ocsLoanService.queryMoneyByCondition(tenantId, countBeginDate, countEndDate);
            Double paymentMoney = ocsPaymentService.queryMoneyByCondition(tenantId, countBeginDate, countEndDate);
            Double billOneMoney = ArithUtils.add(expenseMoney, tripMoney);
            Double billTwoMoney = ArithUtils.add(loanMoney, paymentMoney);
            Double money = ArithUtils.add(billOneMoney, billTwoMoney);
            tenantInfo.setBills_money(money);
            //9.人均单据量
            if (submitUsersNumber == 0) {
                tenantInfo.setAverage_bills_number(0.0);
            } else {
                tenantInfo.setAverage_bills_number(ArithUtils.div((double) tenantInfo.getBills_number(), (double) submitUsersNumber));
            }
            //10.审批总数
            //报销单
            int approveExpenseCount = ocsExpenseService.countByCondition(tenantId, countBeginDate, countBeginDate, "approve");
            //申请单
            int approveTripCount = ocsApplyService.countByCondition(tenantId, countBeginDate, countEndDate, "approve");
            //借款单
            int approveLoanCount = ocsLoanService.countByCondition(tenantId, countBeginDate, countEndDate, "approve");
            //还款单
            int approvePayCount = ocsPaymentService.countByCondition(tenantId, countBeginDate, countEndDate, "approve");
            tenantInfo.setApprove_number(approveExpenseCount + approveTripCount + approveLoanCount + approvePayCount);
            //11.记账总数
            int eating = ocsEatingService.countByCondition(tenantId, countBeginDate, countEndDate);
            int travel = ocsTravelService.countByCondition(tenantId, countBeginDate, countEndDate);
            int hotel = ocsHotelService.countByCondition(tenantId, countBeginDate, countEndDate);
            int communicate = ocsCommunicateService.countByCondition(tenantId, countBeginDate, countEndDate);
            int other = ocsOtherService.countByCondition(tenantId, countBeginDate, countEndDate);
            tenantInfo.setRecord_number(eating + travel + hotel + communicate + other);
            tenantInfoList.add(tenantInfo);
        }
        return tenantInfoList;
    }

    @Override
    public HSSFWorkbook export(List<String> tenantIdList, Date countBeginDate, Date countEndDate) {
        //获取租户个数
        List<SysTenant> tenantList = sysTenantService.selectTenantListByTenantIds(tenantIdList);
        List<TenantInfo> tenantInfoList = this.buildTenantData(countBeginDate, countEndDate, tenantList);
        String[] excelHeader = {"租户ID","租户名称", "开通日期", "开通总人数", "报销人数", "报销占比", "单据总数量", "单据总金额", "人均单据量", "审批总数", "记账总数"};
        String sheetName = "租户统计详情表";
        return ExportExcel.getHssfWorkbook(tenantInfoList,excelHeader,sheetName);
    }

}
