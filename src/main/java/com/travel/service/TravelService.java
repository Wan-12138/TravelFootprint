package com.travel.service;


import com.travel.JDBCUtils;
import com.travel.dao.ExpenseDao;
import com.travel.dao.TravelCityDao;
import com.travel.dao.TravelDao;
import com.travel.dao.TravelScenicDao;
import com.travel.entity.Travel;
import com.travel.entity.TravelCity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TravelService {

    TravelDao travelDao = new TravelDao();

    public List<Travel> getAllTravels()throws SQLException {
        return travelDao.findAll();
    }

    public Travel getTravelById(int id)throws SQLException{
        return travelDao.findById(id);
    }

    public void createTravel(Travel travel)throws SQLException{
        Connection conn = null;//在try外面生明Connection，为了在finally里关掉
        try {
            conn = JDBCUtils.getConnection();//获取数据库连接
            conn.setAutoCommit(false);//开启事务，先不要存入内存，等到commit再存
            travelDao.insert(travel,conn);//插入主表，传入conn是为了让所有Dao都用这一个conn执行SQL

            //在这里扩展

            conn.commit();
        }catch (Exception e){
            //出错就回滚
            if(conn != null){
                try {
                    conn.rollback();
                }catch (SQLException ex){
                    ex.printStackTrace();
                }
            }
            throw new SQLException("failed to create a Travel,already rollback!"+e.getMessage()+e);
        }finally {
            if (conn != null){
                try {
                    conn.setAutoCommit(true);//恢复默认自动提交
                    conn.close();//释放资源
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }

    }

    public void updateTravel(Travel travel) throws SQLException {
        if(travel.getEndDate().isBefore(travel.getBeginDate())){
            throw new IllegalArgumentException("endDate cannot before beginDate!");
        }
        travelDao.update(travel);
    }

    public void deleteTravel(Integer travelId){
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            conn.setAutoCommit(false);

            new ExpenseDao().deleteByTravelId(travelId,conn);
            new TravelCityDao().deleteByTravelId(travelId,conn);
            new TravelScenicDao().deleteByTravelId(travelId,conn);

            int rows = travelDao.deleteById(travelId, conn);
            if(rows == 0){
                throw new RuntimeException("cannot find the travel");
            }
            conn.commit();
            System.out.println("successfully delete!");
        }catch (Exception e){
            if (conn != null){
                try {
                    conn.rollback();
                }catch (SQLException ex){
                    ex.printStackTrace();
                }
            }
            throw new RuntimeException("fail to delete! already rollback"+e.getMessage()+e);
        }finally {
            if(conn != null){
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}
