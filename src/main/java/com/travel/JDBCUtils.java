package com.travel;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtils {
    private static final String URL;
    private static final String USER;
    private static final String PASSWORD;

    static {
        // 1. 尝试读取配置文件（如果存在的话）
        Properties props = new Properties();
        try (InputStream in = JDBCUtils.class.getClassLoader().getResourceAsStream("db.properties")) {
            if (in != null) {
                props.load(in);
            }
        } catch (Exception e) {
            System.err.println("读取配置文件失败，将仅依赖环境变量");
        }

        // 2. 优先级：环境变量 > 配置文件
        String envUrl = System.getenv("TRAVEL_DB_URL");
        String envUser = System.getenv("TRAVEL_DB_USER");
        String envPwd = System.getenv("TRAVEL_DB_PASSWORD");

        URL = (envUrl != null && !envUrl.isEmpty()) ? envUrl : props.getProperty("jdbc.url");
        USER = (envUser != null && !envUser.isEmpty()) ? envUser : props.getProperty("jdbc.user");
        PASSWORD = (envPwd != null && !envPwd.isEmpty()) ? envPwd : props.getProperty("jdbc.password");

        // 3. 启动自检
        if (URL == null || USER == null || PASSWORD == null) {
            throw new IllegalStateException("数据库配置缺失！请配置环境变量或提供 db.properties");
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}