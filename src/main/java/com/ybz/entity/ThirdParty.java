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
public class ThirdParty implements Serializable {
    private String tenantId;          //租户ID
    private String tenantName;        //租户名称
    private String countRange;        //统计区间
    private String providerName;      //服务商名称
    private Integer providersNumber;  //服务商数量
    private Integer ordersNumber;     //订单总数
    private Double ordersMoney;       //订单总金额
    private Integer syncNotesNumber;  //同步记事数
    private Integer submittedNotesNumber; //已报销记事数
    private Double submittedNotesMoney; //已报销记事总金额
    private Integer tenantCount;       //租户数量
}
