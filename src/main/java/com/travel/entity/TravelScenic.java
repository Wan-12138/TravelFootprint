package com.travel.entity;

import java.sql.Date;
import java.time.LocalDate;

public class TravelScenic {
    private Integer tsId;
    private Integer travelId;
    private Integer scenicId;
    private LocalDate scenicVisitDate;
    private Integer scenicConvenienceScore;
    private Integer scenicValueformoneyScore;
    private Integer scenicComfortScore;
    private Integer scenicExperienceScore;

    public TravelScenic() {
    }

    public TravelScenic(Integer tsId, Integer travelId, Integer scenicId,LocalDate scenicVisitDate, Integer scenicConvenienceScore, Integer scenicValueformoneyScore, Integer scenicComfortScore, Integer scenicExperienceScore) {
        this.tsId = tsId;
        this.travelId = travelId;
        this.scenicId = scenicId;
        this.scenicVisitDate = scenicVisitDate;
        this.scenicConvenienceScore = scenicConvenienceScore;
        this.scenicValueformoneyScore = scenicValueformoneyScore;
        this.scenicComfortScore = scenicComfortScore;
        this.scenicExperienceScore = scenicExperienceScore;
    }

    public Integer getTsId() {
        return tsId;
    }

    public void setTsId(Integer tsId) {
        this.tsId = tsId;
    }

    public Integer getTravelId() {
        return travelId;
    }

    public void setTravelId(Integer travelId) {
        this.travelId = travelId;
    }

    public Integer getScenicId() {
        return scenicId;
    }

    public void setScenicId(Integer scenicId) {
        this.scenicId = scenicId;
    }

    public LocalDate getScenicVisitDate() {
        return scenicVisitDate;
    }

    public void setScenicVisitDate(LocalDate scenicVisitDate) {
        this.scenicVisitDate = scenicVisitDate;
    }

    public Integer getScenicConvenienceScore() {
        return scenicConvenienceScore;
    }

    public void setScenicConvenienceScore(Integer scenicConvenienceScore) {
        this.scenicConvenienceScore = scenicConvenienceScore;
    }

    public Integer getScenicValueformoneyScore() {
        return scenicValueformoneyScore;
    }

    public void setScenicValueformoneyScore(Integer scenicValueformoneyScore) {
        this.scenicValueformoneyScore = scenicValueformoneyScore;
    }

    public Integer getScenicComfortScore() {
        return scenicComfortScore;
    }

    public void setScenicComfortScore(Integer scenicComfortScore) {
        this.scenicComfortScore = scenicComfortScore;
    }

    public Integer getScenicExperienceScore() {
        return scenicExperienceScore;
    }

    public void setScenicExperienceScore(Integer scenicExperienceScore) {
        this.scenicExperienceScore = scenicExperienceScore;
    }


    @Override
    public String toString() {
        return "TravelScenic{" +
                "tsId=" + tsId +
                ", travelId=" + travelId +
                ", scenicId=" + scenicId +
                ", scenicVisitDate=" + scenicVisitDate +
                ", scenicConvenienceScore=" + scenicConvenienceScore +
                ", scenicValueformoneyScore=" + scenicValueformoneyScore +
                ", scenicComfortScore=" + scenicComfortScore +
                ", scenicExperienceScore=" + scenicExperienceScore +
                '}';
    }

}