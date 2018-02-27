package com.ybz.service.impl;

import com.ybz.dao.OcsPaymentMapper;
import com.ybz.entity.OcsPaymentExample;
import com.ybz.service.IDataOcsPaymentService;
import com.ybz.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by zhangybt on 2018/1/22.
 */
@Service
public class DataOcsPaymentServiceImpl implements IDataOcsPaymentService {
    @Autowired
    private OcsPaymentMapper ocsPaymentMapperDao;

    @Override
    public int countByCondition(String tenantId, Date countBeginDate, Date countEndDate, String flag) {
        OcsPaymentExample example = new OcsPaymentExample();
        OcsPaymentExample.Criteria criteria = example.createCriteria();
        criteria.andTenantIdEqualTo(tenantId);
        if (countBeginDate != null && countEndDate != null) {
            criteria.andDealdateBetween(countBeginDate, countEndDate);
        }
        //已完成的单据
        if ("approve".equalsIgnoreCase(flag)) {
            criteria.andBillstatusEqualTo(5);
        }
        return ocsPaymentMapperDao.countByExample(example);
    }

    @Override
    public Double queryMoneyByCondition(String tenantId, Date countBeginDate, Date countEndDate) {
        OcsPaymentExample example = new OcsPaymentExample();
        OcsPaymentExample.Criteria criteria = example.createCriteria();
        criteria.andTenantIdEqualTo(tenantId);
        if (countBeginDate != null && countEndDate != null) {
            criteria.andDealdateBetween(countBeginDate, countEndDate);
        }
        Double money = ocsPaymentMapperDao.queryMoneyByDate(example);
        if (money == null) {
            return 0.0;
        }
        return money;
    }
}
