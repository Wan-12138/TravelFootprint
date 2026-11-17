package com.travel.entity;

public class Food {
    private Integer foodId;
    private String food;
    private String flavor;
    private Integer tasteScore;
    private Integer valueformoneyScore;
    private Integer cityId;

    public Food(){

    }

    public Food(Integer foodId, String food, String flavor, Integer tasteScore, Integer valueformoneyScore, Integer cityId) {
        this.foodId = foodId;
        this.food = food;
        this.flavor = flavor;
        this.tasteScore = tasteScore;
        this.valueformoneyScore = valueformoneyScore;
        this.cityId = cityId;
    }

    public Integer getFoodId() {
        return foodId;
    }

    public void setFoodId(Integer foodId) {
        this.foodId = foodId;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public Integer getTasteScore() {
        return tasteScore;
    }

    public void setTasteScore(Integer tasteScore) {
        this.tasteScore = tasteScore;
    }

    public Integer getValueformoneyScore() {
        return valueformoneyScore;
    }

    public void setValueformoneyScore(Integer valueformoneyScore) {
        this.valueformoneyScore = valueformoneyScore;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    @Override
    public String toString() {
        return "Food{" +
                "foodId=" + foodId +
                ", food='" + food + '\'' +
                ", flavor='" + flavor + '\'' +
                ", tasteScore=" + tasteScore +
                ", valueformoneyScore=" + valueformoneyScore +
                ", cityId=" + cityId +
                '}';
    }
}
