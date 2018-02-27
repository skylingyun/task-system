package com.ybz.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class OcsExpense {
    private Integer id;

    private String pk;

    private String userid;

    private String tenantId;

    private Date expenseDate;

    private Double money;

    private Date ts;

    private String djlxpk;

    private Integer billstatus;

    private String billtypecode;

    private String billtypename;

    private String pkBill;

    private Date dealdate;

    private Boolean valid;

    private String zdyhead;

    private String zdyheadvalue;

    private String bankaccountkey;

    private String bankaccount;

    private String szitemkey;

    private String szitemvalue;

    private String requisitionkey;

    private String requisition;

    private String defitem1;

    private String defitem2;

    private String defitem3;

    private String defitem4;

    private String defitem5;

    private String defitem6;

    private String defitem7;

    private String defitem8;

    private String defitem9;

    private String defitem10;

    private Boolean overStandard;

    private String assumeOrg;

    private String assumeDept;

    private String sqdpk;

    private Double reductionAmount;

    private Date reductionTime;

    private String reimbursePerson;

    private Boolean agencyStatus;

    private Boolean overBudgetStatus;

    private Double payAmount1;

    private Double payAmount2;

    private String adminOrg;

    private String financeOrg;

    private String reimburseDept;

    private String currency;

    private Double exchangeRate;

    private Double localMoney;

    private Double restPayMoney;

    private String userOrg;

    private Boolean payType;

    private String reveiveType;

    private String billnum;

    private String assignid;

    private Date createTime;

}