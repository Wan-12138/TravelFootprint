package com.travel.entity;

public class Scenic {
    private Integer scenicId;
    private String scenic;
    private String type;
    private Integer scenicCityId;

    public Scenic(){}

    public Scenic(Integer scenicId, String scenic, String type, Integer scenicCityId) {
        this.scenicId = scenicId;
        this.scenic = scenic;
        this.type = type;
        this.scenicCityId = scenicCityId;
    }

    public Integer getScenicId() {
        return scenicId;
    }

    public void setScenicId(Integer scenicId) {
        this.scenicId = scenicId;
    }

    public String getScenic() {
        return scenic;
    }

    public void setScenic(String scenic) {
        this.scenic = scenic;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getScenicCityId() {
        return scenicCityId;
    }

    public void setScenicCityId(Integer scenicCityId) {
        this.scenicCityId = scenicCityId;
    }

    @Override
    public String toString() {
        return "Scenic{" +
                "scenicId=" + scenicId +
                ", scenic='" + scenic + '\'' +
                ", type='" + type + '\'' +
                ", scenicCityId=" + scenicCityId +
                '}';
    }
}
