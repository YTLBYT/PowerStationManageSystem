package com.example.powerstationmanagesystem.service.serviceImpl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.example.powerstationmanagesystem.controller.request.StationRequest;
import com.example.powerstationmanagesystem.dao.StationDao;
import com.example.powerstationmanagesystem.entiy.Car;
import com.example.powerstationmanagesystem.entiy.Station;
import com.example.powerstationmanagesystem.service.StationService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class StationServiceImpl implements StationService {

    @Resource
    StationDao stationDao;
    @Resource
    private SqlSessionTemplate sqlSessionTemplate;

    @Override
    public int addStation(Station station) {
        //编号不由输入，由工具类自动生成
        Date date = new Date();
        //由hutool工具类自动生成卡号并且取哈希值便于阅读，前面拼接日期更有意义
        station.setStationNumber(DateUtil.format(date,"yyyyMMdd") + Math.abs(IdUtil.nanoId(6).hashCode()));
        station.setChargeNumber(station.getChargeTotal());
        stationDao.addStation(station);
        //在插入之后把id返回回来
        Station station1 = stationDao.selectStationByNumber(station.getStationNumber());
        //利用sqlbatch增快批量插入速度
        SqlSession sqlSession = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);//跟上述sql区别
        StationDao mapper = sqlSession.getMapper(StationDao.class);
        List<Integer> carIdList = station.getCarIdList();
        for (Integer carId:carIdList) {
            mapper.addStationToCars(station1.getStationId(), carId);
        }
        //提交事务
        sqlSession.commit();
        //清楚缓存
        sqlSession.clearCache();
        return 0;
    }

    @Override
    public List<Car> getCars() {
        return stationDao.getCars();
    }

    @Override
    public Object selectPageStation(StationRequest stationRequest) {
        PageHelper.startPage(stationRequest.getPageNum(), stationRequest.getPageSize());
        List<Station> stations = stationDao.selectPageStation(stationRequest);
        return new PageInfo<>(stations);
    }

    @Override
    public Station selectStationByNumber(String stationNumber) {
        return stationDao.selectStationByNumber(stationNumber);
    }

    @Override
    public Integer updateStation(Station station) {
        return stationDao.updateStation(station);
    }

    @Override
    public Integer deleteStation(Integer stationId) {
        //删除换电站的同时应该删除电站汽车表中的信息
        stationDao.deleteStationToCars(stationId);
        return stationDao.deleteStation(stationId);
    }


}
