package com.travel.dao;

import com.travel.entity.ExpenseCategory;
import com.travel.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExpenseCategoryDao {

    public List<ExpenseCategory> findAll() throws SQLException {
        List<ExpenseCategory> ECList = new ArrayList<>();
        String sql = "select * from tb_expense_category";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                ExpenseCategory ec = new ExpenseCategory();
                ec.setCategoryId(rs.getInt("category_id"));
                ec.setCategory(rs.getString("category"));
                ECList.add(ec);
            }
        }
        return ECList;
    }

    public ExpenseCategory findById(int id) throws SQLException {
        String sql = "select * from tb_expense_category where category_id = ?";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ExpenseCategory ec = new ExpenseCategory();
                    ec.setCategoryId(rs.getInt("category_id"));
                    ec.setCategory(rs.getString("category"));
                    return ec;
                }
            }
        }
        return null;
    }

    public ExpenseCategory findByName(String name) throws SQLException {
        String sql = "select * from tb_expense_category where category = ?";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ExpenseCategory ec = new ExpenseCategory();
                    ec.setCategoryId(rs.getInt("category_id"));
                    ec.setCategory(rs.getString("category"));
                    return ec;
                }
            }
        }
        return null;
    }

    public int insert(ExpenseCategory ec) throws SQLException {
        String sql = "insert into tb_expense_category(category) values(?)";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, ec.getCategory());
            return ps.executeUpdate();
        }
    }

    public int update(ExpenseCategory ec) throws SQLException {
        String sql = "update tb_expense_category set category = ? where category_id = ?";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, ec.getCategory());
            ps.setInt(2, ec.getCategoryId());
            return ps.executeUpdate();
        }
    }

    public int deleteById(int id) throws SQLException {
        String sql = "delete from tb_expense_category where category_id = ?";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate();
        }
    }
}