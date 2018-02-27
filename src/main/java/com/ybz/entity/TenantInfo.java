package com.ybz.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/1/20.
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class TenantInfo implements Serializable {

    private String tenant_id; // 租户ID
    private String tenant_name; // 租户名称
    private String open_date; // 开通日期
    private Integer users_number; // 开通总人数
    private Integer submit_users_number;  // 报销人数
    private Double submit_radio; // 报销占比
    private Integer bills_number; // 单据总数量
    private Double bills_money; // 单据总金额
    private Double average_bills_number;  // 人均单据量
    private Integer approve_number;   // 审批总数
    private Integer record_number;// 记账总数
}
