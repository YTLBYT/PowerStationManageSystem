package com.example.powerstationmanagesystem.service;

import com.example.powerstationmanagesystem.controller.request.TroubleRequest;
import com.example.powerstationmanagesystem.entiy.Trouble;

import java.util.List;


public interface TroubleService {
    public int addTrouble(Trouble trouble);
    public Object selectTroubleList(TroubleRequest troubleRequest);
    public int updateTrouble(Trouble trouble);
    public int deleteTrouble(Integer troubleId);
}
