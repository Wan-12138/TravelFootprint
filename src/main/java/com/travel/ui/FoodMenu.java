package com.travel.ui;

import com.travel.entity.City;
import com.travel.entity.Food;
import com.travel.service.FoodService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class FoodMenu {
    private final Scanner scanner;
    private final FoodService foodService;

    public FoodMenu(Scanner scanner, FoodService foodService) {
        this.scanner = scanner;
        this.foodService = foodService;
    }

    public void show(){
        while (true) {
            System.out.println("=====欢迎使用美食管理功能，请输入数字选择如下功能=====");
            System.out.println("1.查询美食");
            System.out.println("2.添加美食");
            System.out.println("3.修改美食");
            System.out.println("4.删除美食");
            System.out.println("0.返回主菜单");
            String secondChoice = scanner.nextLine().trim();
            if (secondChoice.equals("0")){
                return;
            }
            try {
                switch (secondChoice) {
                    case "1":
                        findAllFoods();
                        break;
                    case "2":
                        insertFoodWithCity();
                        break;
                    case "3":
                        updateFood();
                        break;
                    case "4":
                        deleteFood();
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
    private void findAllFoods() throws SQLException {
        List<String> allFoodWithCity = foodService.findAllFoodWithCity();
        for (String s : allFoodWithCity) {
            System.out.println(s);
        }
    }

    private void insertFoodWithCity() throws SQLException{
        System.out.println("input the food name");
        String foodName = scanner.nextLine().trim();
        System.out.println("input the flavor name");
        String flavor = scanner.nextLine().trim();
        System.out.println("input the taste score(0-5,Integer)");
        Integer tasteScore = Integer.parseInt(scanner.nextLine().trim());
        if (tasteScore < 0 || tasteScore > 5) {
            throw new IllegalArgumentException(
                    "taste score must between 0 and 5"
            );
        }
        System.out.println("input the value for money score(0-5,Integer)");
        Integer valueformoneyScore = Integer.parseInt(scanner.nextLine().trim());
        if (valueformoneyScore < 0 || valueformoneyScore > 5) {
            throw new IllegalArgumentException(
                    "score must between 0 and 5"
            );
        }
        System.out.println("input the city");
        String cityName = scanner.nextLine().trim();

        foodService.insertFoodWithCityService(foodName,flavor,tasteScore,valueformoneyScore,cityName);
        System.out.println("successfully insert a food with city");


    }

    private void updateFood() throws SQLException{
        System.out.println("input the city name where the food you want to update located:");
        String cityName = scanner.nextLine().trim();
        List<Food> foodList = foodService.getFoodByCityName(cityName);
        System.out.println("the food in this city are as follows");
        for (Food food : foodList) {
            System.out.println("ID: "+food.getFoodId()+" foodName: "+food.getFood()+" taste score: "+food.getTasteScore()+" value for money score:"+food.getValueformoneyScore()+" city: "+cityName);
        }
        System.out.println("input the ID you want to update!");
        String foodIDStr = scanner.nextLine().trim();
        Integer foodID = Integer.parseInt(foodIDStr);

        Food food = foodService.findFoodWithID(foodID);
        if (food == null) {
            System.out.println("❌ 找不到 ID 为 " + foodID + " 的美食！");
            return;
        }
        System.out.println("input new food name: ");
        String foodName = scanner.nextLine().trim();
        System.out.println("input new flavor: ");
        String flavor = scanner.nextLine().trim();
        System.out.println("input new taste score: ");
        Integer tasteScore = Integer.parseInt(scanner.nextLine().trim());
        if (tasteScore < 0 || tasteScore > 5) {
            throw new IllegalArgumentException(
                    "score must between 0 and 5"
            );
        }
        System.out.println("input new value for money score");
        Integer valueformoneyScore = Integer.parseInt(scanner.nextLine().trim());
        if (valueformoneyScore < 0 || valueformoneyScore > 5) {
            throw new IllegalArgumentException(
                    "score must between 0 and 5"
            );
        }
        System.out.println("input new city name");
        String newCityName = scanner.nextLine().trim();

        food.setFood(foodName);
        food.setFlavor(flavor);
        food.setTasteScore(tasteScore);
        food.setValueformoneyScore(valueformoneyScore);
        foodService.updateFoodWithCity(food,newCityName);

    }

    private void deleteFood() throws SQLException {
        System.out.println("input the food name you want to delete:");
        String foodName = scanner.nextLine().trim();
        List<Food> foodListWithName = foodService.findFoodWithName(foodName);
        if (foodListWithName.isEmpty()) {
            System.out.println("❌ 没有找到名字包含【" + foodName + "】的美食。");
            return;
        }
        for (Food food : foodListWithName) {
            System.out.println("ID: "+food.getFoodId()+" foodName: "+food.getFood()+" taste score: "+food.getTasteScore()+" value for money score:"+food.getValueformoneyScore());
        }
        System.out.println("input the ID you want to delete!");
        String foodIDStr = scanner.nextLine().trim();
        Integer foodID = Integer.parseInt(foodIDStr);
        System.out.println("confirm whether to delete,if you want,click “y” and enter");
        System.out.println("if you do other operate,delete will cancel");
        String confirm = scanner.nextLine().trim();
        if(confirm.equals("y")) {
            foodService.deleteById(foodID);
        }else {
            System.out.println("delete is cancelled");
        }

    }
}
