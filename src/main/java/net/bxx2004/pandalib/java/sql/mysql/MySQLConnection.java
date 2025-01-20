package net.bxx2004.pandalib.java.sql.mysql;

import net.bxx2004.pandalib.java.sql.SQLConnection;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * MySQL数据库连接
 */
public class MySQLConnection implements SQLConnection {
    private Connection connection;
    private String url;
    private String database;

    /**
     * 获取一个链接
     *
     * @param url      地址
     * @param username 用户名
     * @param password 密码
     * @param database 库名
     */
    public MySQLConnection(String url, String username, String password, String database) {
        this.url = url;
        this.database = database;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String ip = "jdbc:mysql://" + url + "/" + database;
            this.connection = DriverManager.getConnection(ip, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public String getUrl() {
        return url;
    }

    public String getDatabase() {
        return database;
    }
}
