package com.travel.service;

import com.travel.dao.CityDao;
import com.travel.dao.ProvinceDao;
import com.travel.dao.TravelCityDao;
import com.travel.dao.TravelDao;
import com.travel.entity.City;
import com.travel.entity.Province;
import com.travel.JDBCUtils;
import com.travel.entity.Travel;
import com.travel.entity.TravelCity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

public class CityService {

    private TravelDao travelDao = new TravelDao();
    private CityDao cityDao = new CityDao();
    private TravelCityDao travelCityDao = new TravelCityDao();
    private ProvinceDao provinceDao = new ProvinceDao();

    public Map<String,List<String>> getVisitedprovincesAndCities()throws Exception{
        // 1. 先拿到所有的旅行记录
        List<Travel> allTravels = travelDao.findAll();
        // 2. 用一个 Set 收集所有去过的城市 ID（Set 自动去重，防止去同一个城市多次导致重复）
        Set<Integer> visitedCityIds = new HashSet<>();
        for (Travel t : allTravels) {
            List<TravelCity> tcs = travelCityDao.findByTravelId(t.getTravelId());
            for (TravelCity tc : tcs) {
                visitedCityIds.add(tc.getTcCityId());
            }
        }
        // 3. 准备一个 Map 用来拼装最终结果（用 TreeMap 保证打印时省份按首字母排序，好看点）
        Map<String, List<String>> resultMap = new TreeMap<>();

        // 4. 遍历去过的城市 ID，查出城市详情和省份详情，放进 Map
        for (Integer cityId : visitedCityIds) {
            City city = cityDao.findById(cityId);
            if (city == null) continue;

            Province province = provinceDao.findById(city.getProvinceId());
            if (province == null) continue;

            String provinceName = province.getProvince();
            String cityName = city.getCity();

            // 如果 Map 里还没有这个省份的 key，就创建一个空 List
            resultMap.putIfAbsent(provinceName, new ArrayList<>());
            // 把城市名加到这个省份对应的 List 里
            resultMap.get(provinceName).add(cityName);
        }
        return resultMap;
    }


    public List<Province> notVisitedProvince()throws SQLException {
        List<Travel> alltravels = travelDao.findAll();
        Set<Integer> visitedCityId = new HashSet<>();
        for (Travel t : alltravels) {
            //遍历所有travelId
            //按travelid找到去过的城市记录
            for (TravelCity tc : travelCityDao.findByTravelId(t.getTravelId())) {
                //获取去过的城市id并添加至Set集合
                visitedCityId.add(tc.getTcCityId());
            }
        }

        Set<Integer> visitedProvinceIdSet = new HashSet<>();
        List<Province> allProvince = provinceDao.findAll();
        for (Integer i : visitedCityId) {
            City everyVisitCity = cityDao.findById(i);
            Integer visitprovinceId = everyVisitCity.getProvinceId();
            visitedProvinceIdSet.add(visitprovinceId);
        }

        List<Province> notVisitedProvinceList = new ArrayList<>();

        for (Province province : allProvince) {
            if(!visitedProvinceIdSet.contains(province.getProvinceId())){
                notVisitedProvinceList.add(province);
            }
        }

        return notVisitedProvinceList;


    }

}