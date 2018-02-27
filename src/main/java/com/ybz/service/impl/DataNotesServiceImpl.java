package com.ybz.service.impl;

import com.ybz.entity.NodeNotes;
import com.ybz.entity.SysTenant;
import com.ybz.exception.CustomRuntimeException;
import com.ybz.service.*;
import com.ybz.utils.*;
import com.yonyou.iuap.context.InvocationInfoProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * 所有记事实现类
 *
 * @author zhangybt
 * @create 2017年08月09日 19:31
 **/
@Service
public class DataNotesServiceImpl implements IDataNotesService {

    private static LoggerHelper logger = new LoggerHelper(DataNotesServiceImpl.class);

    @Resource(name = "dataOcsEatingServiceImpl")
    private IDataOcsEatingService nodeEatingService;

    @Resource(name = "dataNodeGatherServiceImpl")
    private IDataNodeGatherService nodeGatherService;

    @Resource(name = "dataNodeCommunicateServiceImpl")
    private IDataNodeCommunicateService nodeCommunicateService;

    @Resource(name = "dataOcsHotelServiceImpl")
    private IDataOcsHotelService nodeHotelService;

    @Resource(name = "dataOcsOtherServiceImpl")
    private IDataOcsOtherService nodeOtherService;

    @Resource(name = "dataOcsTravelServiceImpl")
    private DataOcsTravelServiceImpl nodeTravelService;

    @Autowired
    private IDataSysTenantService dataSysTenantService;

    public List<List<NodeNotes>> getDataNotesPage(String beginTime, String endTime, int offset, int limit) throws Exception {
        List<List<NodeNotes>> notesLists = new ArrayList<List<NodeNotes>>();
        try {
//            List<String> dataBasesList = DataBaseUtils.queryDataBasesListFromExcel(offset, limit);
            List<SysTenant> sysTenantList = dataSysTenantService.selectTenantListByPage(offset, limit);
            for (SysTenant sysTenant : sysTenantList) {
                if (sysTenant.getTenantId().length() != 8) {
                    continue;
                }
                InvocationInfoProxy.setTenantid(sysTenant.getTenantId());
                List<NodeNotes> nodeNotesList = queryNotesByTenantId(beginTime, endTime, sysTenant.getTenantId(), sysTenant.getTenantName());
                notesLists.add(nodeNotesList);
            }
        } catch (Exception e) {
            logger.error("查询记事时获取租户失败", e.getMessage());
            throw new CustomRuntimeException("查询记事时获取租户失败");
        }
        return notesLists;
    }

    private List<NodeNotes> queryNotesByTenantId(String beginTime, String endTime, String tenantId, String tenantName) {
        InvocationInfoProxy.setTenantid(tenantId);
        NodeNotes nodeNotes = new NodeNotes();
        List<NodeNotes> list = new LinkedList<NodeNotes>();
        int travelCount = nodeTravelService.countByExample(beginTime, endTime);
        Double travelMoney = nodeTravelService.queryMoneyByDate(beginTime, endTime);
        int eatingCount = nodeEatingService.countByExample(beginTime, endTime);
        Double eatingMoney = nodeEatingService.queryMoneyByDate(beginTime, endTime);
        int gatherCount = nodeCommunicateService.countByExample(beginTime, endTime);
        Double gatherMoney = nodeCommunicateService.queryMoneyByDate(beginTime, endTime);
        int hotelCount = nodeHotelService.countByExample(beginTime, endTime);
        Double hotelMoney = nodeHotelService.queryMoneyByDate(beginTime, endTime);
        int otherCount = nodeOtherService.countByExample(beginTime, endTime);
        Double otherMoney = nodeOtherService.queryMoneyByDate(beginTime, endTime);
        double travel = ArithUtils.add(travelMoney, eatingMoney);
        double gather = ArithUtils.add(travel, gatherMoney);
        double hotel = ArithUtils.add(gather, hotelMoney);
        double totalMoney = ArithUtils.add(hotel, otherMoney);
        nodeNotes.setTenantId(tenantId);
        nodeNotes.setTenantName(tenantName);
        nodeNotes.setTravel(travelCount);
        nodeNotes.setTravelMoney(travelMoney);
        nodeNotes.setEating(eatingCount);
        nodeNotes.setEatingMoney(eatingMoney);
        nodeNotes.setGather(gatherCount);
        nodeNotes.setGatherMoney(gatherMoney);
        nodeNotes.setHotel(hotelCount);
        nodeNotes.setHotelMoney(hotelMoney);
        nodeNotes.setOther(otherCount);
        nodeNotes.setOtherMoney(otherMoney);
        nodeNotes.setTotalMoney(totalMoney);
        nodeNotes.setBeforeDay(beginTime);
        nodeNotes.setAfterDay(endTime);
        list.add(nodeNotes);
        return list;
    }

    @Override
    public List<List<NodeNotes>> queryFiveDaysNotes(String tenantId) {
        List<List<NodeNotes>> lists = new LinkedList<List<NodeNotes>>();
        for (int i = 5; i > 0; i--) {
            String beforeDay = DateUtils.format(DateUtils.getDateBefore(new Date(), i));
            String afterDay = DateUtils.format(DateUtils.getDateBefore(new Date(), i - 1));
            List<NodeNotes> nodeNotesList = this.queryNotesByTenantId(beforeDay, afterDay, tenantId, null);
            lists.add(nodeNotesList);
        }
        return lists;
    }


}
