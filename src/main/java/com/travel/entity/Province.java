package com.travel.entity;

public class Province {
    private Integer provinceId;
    private String province;

    public Province(){

    }

    public Province(Integer provinceId, String province) {
        this.provinceId = provinceId;
        this.province = province;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Override
    public String toString() {
        return "Province{" +
                "provinceId=" + provinceId +
                ", province='" + province + '\'' +
                '}';
    }
}
