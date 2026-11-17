package com.travel.service;

import com.travel.dao.CityDao;
import com.travel.dao.FoodDao;
import com.travel.entity.City;
import com.travel.entity.Food;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FoodService {
    FoodDao foodDao = new FoodDao();
    CityDao cityDao = new CityDao();

    public List<String> findAllFoodWithCity()throws SQLException {
        List<Food> allFood = foodDao.findAll();
        List<String> resultList = new ArrayList<>();
        for (Food food : allFood) {
            Integer foodCityId = food.getCityId();
            City cityById = cityDao.findById(foodCityId);
            String cityName = (cityById != null) ? cityById.getCity() : "未知城市";
            String result = "FoodName is: "+food.getFood()+" food flavor is: "+food.getFlavor()+"\n"+
                            "taste score is : "+food.getTasteScore()+" value for money score is: "+food.getValueformoneyScore()+
                            "\n"+"city is: "+cityName;

            resultList.add(result);
        }
        return resultList;
    }

    public void insertFoodWithCityService(String foodName,String flavor,Integer tasteScore,Integer valueformoneyScore,String cityName)throws SQLException{
        City city = cityDao.findByName(cityName);
        if(city == null){
            throw new IllegalArgumentException("the city is not found!");
        }

        Food food = new Food();
        food.setFood(foodName);
        food.setFlavor(flavor);
        food.setTasteScore(tasteScore);
        food.setValueformoneyScore(valueformoneyScore);
        food.setCityId(city.getCityId());

        foodDao.insert(food);
    }

    public List<Food> getFoodByCityName(String cityName)throws SQLException{
        City city = cityDao.findByName(cityName);
        if(city == null){
            throw new IllegalArgumentException("cannot find the city!");
        }
        return foodDao.findByCityId(city.getCityId());
    }

    public Food findFoodWithID(int id)throws SQLException{
        return foodDao.findById(id);
    }

    public List<Food> findFoodWithName(String foodName)throws SQLException{
        return foodDao.findFoodByName(foodName);
    }

    public void updateFoodWithCity(Food food,String cityName)throws SQLException{

        City newCity = cityDao.findByName(cityName);
        if(newCity == null){
            throw new IllegalArgumentException("cannot find the city");
        }
        food.setCityId(newCity.getCityId());
        foodDao.update(food);
    }

    public void deleteById(int id)throws SQLException{
        foodDao.deleteById(id);
    }


}
