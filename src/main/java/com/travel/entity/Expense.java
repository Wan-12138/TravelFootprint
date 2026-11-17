package com.travel.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Expense {
    private Integer expenseId;
    private Integer expenseTravelId;
    private Integer expenseCityId;
    private Integer expenseCategoryId;
    private BigDecimal expenseAmount;
    private LocalDate expenseDate;
    private String expenseDescription;

    public Expense(){}

    public Expense(Integer expenseId, Integer expenseTravelId, Integer expenseCityId, Integer expenseCategoryId, BigDecimal expenseAmount, LocalDate expenseDate, String expenseDescription) {
        this.expenseId = expenseId;
        this.expenseTravelId = expenseTravelId;
        this.expenseCityId = expenseCityId;
        this.expenseCategoryId = expenseCategoryId;
        this.expenseAmount = expenseAmount;
        this.expenseDate = expenseDate;
        this.expenseDescription = expenseDescription;
    }

    public Integer getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(Integer expenseId) {
        this.expenseId = expenseId;
    }

    public Integer getExpenseTravelId() {
        return expenseTravelId;
    }

    public void setExpenseTravelId(Integer expenseTravelId) {
        this.expenseTravelId = expenseTravelId;
    }

    public Integer getExpenseCityId() {
        return expenseCityId;
    }

    public void setExpenseCityId(Integer expenseCityId) {
        this.expenseCityId = expenseCityId;
    }

    public Integer getExpenseCategoryId() {
        return expenseCategoryId;
    }

    public void setExpenseCategoryId(Integer expenseCategoryId) {
        this.expenseCategoryId = expenseCategoryId;
    }

    public BigDecimal getExpenseAmount() {
        return expenseAmount;
    }

    public void setExpenseAmount(BigDecimal expenseAmount) {
        this.expenseAmount = expenseAmount;
    }

    public LocalDate getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(LocalDate expenseDate) {
        this.expenseDate = expenseDate;
    }

    public String getExpenseDescription() {
        return expenseDescription;
    }

    public void setExpenseDescription(String expenseDescription) {
        this.expenseDescription = expenseDescription;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "expenseId=" + expenseId +
                ", expenseTravelId=" + expenseTravelId +
                ", expenseCityId=" + expenseCityId +
                ", expenseCategoryId=" + expenseCategoryId +
                ", expenseAmount=" + expenseAmount +
                ", expenseDate=" + expenseDate +
                ", expenseDescription='" + expenseDescription + '\'' +
                '}';
    }

}
