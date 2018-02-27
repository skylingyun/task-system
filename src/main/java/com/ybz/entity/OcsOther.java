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
public class OcsOther {
    private Integer id;

    private String pk;

    private String userId;

    private String tenantId;

    private Double money;

    private Date otherDate;

    private Boolean expense;

    private Boolean valid;

    private String category;

    private Date ts;

    private Double taxrate;

    private Double includtax;

    private Double nottax;

    private String invoicenum;

    private String invoicetype;

    private String imagesystemtype;

    private Boolean overStandard;

    private String itemPk;

    private String itemName;

    private String szitemPk;

    private String szitemName;

    private Double reductionAmount;

    private String reductionInfo;

    private Date reductionTime;

    private Date createTime;

}