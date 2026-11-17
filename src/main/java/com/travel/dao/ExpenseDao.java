package com.travel.dao;

import com.travel.entity.Expense;
import com.travel.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExpenseDao {

    public List<Expense> findAll() throws SQLException {
        List<Expense> expenseList = new ArrayList<>();
        String sql = "select * from tb_expense";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Expense expense = new Expense();
                expense.setExpenseId(rs.getInt("expense_id"));
                expense.setExpenseTravelId(rs.getInt("expense_travel_id"));
                expense.setExpenseCityId(rs.getObject("expense_city_id",Integer.class));
                expense.setExpenseCategoryId(rs.getInt("expense_category_id"));
                expense.setExpenseAmount(rs.getBigDecimal("expense_amount"));
                expense.setExpenseDate(rs.getDate("expense_date").toLocalDate());
                expense.setExpenseDescription(rs.getString("expense_description"));
                expenseList.add(expense);
            }
        }
        return expenseList;
    }
    public Expense findByExpenseId(Integer id) throws SQLException {
        Expense expense = new Expense();
        String sql = "select * from tb_expense where expense_id = ?";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    expense.setExpenseId(rs.getInt("expense_id"));
                    expense.setExpenseTravelId(rs.getInt("expense_travel_id"));
                    expense.setExpenseCityId(rs.getObject("expense_city_id",Integer.class));
                    expense.setExpenseCategoryId(rs.getInt("expense_category_id"));
                    expense.setExpenseAmount(rs.getBigDecimal("expense_amount"));
                    expense.setExpenseDate(rs.getDate("expense_date").toLocalDate());
                    expense.setExpenseDescription(rs.getString("expense_description"));
                    return expense;
                }
            }
        }
        return null;

    }


    public List<Expense> findByTravelId(Integer id) throws SQLException {
        List<Expense> expenseList = new ArrayList<>();
        String sql = "select * from tb_expense where expense_travel_id = ?";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Expense expense = new Expense();
                    expense.setExpenseId(rs.getInt("expense_id"));
                    expense.setExpenseTravelId(rs.getInt("expense_travel_id"));
                    expense.setExpenseCityId(rs.getObject("expense_city_id",Integer.class));
                    expense.setExpenseCategoryId(rs.getInt("expense_category_id"));
                    expense.setExpenseAmount(rs.getBigDecimal("expense_amount"));
                    expense.setExpenseDate(rs.getDate("expense_date").toLocalDate());
                    expense.setExpenseDescription(rs.getString("expense_description"));
                    expenseList.add(expense);
                }
            }
        }
        return expenseList;
    }

    public List<Expense> findByCityId(Integer id) throws SQLException {
        List<Expense> expenseList = new ArrayList<>();
        String sql = "select * from tb_expense where expense_city_id = ?";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Expense expense = new Expense();
                    expense.setExpenseId(rs.getInt("expense_id"));
                    expense.setExpenseTravelId(rs.getInt("expense_travel_id"));
                    expense.setExpenseCityId(rs.getObject("expense_city_id",Integer.class));
                    expense.setExpenseCategoryId(rs.getInt("expense_category_id"));
                    expense.setExpenseAmount(rs.getBigDecimal("expense_amount"));
                    expense.setExpenseDate(rs.getDate("expense_date").toLocalDate());
                    expense.setExpenseDescription(rs.getString("expense_description"));
                    expenseList.add(expense);
                }
            }
        }
        return expenseList;
    }

    public List<Expense> findByCategoryId(Integer id) throws SQLException {
        List<Expense> expenseList = new ArrayList<>();
        String sql = "select * from tb_expense where expense_category_id = ?";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Expense expense = new Expense();
                    expense.setExpenseId(rs.getInt("expense_id"));
                    expense.setExpenseTravelId(rs.getInt("expense_travel_id"));
                    expense.setExpenseCityId(rs.getObject("expense_city_id",Integer.class));
                    expense.setExpenseCategoryId(rs.getInt("expense_category_id"));
                    expense.setExpenseAmount(rs.getBigDecimal("expense_amount"));
                    expense.setExpenseDate(rs.getDate("expense_date").toLocalDate());
                    expense.setExpenseDescription(rs.getString("expense_description"));
                    expenseList.add(expense);
                }
            }
        }
        return expenseList;
    }

    public int insert(Expense e) throws SQLException {
        String sql = "insert into tb_expense(expense_travel_id, expense_city_id, expense_category_id, expense_amount, expense_date, expense_description) values(?,?,?,?,?,?)";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, e.getExpenseTravelId());
            ps.setObject(2, e.getExpenseCityId());
            ps.setInt(3, e.getExpenseCategoryId());
            ps.setBigDecimal(4, e.getExpenseAmount());
            ps.setObject(5, e.getExpenseDate());
            ps.setString(6, e.getExpenseDescription());
            return ps.executeUpdate();
        }
    }

    public int insert(Expense e,Connection conn) throws SQLException {
        String sql = "insert into tb_expense(expense_travel_id, expense_city_id, expense_category_id, expense_amount, expense_date, expense_description) values(?,?,?,?,?,?)";
        try (PreparedStatement ps = conn.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, e.getExpenseTravelId());
            ps.setObject(2, e.getExpenseCityId());
            ps.setInt(3, e.getExpenseCategoryId());
            ps.setBigDecimal(4, e.getExpenseAmount());
            ps.setObject(5, e.getExpenseDate());
            ps.setString(6, e.getExpenseDescription());
            int rows = ps.executeUpdate();
            try(ResultSet rs = ps.getGeneratedKeys()) {
                if(rs.next()) {
                    e.setExpenseId(rs.getInt(1));
                }
            }
            return rows;
        }
    }

    public int update(Expense e) throws SQLException {
        String sql = "update tb_expense set expense_city_id=?, expense_category_id=?, " +
                "expense_amount=?, expense_date=?, expense_description=? where expense_id=?";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setObject(1, e.getExpenseCityId());
            ps.setInt(2, e.getExpenseCategoryId());
            ps.setBigDecimal(3, e.getExpenseAmount());
            ps.setObject(4, e.getExpenseDate());
            ps.setString(5, e.getExpenseDescription());
            ps.setInt(6, e.getExpenseId());
            return ps.executeUpdate();
        }
    }

    public int deleteById(Integer id) throws SQLException {
        String sql = "delete from tb_expense where expense_id = ?";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate();
        }
    }

    public int deleteByTravelId(Integer travelid,Connection conn) throws SQLException {
        String sql = "delete from tb_expense where expense_travel_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, travelid);
            return ps.executeUpdate();
        }
    }
}