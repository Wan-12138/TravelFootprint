package com.travel.entity;

public class City {
    private Integer cityId;
    private String city;
    private Integer provinceId;

    public City() {
    }

    public City(Integer cityId, String city, Integer provinceId) {
        this.cityId = cityId;
        this.city = city;
        this.provinceId = provinceId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    @Override
    public String toString() {
        return "City{" +
                "cityId=" + cityId +
                ", city='" + city + '\'' +
                ", provinceId=" + provinceId +
                '}';
    }
}
