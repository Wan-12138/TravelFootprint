package com.travel.entity;

public class ExpenseCategory {
    private Integer categoryId;
    private String category;
    public ExpenseCategory(){}

    public ExpenseCategory(Integer categoryId, String category) {
        this.categoryId = categoryId;
        this.category = category;
    }


    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "ExpenseCategory{" +
                "categoryId=" + categoryId +
                ", category='" + category + '\'' +
                '}';
    }

}
