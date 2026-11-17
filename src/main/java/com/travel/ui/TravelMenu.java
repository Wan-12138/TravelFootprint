package com.travel.ui;

import com.travel.entity.Travel;
import com.travel.service.TravelService;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class TravelMenu {
    private final Scanner scanner;
    private final TravelService travelService;


    public TravelMenu(Scanner scanner, TravelService travelService) {
        this.scanner = scanner;
        this.travelService = travelService;
    }

    public void show(){
        while (true){
            System.out.println("=====欢迎使用旅行管理功能，请输入数字选择如下功能=====");
            System.out.println("1.查询旅行");
            System.out.println("2.添加旅行");
            System.out.println("3.修改旅行");
            System.out.println("4.删除旅行");
            System.out.println("0.返回主菜单");
            String choice = scanner.nextLine().trim();

            if ("0".equals(choice)) {
                return;
            }
            try {
                switch (choice) {
                    case "1":
                        findAllTravels();
                        break;
                    case "2":
                        insertTravel();
                        break;
                    case "3":
                        updateTravel();
                        break;
                    case "4":
                        deleteTravel();
                        break;
                    default:
                        System.out.println("请输入正确的数字！");
                        continue;
                }
                System.out.println("\n----------------------------------");
                System.out.println("操作完成！按 0 返回主菜单，按其他任意键继续当前功能...");
            }catch (Exception e){
                System.out.println("fail to operate!" + e.getMessage());
            }

            String next = scanner.nextLine().trim();
            if ("0".equals(next)) {
                return; // 用户按了0，跳出循环，回到最外层的主菜单
            }
        }
    }
    private void findAllTravels() throws SQLException {
        List<Travel> allTravels = travelService.getAllTravels();
        if (allTravels.isEmpty()) {
            System.out.println("目前空空如也，快添加一次旅行吧！");
            return;
        }
        for (Travel t : allTravels) {
            System.out.printf("旅行ID: %d | 日期: %s 至 %s | 评分(便利/饮食/体验): %d/%d/%d\n",
                    t.getTravelId(), t.getBeginDate(), t.getEndDate(),
                    t.getConvenienceScore(), t.getFoodScore(), t.getExperienceScore());
        }

    }

    private void insertTravel() throws SQLException{
        System.out.println("=====添加旅行=====");
        LocalDate beginDate = null;
        LocalDate endDate = null;
        //主体1：获取用户输入的旅行对象的内容，并检验日期格式以及日期的顺序
        //校验日期，对应SQL的check
        while (true){
            try {
                System.out.println("input the beginDate (example format : yyyy-mm-dd):");
                beginDate = LocalDate.parse(scanner.nextLine().trim());
                //LocalDate.parse会自动解析“年-月-日”
                System.out.println("input the endDate (example format : yyyy-mm-dd):");
                endDate = LocalDate.parse(scanner.nextLine().trim());

                if(endDate.isBefore(beginDate)){
                    System.out.println("endDate cannot before beginDate,please input again!");
                    continue;
                }
                break;
            }catch (Exception e){
                System.out.println("wrong format!please strictly input with yyyy-mm-dd format");
            }
        }

        //接主体1：接收用户输入的评分
        System.out.print("input travel convenience score(0-5):");
        int convenienceScore = Integer.parseInt(scanner.nextLine().trim());
        if (convenienceScore < 0 || convenienceScore > 5) {
            throw new IllegalArgumentException(
                    "score must between 0 and 5"
            );
        }
        System.out.print("input travel food score (0-5):");
        int foodScore = Integer.parseInt(scanner.nextLine().trim());
        if (foodScore < 0 || foodScore > 5) {
            throw new IllegalArgumentException(
                    "score must between 0 and 5"
            );
        }
        System.out.println("input travel experience score(0-5):");
        int experienceScore = Integer.parseInt(scanner.nextLine().trim());
        if (experienceScore < 0 || experienceScore > 5) {
            throw new IllegalArgumentException(
                    "score must between 0 and 5"
            );
        }
        //主体2：新建空的Travel实体，把用户输入的数据放到里面
        Travel travel = new Travel();
        travel.setBeginDate(beginDate);
        travel.setEndDate(endDate);
        travel.setConvenienceScore(convenienceScore);
        travel.setFoodScore(foodScore);
        travel.setExperienceScore(experienceScore);
        //调用Service保存

        //把组装好的travel扔给Service
        travelService.createTravel(travel);
        //如果Service保存成功，由于底层Dao设置了RETURN_GENERATED_KEYS，
        //数据库里生成的travel_id，会回填到travel对象里
        System.out.println("successfully operated! generate travel_id is :"+travel.getTravelId());



    }

    private void updateTravel() throws SQLException{
        System.out.println("Please input the travel_id you want to update:");
        Integer id = Integer.parseInt(scanner.nextLine().trim());//主体1：用户输入要修改的旅行id

        Travel travelById = travelService.getTravelById(id);//主体2：按id把这个旅行取出来

        if (travelById == null){
            System.out.println("this travel information cannot be found!");
            return;
        }

        System.out.println("exist travel information is:"+travelById);

        //主体3：修改过程，把用户新输入的set到这个取出的旅行对象中
        System.out.println("input new begindate(now is:"+travelById.getBeginDate()+"):");
        travelById.setBeginDate(LocalDate.parse(scanner.nextLine().trim()));

        System.out.println("input new enddate(now is:"+travelById.getEndDate()+"):");
        travelById.setEndDate(LocalDate.parse(scanner.nextLine().trim()));

        System.out.println("input new convenience score(0-5):");
        Integer newConvenienceScore = Integer.parseInt(scanner.nextLine().trim());
        travelById.setConvenienceScore(newConvenienceScore);
        if ( newConvenienceScore < 0 || newConvenienceScore > 5) {
            throw new IllegalArgumentException(
                    "score must between 0 and 5"
            );
        }

        System.out.println("input new food score(0-5):");
        Integer newFoodScore = Integer.parseInt(scanner.nextLine().trim());
        travelById.setFoodScore(newFoodScore);

        System.out.println("input new experience score(0-5):");
        Integer newExperienceScore = Integer.parseInt(scanner.nextLine().trim());
        travelById.setExperienceScore(newExperienceScore);
        if ( newExperienceScore < 0 || newExperienceScore > 5) {
            throw new IllegalArgumentException(
                    "score must between 0 and 5"
            );
        }
        travelService.updateTravel(travelById);//主体4：执行travelService的更新操作
        System.out.println("successfully update!");

    }

    private void deleteTravel() throws SQLException{
        Integer id = Integer.parseInt(scanner.nextLine().trim());//主体1：用户输入要删除的旅行id
        System.out.println("input the travel id you want to delete:");
        Travel travel = travelService.getTravelById(id);
        if(travel == null){
            System.out.println("cannot find the information");
            return;
        }
        System.out.println("⚠️ 警告：即将删除旅行 [" + travel.getBeginDate() + " 至 " + travel.getEndDate() + "]");
        System.out.println("这将会同时删除该旅行下的所有城市、景点和花费记录！");
        System.out.print("确定要删除吗？(y/n): ");

        String confirm = scanner.nextLine().trim();
        if ("y".equalsIgnoreCase(confirm)){
            travelService.deleteTravel(id);//主体2：执行删除
        }else{
            System.out.println("cancel the delete");
        }
    }

}
