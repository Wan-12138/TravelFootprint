package com.travel.ui;

import com.travel.entity.Province;
import com.travel.service.CityService;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CityMenu {

    private final Scanner scanner;
    private final CityService cityService;

    public CityMenu(Scanner scanner, CityService cityService) {
        this.scanner = scanner;
        this.cityService = cityService;
    }

    public void show(){
        while (true) {
            System.out.println("=====欢迎使用城市查询功能,请输入数字选择如下功能=====");
            System.out.println("1.查询你已经去过的城市及省份");
            System.out.println("2.查询你未去过的省份");
            System.out.println("0.返回主菜单");
            String choice = scanner.nextLine().trim();
            if ("0".equals(choice)) {
                return;
            }
            try {
                switch (choice) {
                    case "1":
                        findVisitedCityWithProvince();
                        break;
                    case "2":
                        findNotVisitedProvince();
                        break;
                    default:
                        System.out.println("请输入正确的数字！");
                        continue;
                }
                System.out.println("\n----------------------------------");
                System.out.println("操作完成！按 0 返回主菜单，按其他任意键继续当前功能...");
            } catch (Exception e) {
                System.out.println("fail to operate!"+e.getMessage());
            }

            String next = scanner.nextLine().trim();
            if ("0".equals(next)) {
                return; // 用户按了0，跳出循环，回到最外层的主菜单
            }
        }
    }

    private void findVisitedCityWithProvince() throws Exception {
        Map<String, List<String>> visitedprovincesAndCities = cityService.getVisitedprovincesAndCities();

        for (Map.Entry<String, List<String>> entry : visitedprovincesAndCities.entrySet()) {
            String province = entry.getKey();
            List<String> cities = entry.getValue();

            // 把 List 里的城市名用 "、" 连起来打印，比如：大连、沈阳
            System.out.println("👉 【" + province + "】: " + String.join("、", cities));
        }
    }

    private void findNotVisitedProvince() throws SQLException {
        /**
         * 逻辑：先遍历Travelid，取得所有cityid放到Set去重
         *      然后遍历所有cityid获得所有provinceid
         *      遍历所有省份，把provinceid里没有的加入到新List中
         */

        List<Province> notVisitedProvinceList = cityService.notVisitedProvince();
        for (Province province : notVisitedProvinceList) {
            System.out.println(province.getProvinceId()+" "+province.getProvince());
        }

    }
}
