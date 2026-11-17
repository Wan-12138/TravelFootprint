package com.travel.entity;

import java.sql.Date;
import java.time.LocalDate;

public class TravelCity {
    private Integer tcId;
    private Integer tcTravelId;
    private Integer tcCityId;
    private LocalDate tcBeginDate;
    private LocalDate tcEndDate;
    private Integer tcConvenienceScore;
    private Integer tcClimateScore;
    private Integer tcFoodScore;
    private Integer tcExperienceScore;
    private Integer tcValueformoneyScore;

    public TravelCity() {
    }

    public TravelCity(Integer tcId, Integer tcTravelId, Integer tcCityId, LocalDate tcBeginDate, LocalDate tcEndDate, Integer tcConvenienceScore, Integer tcClimateScore, Integer tcFoodScore, Integer tcExperienceScore, Integer tcValueformoneyScore) {
        this.tcId = tcId;
        this.tcTravelId = tcTravelId;
        this.tcCityId = tcCityId;
        this.tcBeginDate = tcBeginDate;
        this.tcEndDate = tcEndDate;
        this.tcConvenienceScore = tcConvenienceScore;
        this.tcClimateScore = tcClimateScore;
        this.tcFoodScore = tcFoodScore;
        this.tcExperienceScore = tcExperienceScore;
        this.tcValueformoneyScore = tcValueformoneyScore;
    }

    public Integer getTcId() {
        return tcId;
    }

    public void setTcId(Integer tcId) {
        this.tcId = tcId;
    }

    public Integer getTcTravelId() {
        return tcTravelId;
    }

    public void setTcTravelId(Integer tcTravelId) {
        this.tcTravelId = tcTravelId;
    }

    public Integer getTcCityId() {
        return tcCityId;
    }

    public void setTcCityId(Integer tcCityId) {
        this.tcCityId = tcCityId;
    }

    public LocalDate getTcBeginDate() {
        return tcBeginDate;
    }

    public void setTcBeginDate(LocalDate tcBeginDate) {
        this.tcBeginDate = tcBeginDate;
    }

    public LocalDate getTcEndDate() {
        return tcEndDate;
    }

    public void setTcEndDate(LocalDate tcEndDate) {
        this.tcEndDate = tcEndDate;
    }

    public Integer getTcConvenienceScore() {
        return tcConvenienceScore;
    }

    public void setTcConvenienceScore(Integer tcConvenienceScore) {
        this.tcConvenienceScore = tcConvenienceScore;
    }

    public Integer getTcClimateScore() {
        return tcClimateScore;
    }

    public void setTcClimateScore(Integer tcClimateScore) {
        this.tcClimateScore = tcClimateScore;
    }

    public Integer getTcFoodScore() {
        return tcFoodScore;
    }

    public void setTcFoodScore(Integer tcFoodScore) {
        this.tcFoodScore = tcFoodScore;
    }

    public Integer getTcExperienceScore() {
        return tcExperienceScore;
    }

    public void setTcExperienceScore(Integer tcExperienceScore) {
        this.tcExperienceScore = tcExperienceScore;
    }

    public Integer getTcValueformoneyScore() {
        return tcValueformoneyScore;
    }

    public void setTcValueformoneyScore(Integer tcValueformoneyScore) {
        this.tcValueformoneyScore = tcValueformoneyScore;
    }

    @Override
    public String toString() {
        return "TravelCity{" +
                "tcId=" + tcId +
                ", tcTravelId=" + tcTravelId +
                ", tcCityId=" + tcCityId +
                ", tcBeginDate=" + tcBeginDate +
                ", tcEndDate=" + tcEndDate +
                ", tcConvenienceScore=" + tcConvenienceScore +
                ", tcClimateScore=" + tcClimateScore +
                ", tcFoodScore=" + tcFoodScore +
                ", tcExperienceScore=" + tcExperienceScore +
                ", tcValueformoneyScore=" + tcValueformoneyScore +
                '}';
    }
}