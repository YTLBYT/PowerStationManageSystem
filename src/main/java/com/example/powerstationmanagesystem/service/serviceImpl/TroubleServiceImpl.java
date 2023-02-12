package com.example.powerstationmanagesystem.service.serviceImpl;

import com.example.powerstationmanagesystem.controller.request.TroubleRequest;
import com.example.powerstationmanagesystem.dao.TroubleDao;
import com.example.powerstationmanagesystem.entiy.Station;
import com.example.powerstationmanagesystem.entiy.Trouble;
import com.example.powerstationmanagesystem.service.TroubleService;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class TroubleServiceImpl implements TroubleService {

    @Resource
    TroubleDao troubleDao;


    @Override
    public int addTrouble(Trouble trouble) {
        return troubleDao.addTrouble(trouble);
    }

    @Override
    public Object selectTroubleList(TroubleRequest troubleRequest) {
        PageHelper.startPage(troubleRequest.getPageNum(), troubleRequest.getPageSize());
        List<Trouble> troubles = troubleDao.selectTroubleList(troubleRequest);
        return new PageInfo<>(troubles);
    }

    @Override
    public int updateTrouble(Trouble trouble) {
        //更新时要更新时间
        Date date = new Date();
        trouble.setResolveTime(date);
        return troubleDao.updateTrouble(trouble);
    }

    @Override
    public int deleteTrouble(Integer troubleId) {
        return troubleDao.deleteTrouble(troubleId);
    }
}
