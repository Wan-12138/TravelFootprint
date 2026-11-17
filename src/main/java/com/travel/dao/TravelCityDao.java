package com.travel.dao;

import com.travel.entity.TravelCity;
import com.travel.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TravelCityDao {

    public List<TravelCity> findByTravelId(Integer travelId) throws SQLException {
        List<TravelCity> list = new ArrayList<>();
        String sql = "select * from tb_travel_city where tc_travel_id = ?";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, travelId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    TravelCity tc = new TravelCity();
                    tc.setTcId(rs.getInt("tc_id"));
                    tc.setTcTravelId(rs.getInt("tc_travel_id"));
                    tc.setTcCityId(rs.getInt("tc_city_id"));
                    tc.setTcBeginDate(rs.getDate("tc_begin_date").toLocalDate());
                    tc.setTcEndDate(rs.getDate("tc_end_date").toLocalDate());
                    tc.setTcConvenienceScore(rs.getInt("tc_convenience_score"));
                    tc.setTcClimateScore(rs.getInt("tc_climate_score"));
                    tc.setTcFoodScore(rs.getInt("tc_food_score"));
                    tc.setTcExperienceScore(rs.getInt("tc_experience_score"));
                    tc.setTcValueformoneyScore(rs.getInt("tc_valueformoney_score"));
                    list.add(tc);
                }
            }
        }
        return list;
    }

    public List<TravelCity> findByCityId(Integer cityId) throws SQLException {
        List<TravelCity> list = new ArrayList<>();
        String sql = "select * from tb_travel_city where tc_city_id = ?";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, cityId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    TravelCity tc = new TravelCity();
                    tc.setTcId(rs.getInt("tc_id"));
                    tc.setTcTravelId(rs.getInt("tc_travel_id"));
                    tc.setTcCityId(rs.getInt("tc_city_id"));
                    tc.setTcBeginDate(rs.getDate("tc_begin_date").toLocalDate());
                    tc.setTcEndDate(rs.getDate("tc_end_date").toLocalDate());
                    tc.setTcConvenienceScore(rs.getInt("tc_convenience_score"));
                    tc.setTcClimateScore(rs.getInt("tc_climate_score"));
                    tc.setTcFoodScore(rs.getInt("tc_food_score"));
                    tc.setTcExperienceScore(rs.getInt("tc_experience_score"));
                    tc.setTcValueformoneyScore(rs.getInt("tc_valueformoney_score"));
                    list.add(tc);
                }
            }
        }
        return list;
    }

    public int insert(TravelCity tc) throws SQLException {
        String sql = "insert into tb_travel_city(tc_travel_id, tc_city_id, tc_begin_date, tc_end_date, tc_convenience_score, tc_climate_score, tc_food_score, tc_experience_score, tc_valueformoney_score) values(?,?,?,?,?,?,?,?,?)";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, tc.getTcTravelId());
            ps.setInt(2, tc.getTcCityId());
            ps.setObject(3, tc.getTcBeginDate());
            ps.setObject(4, tc.getTcEndDate());
            ps.setInt(5, tc.getTcConvenienceScore());
            ps.setInt(6, tc.getTcClimateScore());
            ps.setInt(7, tc.getTcFoodScore());
            ps.setInt(8, tc.getTcExperienceScore());
            ps.setInt(9, tc.getTcValueformoneyScore());
            return ps.executeUpdate();
        }
    }

    public int insert(TravelCity tc,Connection conn) throws SQLException {
        String sql = "insert into tb_travel_city(tc_travel_id, tc_city_id, tc_begin_date, tc_end_date, tc_convenience_score, tc_climate_score, tc_food_score, tc_experience_score, tc_valueformoney_score) values(?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, tc.getTcTravelId());
            ps.setInt(2, tc.getTcCityId());
            ps.setObject(3, tc.getTcBeginDate());
            ps.setObject(4, tc.getTcEndDate());
            ps.setInt(5, tc.getTcConvenienceScore());
            ps.setInt(6, tc.getTcClimateScore());
            ps.setInt(7, tc.getTcFoodScore());
            ps.setInt(8, tc.getTcExperienceScore());
            ps.setInt(9, tc.getTcValueformoneyScore());
            int rows = ps.executeUpdate();
            try(ResultSet rs = ps.getGeneratedKeys()) {
                if(rs.next()){
                    tc.setTcId(rs.getInt(1));
                }
            }

            return rows;

        }
    }


    public int update(TravelCity tc) throws SQLException {
        String sql = "update tb_travel_city set tc_travel_id=?, tc_city_id=?, tc_begin_date=?, tc_end_date=?, tc_convenience_score=?, tc_climate_score=?, tc_food_score=?, tc_experience_score=?, tc_valueformoney_score=? where tc_id=?";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, tc.getTcTravelId());
            ps.setInt(2, tc.getTcCityId());
            ps.setObject(3, tc.getTcBeginDate());
            ps.setObject(4, tc.getTcEndDate());
            ps.setInt(5, tc.getTcConvenienceScore());
            ps.setInt(6, tc.getTcClimateScore());
            ps.setInt(7, tc.getTcFoodScore());
            ps.setInt(8, tc.getTcExperienceScore());
            ps.setInt(9, tc.getTcValueformoneyScore());
            ps.setInt(10, tc.getTcId());
            return ps.executeUpdate();
        }
    }

    public int deleteById(Integer id) throws SQLException {
        String sql = "delete from tb_travel_city where tc_id = ?";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate();
        }
    }

    public int deleteByTravelId(Integer travelid,Connection conn) throws SQLException {
        String sql = "delete from tb_travel_city where tc_travel_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, travelid);
            return ps.executeUpdate();
        }
    }
}