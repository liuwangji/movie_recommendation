package com.port.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 数据源配置信息
 */
@Configuration
public class MysqlConnectionConfig {

    public static String driver;

    public static String url;

    public static String username ;

    public static String password;

    @Value("${jdbc.driverClassName}")
    public void setDriver(String driver) {
        MysqlConnectionConfig.driver = driver;
    }
    @Value("${jdbc.urlName}")
    public void setUrl(String url) {
        MysqlConnectionConfig.url = url;
    }
    @Value("${jdbc.username}")
    public void setUsername(String username) {
        MysqlConnectionConfig.username = username;
    }
    @Value("${jdbc.password}")
    public void setPassword(String password) {
        MysqlConnectionConfig.password = password;
    }

    @Bean(name="mysqlConnection")
    private static Connection getConn() {
        Connection conn = null;
        try {
            //classLoader,加载对应驱动
            Class.forName(driver);
            conn = (Connection) DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
