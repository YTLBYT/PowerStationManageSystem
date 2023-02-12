package com.example.powerstationmanagesystem.dao;

import com.example.powerstationmanagesystem.controller.request.TroubleRequest;
import com.example.powerstationmanagesystem.entiy.Trouble;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TroubleDao {
    public int addTrouble(Trouble trouble);
    public List<Trouble> selectTroubleList(TroubleRequest troubleRequest);
    public int updateTrouble(Trouble trouble);
    public int deleteTrouble(Integer troubleId);
}
