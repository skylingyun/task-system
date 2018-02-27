package com.ybz.service.impl;

import com.ybz.dao.NodeBusinessTripMapper;
import com.ybz.dao.OcsApplyMapper;
import com.ybz.entity.NodeBusinessTrip;
import com.ybz.entity.NodeBusinessTripExample;
import com.ybz.entity.OcsApplyExample;
import com.ybz.service.IDataOcsApplyService;
import com.ybz.utils.DateUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 申请单据实现类
 * Created by Guoru on 2017/8/3.
 */

@Service
public class DataOcsApplyServiceImpl implements IDataOcsApplyService {
    @Autowired
    private NodeBusinessTripMapper nodeBusinessTripMapperDao;
    @Autowired
    private OcsApplyMapper ocsApplyMapperDao;

    public List<NodeBusinessTrip> getNodeBusinessTripList(String currentTime){
        NodeBusinessTripExample nodeBusinessTripExample = new NodeBusinessTripExample();
        NodeBusinessTripExample.Criteria criteria = nodeBusinessTripExample.createCriteria();
        nodeBusinessTripExample.setDistinct(true);
        nodeBusinessTripExample.setOrderByClause("ts desc");
        criteria.andValidEqualTo(true);

        if (currentTime != null) {
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd ");
                Date currentDate = simpleDateFormat.parse(currentTime);
                criteria.andTsGreaterThanOrEqualTo(currentDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return nodeBusinessTripMapperDao.selectByExample(nodeBusinessTripExample);
    }


    @Override
    public int countByExample(String beginTime, String endTime) {
        NodeBusinessTripExample nodeBusinessTripExample = new NodeBusinessTripExample();
        nodeBusinessTripExample.isDistinct();
        nodeBusinessTripExample.setOrderByClause("ts desc");
        NodeBusinessTripExample.Criteria criteria = nodeBusinessTripExample.createCriteria();
        criteria.andValidEqualTo(true);
        if (StringUtils.isNotEmpty(beginTime) && StringUtils.isNotEmpty(endTime)) {
            Date beginDate = DateUtils.format(beginTime);
            Date endDate = DateUtils.format(endTime);
            criteria.andTsBetween(beginDate, endDate);
        }
        return nodeBusinessTripMapperDao.countByExample(nodeBusinessTripExample);
    }

    @Override
    public Double queryMoneyByDate(String beginTime, String endTime) {
        NodeBusinessTripExample example = new NodeBusinessTripExample();
        example.setDistinct(true);
        NodeBusinessTripExample.Criteria criteria = example.createCriteria();
        criteria.andValidEqualTo(true);
        if(StringUtils.isNotEmpty(beginTime) && StringUtils.isNotEmpty(endTime)){
            Date beginDate = DateUtils.format(beginTime);
            Date endDate = DateUtils.format(endTime);
            criteria.andTsBetween(beginDate,endDate);
        }
        Double nodeBusinessTripMoney =  nodeBusinessTripMapperDao.queryMoneyByDate(example);
        if(nodeBusinessTripMoney == null){
            return 0.0;
        }
        return nodeBusinessTripMoney;
    }

    @Override
    public int countByCondition(String tenantId, Date countBeginDate, Date countEndDate,String flag) {
        OcsApplyExample example = new OcsApplyExample();
        OcsApplyExample.Criteria criteria = example.createCriteria();
        criteria.andTenantIdEqualTo(tenantId);
        if(countBeginDate != null && countEndDate != null){
            criteria.andDealdateBetween(countBeginDate,countEndDate);
        }
        //已完成的单据
        if("approve".equalsIgnoreCase(flag)){
            criteria.andBillstatusEqualTo(5);
        }
        return ocsApplyMapperDao.countByExample(example);
    }

    @Override
    public Double queryMoneyByCondition(String tenantId, Date countBeginDate, Date countEndDate) {
        OcsApplyExample example = new OcsApplyExample();
        OcsApplyExample.Criteria criteria = example.createCriteria();
        criteria.andTenantIdEqualTo(tenantId);
        if(countBeginDate != null && countEndDate != null){
            criteria.andDealdateBetween(countBeginDate,countEndDate);
        }
        Double money = ocsApplyMapperDao.queryMoneyByDate(example);
        if(money == null){
            return 0.0;
        }
        return money;
    }
}
