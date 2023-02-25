package com.example.powerstationmanagesystem.service.serviceImpl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.example.powerstationmanagesystem.controller.request.StationRequest;
import com.example.powerstationmanagesystem.dao.StationDao;
import com.example.powerstationmanagesystem.dao.VideoDao;
import com.example.powerstationmanagesystem.entiy.Car;
import com.example.powerstationmanagesystem.entiy.LoginDTO;
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

/**
 * @author 杨涛
 */
@Service
public class StationServiceImpl implements StationService {

    @Resource
    StationDao stationDao;
    @Resource
    private SqlSessionTemplate sqlSessionTemplate;
    @Resource
    private VideoDao videoDao;

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
        SqlSession sqlSession = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
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
        for (Station station : stations){
            station.setVideoUrl(videoDao.getVideoUrlByStationNumber(station.getStationNumber()));
        }
        return new PageInfo<>(stations);
    }

    @Override
    public Station selectStationByNumber(String stationNumber) {
        return stationDao.selectStationByNumber(stationNumber);
    }

    @Override
    public Integer updateStation(Station station) {
        stationDao.deleteStationToCars(station.getStationId());
        //利用sqlbatch增快批量插入速度
        SqlSession sqlSession = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);//跟上述sql区别
        StationDao mapper = sqlSession.getMapper(StationDao.class);
        List<Integer> carIdList = station.getCarIdList();
        for (Integer carId:carIdList) {
            mapper.addStationToCars(station.getStationId(), carId);
        }
        //提交事务
        sqlSession.commit();
        //清楚缓存
        sqlSession.clearCache();
        return stationDao.updateStation(station);
    }

    @Override
    public Integer updateStatus(Station station){
        return stationDao.updateStation(station);
    }

    @Override
    public Integer deleteStation(Integer stationId) {
        //删除换电站的同时应该删除电站汽车表中的信息
        stationDao.deleteStationToCars(stationId);
        return stationDao.deleteStation(stationId);
    }

    @Override
    public List<Object> getTreeCars() {
        List<Object> carTreeList = new ArrayList<>();
        List<Car> cars = stationDao.getCars();
        List<String> carBrands = stationDao.getCarBrand();
        for(String carBrand: carBrands){
            HashMap<String, Object> treeMap = new HashMap<>();
            treeMap.put("value", 0);
            treeMap.put("label", carBrand);
            List<Object> typeList = new ArrayList<>();
            for (Car car: cars){
                if (car.getCarBrand().equals(carBrand)){
                    HashMap<String, Object> treeMap1 = new HashMap<>();
                    treeMap1.put("value", car.getCarId());
                    treeMap1.put("label", car.getCarType());
                    typeList.add(treeMap1);
                }
            }
            treeMap.put("children", typeList);
            carTreeList.add(treeMap);
        }
        return carTreeList;
    }

    @Override
    public List<Integer> getDefaultCars(Integer stationId) {
        return stationDao.getDefaultCars(stationId);
    }
}
