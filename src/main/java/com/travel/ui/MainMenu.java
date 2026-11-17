package com.travel.ui;

import com.travel.service.*;

import java.util.Scanner;

public class MainMenu {
    private final Scanner scanner = new Scanner(System.in);
    private final TravelService travelService = new TravelService();
    private final CityService cityService = new CityService();
    private final ScenicService scenicService = new ScenicService();
    private final FoodService foodService = new FoodService();
    private final ExpenseService expenseService = new ExpenseService();

    private final TravelMenu  travelMenu  = new TravelMenu(scanner,travelService);
    private final CityMenu    cityMenu    = new CityMenu(scanner,cityService);
    private final ScenicMenu  scenicMenu  = new ScenicMenu(scanner,scenicService);
    private final FoodMenu    foodMenu    = new FoodMenu(scanner,foodService);
    private final ExpenseMenu expenseMenu = new ExpenseMenu(scanner,expenseService);

    public void run(){

        while (true) {
            System.out.println("===欢迎使用 TravelFootprint v1.0旅行足迹系统===");
            System.out.println("1.旅行管理");
            System.out.println("2.城市查询");
            System.out.println("3.景点管理");
            System.out.println("4.美食管理");
            System.out.println("5.花费管理");
            System.out.println("0.退出系统");
            System.out.println("请输入选项: ");
            String choice = scanner.nextLine().trim();
            switch (choice){
                case "1": travelMenu.show();  break;
                case "2": cityMenu.show();    break;
                case "3": scenicMenu.show();  break;
                case "4": foodMenu.show();    break;
                case "5": expenseMenu.show(); break;
                case "0": System.out.println("感谢使用，再见"); return;
                default : System.out.println("请输入正确的数字！");
            }
        }
    }


}
