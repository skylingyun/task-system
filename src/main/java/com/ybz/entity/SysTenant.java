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
public class SysTenant {
    private Long id;

    private String tenantId;

    private String tenantName;

    private String tenantCode;

    private String tenantAddress;

    private String tenantTel;

    private String tenantEmail;

    private String tenantFullname;

    private Date createTime;

    private Date ts;

    private int userNum;

}