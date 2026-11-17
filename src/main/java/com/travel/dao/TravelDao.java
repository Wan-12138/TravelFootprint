package com.travel.dao;

import com.travel.entity.Travel;
import com.travel.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TravelDao {

    public List<Travel> findAll() throws SQLException {
        List<Travel> list = new ArrayList<>();
        String sql = "select * from tb_travel";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Travel t = new Travel();
                t.setTravelId(rs.getInt("travel_id"));
                t.setBeginDate(rs.getDate("begin_date").toLocalDate());
                t.setEndDate(rs.getDate("end_date").toLocalDate());
                t.setConvenienceScore(rs.getInt("convenience_score"));
                t.setFoodScore(rs.getInt("food_score"));
                t.setExperienceScore(rs.getInt("experience_score"));
                list.add(t);
            }
        }
        return list;
    }

    public Travel findById(int id) throws SQLException {
        String sql = "select * from tb_travel where travel_id = ?";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Travel t = new Travel();
                    t.setTravelId(rs.getInt("travel_id"));
                    t.setBeginDate(rs.getDate("begin_date").toLocalDate());
                    t.setEndDate(rs.getDate("end_date").toLocalDate());
                    t.setConvenienceScore(rs.getInt("convenience_score"));
                    t.setFoodScore(rs.getInt("food_score"));
                    t.setExperienceScore(rs.getInt("experience_score"));
                    return t;
                }
            }
        }
        return null;
    }

    public int insert(Travel t) throws SQLException {
        String sql = "insert into tb_travel(begin_date, end_date, convenience_score, food_score, experience_score) values(?,?,?,?,?)";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setObject(1, t.getBeginDate());
            ps.setObject(2, t.getEndDate());
            ps.setInt(3, t.getConvenienceScore());
            ps.setInt(4, t.getFoodScore());
            ps.setInt(5, t.getExperienceScore());
            return ps.executeUpdate();
        }
    }

    public int insert(Travel t,Connection conn) throws SQLException {
        String sql = "insert into tb_travel(begin_date, end_date, convenience_score, food_score, experience_score) values(?,?,?,?,?)";
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setObject(1, t.getBeginDate());
            ps.setObject(2, t.getEndDate());
            ps.setInt(3, t.getConvenienceScore());
            ps.setInt(4, t.getFoodScore());
            ps.setInt(5, t.getExperienceScore());
            int rows = ps.executeUpdate();
            try(ResultSet rs = ps.getGeneratedKeys()) {
                if(rs.next()){
                    t.setTravelId(rs.getInt(1));
                }
            }
            return rows;
        }
    }


    public int update(Travel t) throws SQLException {
        String sql = "update tb_travel set begin_date = ?, end_date = ?, convenience_score = ?, food_score = ?, experience_score = ? where travel_id = ?";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setObject(1, t.getBeginDate());
            ps.setObject(2, t.getEndDate());
            ps.setInt(3, t.getConvenienceScore());
            ps.setInt(4, t.getFoodScore());
            ps.setInt(5, t.getExperienceScore());
            ps.setInt(6, t.getTravelId());
            return ps.executeUpdate();
        }
    }

    public int deleteById(int id) throws SQLException {
        String sql = "delete from tb_travel where travel_id = ?";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate();
        }
    }

    public int deleteById(int id,Connection conn)throws SQLException{
        String sql = "delete from tb_travel where travel_id = ?";
        try(PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1,id);
            return ps.executeUpdate();
        }
    }
}