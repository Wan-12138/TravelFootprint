package com.travel.dao;

import com.travel.entity.Scenic;
import com.travel.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ScenicDao {

    public List<Scenic> findAll() throws SQLException {
        List<Scenic> scenicList = new ArrayList<>();
        String sql = "select * from tb_scenic order by scenic_id";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Scenic scenic = new Scenic();
                scenic.setScenicId(rs.getInt("scenic_id"));
                scenic.setScenic(rs.getString("scenic"));
                scenic.setType(rs.getString("type"));
                scenic.setScenicCityId(rs.getInt("scenic_city_id"));
                scenicList.add(scenic);
            }
        }
        return scenicList;
    }

    public Scenic findById(Integer id) throws SQLException {
        String sql = "select * from tb_scenic where scenic_id = ?";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Scenic scenic = new Scenic();
                    scenic.setScenicId(rs.getInt("scenic_id"));
                    scenic.setScenic(rs.getString("scenic"));
                    scenic.setType(rs.getString("type"));
                    scenic.setScenicCityId(rs.getInt("scenic_city_id"));
                    return scenic;
                }
            }
        }
        return null;
    }

    public List<Scenic> findByName(String name) throws SQLException {
        List<Scenic> scenicList = new ArrayList<>();
        String sql = "select * from tb_scenic where scenic like ?";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + name + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Scenic scenic = new Scenic();
                    scenic.setScenicId(rs.getInt("scenic_id"));
                    scenic.setScenic(rs.getString("scenic"));
                    scenic.setType(rs.getString("type"));
                    scenic.setScenicCityId(rs.getInt("scenic_city_id"));
                    scenicList.add(scenic);
                }
            }
        }
        return scenicList;
    }

    // 根据城市ID查询景点
    public List<Scenic> findByCityId(Integer cityId) throws SQLException {
        List<Scenic> list = new ArrayList<>();
        String sql = "select * from tb_scenic where scenic_city_id = ?";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, cityId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Scenic s = new Scenic();
                    s.setScenicId(rs.getInt("scenic_id"));
                    s.setScenic(rs.getString("scenic"));
                    s.setType(rs.getString("type"));
                    s.setScenicCityId(rs.getInt("scenic_city_id"));
                    list.add(s);
                }
            }
        }
        return list;
    }

    public int insert(Scenic s) throws SQLException {
        String sql = "insert into tb_scenic(scenic, type, scenic_city_id) values(?,?,?)";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, s.getScenic());
            ps.setString(2, s.getType());
            ps.setInt(3, s.getScenicCityId());
            return ps.executeUpdate();
        }
    }

    public int update(Scenic s) throws SQLException {
        String sql = "update tb_scenic set scenic = ?, type = ?, scenic_city_id = ? where scenic_id = ?";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, s.getScenic());
            ps.setString(2, s.getType());
            ps.setInt(3, s.getScenicCityId());
            ps.setInt(4, s.getScenicId());
            return ps.executeUpdate();
        }
    }

    public int deleteByid(Integer id) throws SQLException {
        String sql = "delete from tb_scenic where scenic_id = ?";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1,id);
            return ps.executeUpdate();
        }
    }
}