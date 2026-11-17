package com.travel.dao;

import com.travel.entity.City;
import com.travel.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CityDao {

    public List<City> findAll() throws SQLException {
        List<City> list = new ArrayList<>();
        String sql = "select * from tb_city";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                City city = new City();
                city.setCityId(rs.getInt("city_id"));
                city.setCity(rs.getString("city"));
                city.setProvinceId(rs.getInt("province_id"));
                list.add(city);
            }
        }
        return list;
    }

    public City findById(int id) throws SQLException {
        String sql = "select * from tb_city where city_id = ?";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    City city = new City();
                    city.setCityId(rs.getInt("city_id"));
                    city.setCity(rs.getString("city"));
                    city.setProvinceId(rs.getInt("province_id"));
                    return city;
                }
            }
        }
        return null;
    }

    public List<City> findByProvinceId(int id) throws SQLException {
        List<City> cityList = new ArrayList<>();
        String sql = "select * from tb_city where province_id = ?";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    City city = new City();
                    city.setCityId(rs.getInt("city_id"));
                    city.setCity(rs.getString("city"));
                    city.setProvinceId(rs.getInt("province_id"));
                    cityList.add(city);
                }
            }
        }
        return cityList;
    }

    public City findByName(String cityName) throws SQLException {
        String sql = "select * from tb_city where city = ?";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            // 设置参数
            ps.setString(1, cityName);

            try (ResultSet rs = ps.executeQuery()) {
                // 因为你的建表语句里 city 是 unique（唯一的），所以最多只可能有一条
                if (rs.next()) {
                    City city = new City();
                    city.setCityId(rs.getInt("city_id"));
                    city.setCity(rs.getString("city"));
                    city.setProvinceId(rs.getInt("province_id"));
                    return city;
                }
            }
        }
        return null; // 没查到就返回 null
    }

}