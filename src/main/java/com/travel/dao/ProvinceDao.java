package com.travel.dao;

import com.travel.entity.Province;
import com.travel.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 * findAll findById findByName
 * insert update deleteById
 */
public class ProvinceDao {

    public List<Province> findAll() throws SQLException {
        List<Province> provinceList = new ArrayList<>();
        String sql = "select * from tb_province order by province_id";
        /**
         * try with resources 是为了自动关资源
         * catch 是为了处理异常
         */
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Province province = new Province();
                province.setProvinceId(rs.getInt("province_id"));
                province.setProvince(rs.getString("province"));
                provinceList.add(province);
            }
        }
        return provinceList;
    }

    public Province findById(int id) throws SQLException {
        String sql = "select * from tb_province where province_id = ?";

        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Province province = new Province();
                    province.setProvinceId(rs.getInt("province_id"));
                    province.setProvince(rs.getString("province"));
                    return province;
                }
            }
        }
        return null;
    }

    public Province findByName(String name) throws Exception{
        String sql = "select province_id,province from tb_province where province = ?";
        try(Connection conn = JDBCUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1,name);
            try(ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Province province = new Province();
                    province.setProvinceId(rs.getInt("province_id"));
                    province.setProvince(rs.getString("province"));
                    return province;
                }
            }
        }
        return null;
    }


}