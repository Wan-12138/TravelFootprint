package com.travel.service;

import com.travel.dao.CityDao;
import com.travel.dao.ScenicDao;
import com.travel.entity.City;
import com.travel.entity.Scenic;

import java.sql.SQLException;
import java.util.*;

public class ScenicService {

    ScenicDao scenicDao = new ScenicDao();
    CityDao cityDao = new CityDao();

    public List<String> findAllScenicWithCityName()throws SQLException {
        List<Scenic> allScenic = scenicDao.findAll();
        List<City> allCities = cityDao.findAll();
        Map<Integer,String> cityMap = new HashMap<>();

        for (City c : allCities) {
            cityMap.put(c.getCityId(),c.getCity());
        }

        List<String> resultList = new ArrayList<>();
        for (Scenic s : allScenic) {
            String cityName = cityMap.get(s.getScenicCityId());
            String info = cityName + " " + s.getScenic() + " "+s.getType();
            resultList.add(info);
        }
        return resultList;
    }



    public List<Scenic> findByScenicName(String scenicName)throws SQLException{
        return scenicDao.findByName(scenicName);
    }

    public City findCityById(int id)throws SQLException{
        return cityDao.findById(id);
    }

    public void insertScenicWithCityName(String scenicName,String type,String cityName)throws SQLException{
        City city = cityDao.findByName(cityName);
        if (city == null) {
            throw new IllegalArgumentException("cannot find the city");
        }
        Scenic scenic = new Scenic();
        scenic.setScenic(scenicName);
        scenic.setType(type);
        scenic.setScenicCityId(city.getCityId());
        scenicDao.insert(scenic);
    }

    public List<Scenic> getScenicByCityName(String cityName)throws SQLException{
        City city = cityDao.findByName(cityName);
        if(city ==null){
            throw new IllegalArgumentException("cannot find the city");
        }
        return scenicDao.findByCityId(city.getCityId());
    }

    public Scenic getScenicById(int id)throws SQLException{
        return scenicDao.findById(id);
    }

    public void updateScenicByName(Scenic scenic)throws SQLException{
        scenicDao.update(scenic);
    }

    public void deleteScenic(Integer id)throws SQLException{
        scenicDao.deleteByid(id);
    }

}
