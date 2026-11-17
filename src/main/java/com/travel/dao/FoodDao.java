package com.travel.dao;

import com.travel.entity.Food;
import com.travel.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FoodDao {

    public List<Food> findAll() throws SQLException {
        List<Food> foodList = new ArrayList<>();
        String sql = "select * from tb_food";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Food food = new Food();
                food.setFoodId(rs.getInt("food_id"));
                food.setFood(rs.getString("food"));
                food.setFlavor(rs.getString("flavor"));
                food.setTasteScore(rs.getInt("taste_score"));
                food.setValueformoneyScore(rs.getInt("valueformoney_score"));
                food.setCityId(rs.getInt("city_id"));
                foodList.add(food);
            }
        }
        return foodList;
    }

    public Food findById(Integer id) throws SQLException {
        String sql = "select * from tb_food where food_id = ?";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Food food = new Food();
                    food.setFoodId(rs.getInt("food_id"));
                    food.setFood(rs.getString("food"));
                    food.setFlavor(rs.getString("flavor"));
                    food.setTasteScore(rs.getInt("taste_score"));
                    food.setValueformoneyScore(rs.getInt("valueformoney_score"));
                    food.setCityId(rs.getInt("city_id"));
                    return food;
                }
            }
        }
        return null;
    }

    public List<Food> findFoodByName(String name)throws SQLException{
        List<Food> foodList = new ArrayList<>();
        String sql = "select * from tb_food where food = ?";
        try(Connection conn = JDBCUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1,name);
            try(ResultSet rs = ps.executeQuery()) {
                while (rs.next()){
                    Food food = new Food();
                    food.setFoodId(rs.getInt("food_id"));
                    food.setFood(rs.getString("food"));
                    food.setFlavor(rs.getString("flavor"));
                    food.setTasteScore(rs.getInt("taste_score"));
                    food.setValueformoneyScore(rs.getInt("valueformoney_score"));
                    food.setCityId(rs.getInt("city_id"));
                    foodList.add(food);
                }
            }
        }
        return foodList;
    }

    public List<Food> findByCityId(Integer cityId) throws SQLException {
        List<Food> foodList = new ArrayList<>();
        String sql = "select * from tb_food where city_id = ?";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, cityId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Food food = new Food();
                    food.setFoodId(rs.getInt("food_id"));
                    food.setFood(rs.getString("food"));
                    food.setFlavor(rs.getString("flavor"));
                    food.setTasteScore(rs.getInt("taste_score"));
                    food.setValueformoneyScore(rs.getInt("valueformoney_score"));
                    food.setCityId(rs.getInt("city_id"));
                    foodList.add(food);
                }
            }
        }
        return foodList;
    }

    public int insert(Food f) throws SQLException {
        String sql = "insert into tb_food(food, flavor, taste_score, valueformoney_score, city_id) values(?,?,?,?,?)";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, f.getFood());
            ps.setString(2, f.getFlavor());
            ps.setInt(3, f.getTasteScore());
            ps.setInt(4, f.getValueformoneyScore());
            ps.setInt(5, f.getCityId());
            return ps.executeUpdate();
        }
    }

    public int update(Food f) throws SQLException {
        String sql = "update tb_food set food = ?, flavor = ?, taste_score = ?, valueformoney_score = ?, city_id = ? where food_id = ?";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, f.getFood());
            ps.setString(2, f.getFlavor());
            ps.setInt(3, f.getTasteScore());
            ps.setInt(4, f.getValueformoneyScore());
            ps.setInt(5, f.getCityId());
            ps.setInt(6, f.getFoodId());
            return ps.executeUpdate();
        }
    }

    public int deleteById(Integer id) throws SQLException {
        String sql = "delete from tb_food where food_id = ?";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate();
        }
    }
}