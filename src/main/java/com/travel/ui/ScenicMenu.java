package com.travel.ui;

import com.travel.dao.CityDao;
import com.travel.dao.ScenicDao;
import com.travel.entity.City;
import com.travel.entity.Scenic;
import com.travel.service.ScenicService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ScenicMenu {
    private final Scanner scanner;
    private final ScenicService scenicService;

    public ScenicMenu(Scanner scanner, ScenicService scenicService) {
        this.scanner = scanner;
        this.scenicService = scenicService;
    }

    public void show(){
        while (true) {
            System.out.println("=====欢迎使用景点管理功能，请输入数字选择如下功能=====");
            System.out.println("1.查询景点");
            System.out.println("2.添加景点");
            System.out.println("3.修改景点");
            System.out.println("4.删除景点");
            System.out.println("0.返回主菜单");
            String secondChoice = scanner.nextLine().trim();
            if (secondChoice.equals("0")){
                return;
            }
            try {
                switch (secondChoice) {
                    case "1":
                        findAllScenics();
                        break;
                    case "2":
                        insertScenic();
                        break;
                    case "3":
                        updateScenic();
                        break;
                    case "4":
                        deleteScenicByName();
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
    private void findAllScenics() throws SQLException {
        List<String> allScenicWithCityName = scenicService.findAllScenicWithCityName();
        for (String s : allScenicWithCityName) {
            System.out.println(s);
        }
    }

    private void insertScenic() throws SQLException{
        System.out.println("input scenic:");
        String scenicName = scanner.nextLine().trim();
        System.out.println("input type:");
        String type = scanner.nextLine().trim();
        System.out.println("input cityName:");
        String cityName = scanner.nextLine().trim();

        scenicService.insertScenicWithCityName(scenicName,type,cityName);
        System.out.println("successfully insert a scenic with city");
    }


    private void updateScenic() throws SQLException{
        System.out.println("input the scenic's city name you want to update");
        String cityName = scanner.nextLine().trim();

        List<Scenic> scenicByCityNameList = scenicService.getScenicByCityName(cityName);
        System.out.println("这个城市拥有的景点列表如下");

        for (Scenic scenic : scenicByCityNameList) {
            System.out.println("scenic id:"+scenic.getScenicId()+"scenic name:"+scenic.getScenic()+
                                "type"+scenic.getType());
        }

        System.out.println("input the scenic id you want to update");
        String idStr = scanner.nextLine().trim();
        if("0".equals(idStr)){
            System.out.println("that update is cancelled!");
            return;
        }

        Integer scenicId = Integer.parseInt(idStr);

        Scenic scenicById = scenicService.getScenicById(scenicId);
        if(scenicById == null){
            System.out.println("this scenic id is not exsit in the list");
        }

        System.out.println("input new scenic name");
        String scenicName = scanner.nextLine().trim();
        System.out.println("input new scenic type");
        String type = scanner.nextLine().trim();

        if (!scenicName.isEmpty()) {
            scenicById.setScenic(scenicName);
        }
        if (!type.isEmpty()) {
            scenicById.setType(type);
        }
        scenicService.updateScenicByName(scenicById);
    }

    private void deleteScenicByName() throws SQLException{
        System.out.println("input the scenic name you want to delete");
        String scenicName = scanner.nextLine().trim();
        List<Scenic> scenicListFindByName = scenicService.findByScenicName(scenicName);
        // 坑1：没查到结果直接返回
        if (scenicListFindByName.isEmpty()) {
            System.out.println("❌ 没有找到叫【" + scenicName + "】的景点。");
            return;
        }

        System.out.println("the scenic list you want to delete are as follow");

        for (Scenic scenic : scenicListFindByName) {
            City city = scenicService.findCityById(scenic.getScenicCityId());
            // 坑2：防 NPE（空指针）
            String cityName = (city != null) ? city.getCity() : "未知城市";
            System.out.println("ID:"+scenic.getScenicId()+"scenic name:"+scenic.getScenic()+"type"+scenic.getType()+"city"+cityName);
        }

        System.out.println("input the ID you want delete!!!");
        String scenicIdStr = scanner.nextLine().trim();
        if ("0".equals(scenicIdStr)) return;

        // 不使用 try-catch 嵌套，直接正则或者简单拦截字母
        if (!scenicIdStr.matches("\\d+")) {
            System.out.println("❌ ID 必须是纯数字！");
            return;
        }
        int scenicId = Integer.parseInt(scenicIdStr);
        System.out.println("confirm whether delete or not,if you want,input y!!!");
        String confirm = scanner.nextLine().trim();
        if(confirm.equals("y")) {
            scenicService.deleteScenic(scenicId);
        }else {
            System.out.println("delete is cancelled!");
        }
    }
}
