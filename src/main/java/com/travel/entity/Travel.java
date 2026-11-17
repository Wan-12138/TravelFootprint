package com.travel.entity;


import java.sql.Date;
import java.time.LocalDate;


public class Travel {


    private Integer travelId;

    private LocalDate beginDate;

    private LocalDate endDate;

    private Integer convenienceScore;

    private Integer foodScore;

    private Integer experienceScore;


    public Integer getTravelId() {
        return travelId;
    }

    public Travel(){

    }

    public Travel(Integer travelId, LocalDate beginDate, LocalDate endDate, Integer convenienceScore, Integer foodScore, Integer experienceScore) {
        this.travelId = travelId;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.convenienceScore = convenienceScore;
        this.foodScore = foodScore;
        this.experienceScore = experienceScore;
    }

    public void setTravelId(int travelId) {
        this.travelId = travelId;
    }


    public LocalDate getBeginDate() {
        return beginDate;
    }


    public void setBeginDate(LocalDate beginDate) {
        this.beginDate = beginDate;
    }


    public LocalDate getEndDate() {
        return endDate;
    }


    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Integer getConvenienceScore() {
        return convenienceScore;
    }

    public void setConvenienceScore(Integer convenienceScore) {
        this.convenienceScore = convenienceScore;
    }

    public Integer getFoodScore() {
        return foodScore;
    }

    public void setFoodScore(Integer foodScore) {
        this.foodScore = foodScore;
    }

    public Integer getExperienceScore() {
        return experienceScore;
    }

    public void setExperienceScore(Integer experienceScore) {
        this.experienceScore = experienceScore;
    }

    @Override
    public String toString() {
        return "Travel{" +
                "travelId=" + travelId +
                ", beginDate=" + beginDate +
                ", endDate=" + endDate +
                ", convenienceScore=" + convenienceScore +
                ", foodScore=" + foodScore +
                ", experienceScore=" + experienceScore +
                '}';
    }
}