package com.travel.dao;

import com.travel.entity.TravelScenic;
import com.travel.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TravelScenicDao {

    public List<TravelScenic> findByTravelId(Integer travelId) throws SQLException {
        List<TravelScenic> list = new ArrayList<>();
        String sql = "select * from tb_travel_scenic where travel_id = ?";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, travelId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    TravelScenic ts = new TravelScenic();
                    ts.setTsId(rs.getInt("ts_id"));
                    ts.setTravelId(rs.getInt("travel_id"));
                    ts.setScenicId(rs.getInt("scenic_id"));
                    ts.setScenicVisitDate(rs.getDate("scenic_visit_date").toLocalDate());
                    ts.setScenicConvenienceScore(rs.getInt("scenic_convenience_score"));
                    ts.setScenicValueformoneyScore(rs.getInt("scenic_valueformoney_score"));
                    ts.setScenicComfortScore(rs.getInt("scenic_comfort_score"));
                    ts.setScenicExperienceScore(rs.getInt("scenic_experience_score"));
                    list.add(ts);
                }
            }
        }
        return list;
    }

    public List<TravelScenic> findByScenicId(Integer scenicId) throws SQLException {
        List<TravelScenic> list = new ArrayList<>();
        String sql = "select * from tb_travel_scenic where scenic_id = ?";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, scenicId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    TravelScenic ts = new TravelScenic();
                    ts.setTsId(rs.getInt("ts_id"));
                    ts.setTravelId(rs.getInt("travel_id"));
                    ts.setScenicId(rs.getInt("scenic_id"));
                    ts.setScenicVisitDate(rs.getDate("scenic_visit_date").toLocalDate());
                    ts.setScenicConvenienceScore(rs.getInt("scenic_convenience_score"));
                    ts.setScenicValueformoneyScore(rs.getInt("scenic_valueformoney_score"));
                    ts.setScenicComfortScore(rs.getInt("scenic_comfort_score"));
                    ts.setScenicExperienceScore(rs.getInt("scenic_experience_score"));
                    list.add(ts);
                }
            }
        }
        return list;
    }

    public int insert(TravelScenic ts) throws SQLException {
        String sql = "insert into tb_travel_scenic(travel_id, scenic_id, scenic_visit_date, scenic_convenience_score, scenic_valueformoney_score, scenic_comfort_score, scenic_experience_score) values(?,?,?,?,?,?,?)";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, ts.getTravelId());
            ps.setInt(2, ts.getScenicId());
            ps.setObject(3, ts.getScenicVisitDate());
            ps.setInt(4, ts.getScenicConvenienceScore());
            ps.setInt(5, ts.getScenicValueformoneyScore());
            ps.setInt(6, ts.getScenicComfortScore());
            ps.setInt(7, ts.getScenicExperienceScore());
            return ps.executeUpdate();
        }
    }

    public int insert(TravelScenic ts,Connection conn) throws SQLException {
        String sql = "insert into tb_travel_scenic(travel_id, scenic_id, scenic_visit_date, scenic_convenience_score, scenic_valueformoney_score, scenic_comfort_score, scenic_experience_score) values(?,?,?,?,?,?,?)";
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, ts.getTravelId());
            ps.setInt(2, ts.getScenicId());
            ps.setObject(3, ts.getScenicVisitDate());
            ps.setInt(4, ts.getScenicConvenienceScore());
            ps.setInt(5, ts.getScenicValueformoneyScore());
            ps.setInt(6, ts.getScenicComfortScore());
            ps.setInt(7, ts.getScenicExperienceScore());
            int rows = ps.executeUpdate();
            try(ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()){
                    ts.setTsId(rs.getInt(1));
                }
            }
            return rows;
        }
    }

    public int update(TravelScenic ts) throws SQLException {
        String sql = "update tb_travel_scenic set travel_id=?, scenic_id=?, scenic_visit_date=?, scenic_convenience_score=?, scenic_valueformoney_score=?, scenic_comfort_score=?, scenic_experience_score=? where ts_id=?";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, ts.getTravelId());
            ps.setInt(2, ts.getScenicId());
            ps.setObject(3, ts.getScenicVisitDate());
            ps.setInt(4, ts.getScenicConvenienceScore());
            ps.setInt(5, ts.getScenicValueformoneyScore());
            ps.setInt(6, ts.getScenicComfortScore());
            ps.setInt(7, ts.getScenicExperienceScore());
            ps.setInt(8, ts.getTsId());
            return ps.executeUpdate();
        }
    }

    public int deleteById(Integer id) throws SQLException {
        String sql = "delete from tb_travel_scenic where ts_id = ?";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate();
        }
    }

    public int deleteByTravelId(Integer travelid,Connection conn) throws SQLException {
        String sql = "delete from tb_travel_scenic where travel_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, travelid);
            return ps.executeUpdate();
        }
    }

}