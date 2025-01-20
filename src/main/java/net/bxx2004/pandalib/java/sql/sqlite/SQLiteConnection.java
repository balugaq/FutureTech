package net.bxx2004.pandalib.java.sql.sqlite;

import net.bxx2004.pandalib.java.sql.SQLConnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLiteConnection implements SQLConnection {
    private Connection connection;
    private String url;

    public SQLiteConnection(String url) {
        this.url = url;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + url);
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
}
