package com.ybz.service.impl;

import com.ybz.dao.NodeExpenseMapper;
import com.ybz.dao.OcsExpenseMapper;
import com.ybz.entity.NodeExpense;
import com.ybz.entity.NodeExpenseExample;
import com.ybz.entity.OcsExpenseExample;
import com.ybz.service.IDataOcsExpenseService;
import com.ybz.utils.DateUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 报销单据实现类
 *
 * @author zhangybt
 * @create 2017年08月02日 15:17
 **/
@Service
public class DataOcsExpenseServiceImpl implements IDataOcsExpenseService {

    @Autowired
    private NodeExpenseMapper nodeExpenseMapperDao;

    @Autowired
    private OcsExpenseMapper ocsExpenseMapperDao;

    public List<NodeExpense> getNodeExpenseList(String currentTime) {
        NodeExpenseExample nodeExpenseExample = new NodeExpenseExample();
        NodeExpenseExample.Criteria criteria = nodeExpenseExample.createCriteria();
        nodeExpenseExample.setDistinct(true);
        nodeExpenseExample.setOrderByClause("ts desc");
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
        return nodeExpenseMapperDao.selectByExample(nodeExpenseExample);
    }

    @Override
    public int countByExample(String beginTime, String endTime) {
        NodeExpenseExample nodeExpenseExample = new NodeExpenseExample();
        nodeExpenseExample.isDistinct();
        nodeExpenseExample.setOrderByClause("ts desc");
        NodeExpenseExample.Criteria criteria = nodeExpenseExample.createCriteria();
        criteria.andValidEqualTo(true);
        if (StringUtils.isNotEmpty(beginTime) && StringUtils.isNotEmpty(endTime)) {
            Date beginDate = DateUtils.format(beginTime);
            Date endDate = DateUtils.format(endTime);
            criteria.andTsBetween(beginDate, endDate);
        }
        return nodeExpenseMapperDao.countByExample(nodeExpenseExample);
    }

    @Override
    public Double queryMoneyByDate(String beginTime, String endTime) {
        NodeExpenseExample example = new NodeExpenseExample();
        example.setDistinct(true);
        NodeExpenseExample.Criteria criteria = example.createCriteria();
        criteria.andValidEqualTo(true);
        if(StringUtils.isNotEmpty(beginTime) && StringUtils.isNotEmpty(endTime)){
            Date beginDate = DateUtils.format(beginTime);
            Date endDate = DateUtils.format(endTime);
            criteria.andTsBetween(beginDate,endDate);
        }
        Double nodeExpenseMoney = nodeExpenseMapperDao.queryMoneyByDate(example);
        if(nodeExpenseMoney == null){
            return 0.0;
        }
        return nodeExpenseMoney;
    }

    @Override
    public int countByCondition(String tenantId, Date countBeginDate, Date countEndDate,String flag) {
        OcsExpenseExample example = new OcsExpenseExample();
        OcsExpenseExample.Criteria criteria = example.createCriteria();
        criteria.andTenantIdEqualTo(tenantId);
        if(countBeginDate!=null && countEndDate != null){
            criteria.andExpenseDateBetween(countBeginDate,countEndDate);
        }
        //报销人数量
        if("reimburse".equalsIgnoreCase(flag)){
            return ocsExpenseMapperDao.distinctUserIdCount(example);
        }
        //已完成的单据
        if("approve".equalsIgnoreCase(flag)){
            criteria.andBillstatusEqualTo(5);
        }
        return ocsExpenseMapperDao.countByExample(example);
    }

    @Override
    public Double queryMoneyByCondition(String tenantId, Date countBeginDate, Date countEndDate) {
        OcsExpenseExample example = new OcsExpenseExample();
        OcsExpenseExample.Criteria criteria = example.createCriteria();
        criteria.andTenantIdEqualTo(tenantId);
        if(countBeginDate!=null && countEndDate != null){
            criteria.andExpenseDateBetween(countBeginDate,countEndDate);
        }
        Double nodeExpenseMoney = ocsExpenseMapperDao.queryMoneyByDate(example);
        if(nodeExpenseMoney == null){
            return 0.0;
        }
        return nodeExpenseMoney;
    }
}
