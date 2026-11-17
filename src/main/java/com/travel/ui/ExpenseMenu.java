package com.travel.ui;

import com.travel.dao.ExpenseDao;
import com.travel.entity.City;
import com.travel.entity.Expense;
import com.travel.entity.Travel;
import com.travel.service.ExpenseService;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class ExpenseMenu {
    private final Scanner scanner;
    private final ExpenseService expenseService;

    public ExpenseMenu(Scanner scanner, ExpenseService expenseService) {
        this.scanner = scanner;
        this.expenseService = expenseService;
    }

    public void show(){
        while (true) {
            System.out.println("=====欢迎使用花费管理功能，请输入数字选择如下功能=====");
            System.out.println("1.查询花费");
            System.out.println("2.添加花费");
            System.out.println("3.修改花费");
            System.out.println("4.删除花费");
            System.out.println("0.返回主菜单");
            String secondChoice = scanner.nextLine().trim();
            if (secondChoice.equals("0")){
                return;
            }
            try {
                switch (secondChoice) {
                    case "1":
                        findAllExpenses();
                        break;
                    case "2":
                        insertExpense();
                        break;
                    case "3":
                        updateExpense();
                        break;
                    case "4":
                        deleteExpense();
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
    private void findAllExpenses() throws SQLException {
        printAllExpenseInfo();
    }

    private void insertExpense() throws SQLException{
        List<String> allInfo = expenseService.findAllExpenseWithTravelIDAndCity();
        for (String s : allInfo) {
            System.out.println(s);
        }
        System.out.println("input the travel id you want to insert where the expense at");
        String chooseIDStr = scanner.nextLine().trim();
        Integer chooseID = Integer.parseInt(chooseIDStr);

        Travel travelById = expenseService.findTravelById(chooseID);
        if(travelById == null){
            System.out.println("this travel is not exsit!!!");
            return;
        }


        System.out.println("here are all expense information this travel id have");
        List<Expense> expenseByTravelId = expenseService.findExpenseByTravelId(chooseID);
        if(expenseByTravelId.isEmpty()){
            System.out.println("the expense list of this travel is still empty");
        }
        for (Expense expense : expenseByTravelId) {
            City city = expenseService.findCityByExpenseCityId(expense.getExpenseCityId());
            String cityName = (city == null) ? "跨城/未指定" : city.getCity();
            System.out.println("travel id: "+expense.getExpenseTravelId()+" city : "+cityName+" date : "+expense.getExpenseDate());
        }
        System.out.println("now you can insert the expense!");
        System.out.println();

        System.out.println("input city:(如果不填直接回车，则视为无城市)");
        String city = scanner.nextLine().trim();
        System.out.println("input category:");
        String category = scanner.nextLine().trim();
        BigDecimal amount = null;
        while (amount == null) {
            try {
                System.out.print("input amount: ");
                amount = new BigDecimal(scanner.nextLine().trim());
                if (amount.compareTo(BigDecimal.ZERO) <= 0) {
                    System.out.println("❌ 金额必须大于 0，请重新输入！");
                    amount = null;
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ 金额格式错误，请输入纯数字（可以有小数）！");
            }
        }
        LocalDate expenseDate = null;
        while (expenseDate == null) {
            try {
                System.out.println("input date(example format:YYYY-MM-DD): ");
                expenseDate = LocalDate.parse(scanner.nextLine().trim());
            } catch (Exception e) {
                System.out.println("❌ 日期格式错误，请严格按 yyyy-MM-dd 格式输入！");
            }
        }
        System.out.println("input the description:");
        String description = scanner.nextLine().trim();

        expenseService.insertExpenseWithTravelIdAndCityAndCategory(chooseID,city,category,amount,expenseDate,description);
        System.out.println("you have successfully inserted a expense with travel id and city name and category!!!");
    }

    private void updateExpense() throws SQLException{
        printAllExpenseInfo();
        System.out.println("choose the expense id you want to update");
        Integer choose = Integer.parseInt(scanner.nextLine().trim());
        Expense expense = expenseService.findByExpenseIdService(choose);
        if(expense == null){
            System.out.println("this expense is not exsit in the list!!!");
            return;
        }
        System.out.println("input city:");
        String city = scanner.nextLine().trim();
        System.out.println("input category:");
        String category = scanner.nextLine().trim();
        BigDecimal amount = null;
        while (amount == null) {
            try {
                System.out.print("input amount: ");
                amount = new BigDecimal(scanner.nextLine().trim());
                if (amount.compareTo(BigDecimal.ZERO) <= 0) {
                    System.out.println("❌ 金额必须大于 0，请重新输入！");
                    amount = null;
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ 金额格式错误，请输入纯数字（可以有小数）！");
            }
        }
        LocalDate expenseDate = null;
        while (expenseDate == null) {
            try {
                System.out.println("input date(example format:YYYY-MM-DD): ");
                expenseDate = LocalDate.parse(scanner.nextLine().trim());
            } catch (Exception e) {
                System.out.println("❌ 日期格式错误，请严格按 yyyy-MM-dd 格式输入！");
            }
        }
        System.out.println("input the description:");
        String description = scanner.nextLine().trim();

        expenseService.updateByExpenseId(choose,city,category,amount,expenseDate,description);
        System.out.println("you have successfully update a expense with category and  city!!!");
    }

    private void deleteExpense() throws SQLException{
        printAllExpenseInfo();
        System.out.println("choose the expense id you want to delete");
        Integer choose = Integer.parseInt(scanner.nextLine().trim());
        System.out.println("here are the details about the expense");
        Expense expense = expenseService.findByExpenseIdService(choose);
        if(expense == null){
            System.out.println("this expense id is not exsit in the list!!!");
            return;
        }
        System.out.println(expense);
        System.out.println();
        System.out.println("please confirm whether to delete or not");
        System.out.println("if you want ,click “y” and enter");
        String confirm = scanner.nextLine().trim();
        if(confirm.equals("y")){
            expenseService.deleteById(choose);
        }else {
            System.out.println("delete is cancelled!");
        }
    }

    public void printAllExpenseInfo()throws SQLException{
        List<String> result = expenseService.findAllExpenseWithCategoryAndCityAndtravelId();
        for (String s : result) {
            System.out.println(s);
        }
    }
}
