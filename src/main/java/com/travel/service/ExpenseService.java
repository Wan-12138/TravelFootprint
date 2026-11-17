package com.travel.service;

import com.travel.dao.CityDao;
import com.travel.dao.ExpenseCategoryDao;
import com.travel.dao.ExpenseDao;
import com.travel.dao.TravelDao;
import com.travel.entity.City;
import com.travel.entity.Expense;
import com.travel.entity.ExpenseCategory;
import com.travel.entity.Travel;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ExpenseService {
    ExpenseDao expenseDao = new ExpenseDao();
    ExpenseCategoryDao expenseCategoryDao = new ExpenseCategoryDao();
    TravelDao travelDao = new TravelDao();
    CityDao cityDao = new CityDao();

    public List<String> findAllExpenseWithCategoryAndCityAndtravelId()throws SQLException {
        /**
         * 查出花费所属旅行+花费所属城市+花费所属类型+花费金额+消费日期+消费描述
         * 需要先获得所有花费，然后获取它的旅行id、城市id、类型id
         * 再调用三个Dao的findById方法获取到对应的旅行、城市、类型
         */

        List<Expense> allExpense = expenseDao.findAll();

        List<String> resultList = new ArrayList<>();
        for (Expense expense : allExpense) {
            Travel et = travelDao.findById(expense.getExpenseTravelId());
            City expenseCity = expense.getExpenseCityId() == null ? null : cityDao.findById(expense.getExpenseCityId());
            String cityName = (expenseCity == null) ? "跨城/未指定" : expenseCity.getCity();
            ExpenseCategory ec = expenseCategoryDao.findById(expense.getExpenseCategoryId());

            String result = "Travel id: "+et.getTravelId()+" expense id: "+expense.getExpenseId()+" city : "+cityName+
                            " expense category : "+ec.getCategory()+" expense amount: "+expense.getExpenseAmount()+
                            " expense date: "+expense.getExpenseDate()+" expense description : "+expense.getExpenseDescription();
            resultList.add(result);
        }
        return resultList;
    }

    public void insertExpenseWithTravelIdAndCityAndCategory(Integer travelId, String city, String category, BigDecimal amount, LocalDate date,String description)throws SQLException{
        /**
         * 要插入花费，先选择在哪里插入，不能凭空加，花费一定有所属旅行以及类型，可以没有城市（例如跨城交通）
         * 所以我要先打印所有的旅行，让用户在里面选要插入的旅行id
         * 要先写一个按旅行id查询的方法,在里面打印旅行id、城市、时间即可，别的不用打印
         * 打出来一个列表，同一次旅行，同一个城市，同一天可能有多条花费
         */
        Travel travel = travelDao.findById(travelId);
        Expense expense = new Expense();
        if(travel == null){
            throw new IllegalArgumentException("cannot found this travel id");
        }
        if (city != null && !city.isEmpty()) {
            City city1 = cityDao.findByName(city);
            if (city1 == null) throw new IllegalArgumentException("cannot found this city");
            expense.setExpenseCityId(city1.getCityId());
        } else {
            expense.setExpenseCityId(null);
        }
        ExpenseCategory expenseCategory = expenseCategoryDao.findByName(category);
        if(expenseCategory == null){
            throw new IllegalArgumentException("cannot find the category");
        }


        expense.setExpenseTravelId(travel.getTravelId());
        expense.setExpenseCategoryId(expenseCategory.getCategoryId());
        expense.setExpenseAmount(amount);
        expense.setExpenseDate(date);
        expense.setExpenseDescription(description);
        expenseDao.insert(expense);

    }

    public List<Expense> findExpenseByTravelId(int id)throws SQLException{
        return expenseDao.findByTravelId(id);
    }
    public City findCityByExpenseCityId(Integer id) throws SQLException {
        if(id == null){
            return null;
        }
        return cityDao.findById(id);
    }

    public Travel findTravelById(int id) throws SQLException {
        return travelDao.findById(id);
    }

    public List<String> findAllExpenseWithTravelIDAndCity()throws SQLException{
        List<Expense> allExpense = expenseDao.findAll();

        List<String> resultList = new ArrayList<>();
        for (Expense expense : allExpense) {
            Travel et = travelDao.findById(expense.getExpenseTravelId());
            City expenseCity = (expense.getExpenseCityId() != null) ? cityDao.findById(expense.getExpenseCityId()) : null;
            String cityName = (expenseCity != null) ? expenseCity.getCity() : "跨城/未指定";
            String result = "Travel id: "+et.getTravelId()+" city : "+cityName+ " expense date: "+expense.getExpenseDate();
            resultList.add(result);
        }
        return resultList;
    }

    public void updateByExpenseId(Integer expenseId,String city,String category,BigDecimal amount,LocalDate date,String description)throws SQLException{
        Expense expense = expenseDao.findByExpenseId(expenseId);
        if (expense == null) {
            throw new IllegalArgumentException("expense not found: " + expenseId);
        }

        if (city != null && !city.isEmpty()) {
            City c = cityDao.findByName(city);
            if (c == null) throw new IllegalArgumentException("cannot find city: " + city);
            expense.setExpenseCityId(c.getCityId());
        } else {
            expense.setExpenseCityId(null);
        }
        ExpenseCategory expenseCategory = expenseCategoryDao.findByName(category);
        if (expenseCategory == null) {
            throw new IllegalArgumentException("cannot find category: " + category);
        }
        expense.setExpenseCategoryId(expenseCategory.getCategoryId());
        expense.setExpenseAmount(amount);
        expense.setExpenseDate(date);
        expense.setExpenseDescription(description);
        expenseDao.update(expense);

    }

    public Expense findByExpenseIdService(int id) throws SQLException{
        return expenseDao.findByExpenseId(id);
    }

    public void deleteById(int id)throws SQLException{
        expenseDao.deleteById(id);
    }




}
