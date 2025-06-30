package by.HomeWork.connection.connectDB;

import java.sql.*;
import java.util.Properties;

public class JDBCconnection {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/Vote";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "1998177";
    private static Connection conn;


    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("JDBC driver error", e);
        }
    }

    public static synchronized Connection getDatabaseConnection(){
        try {
            if(conn == null||conn.isClosed()){
                Properties props = new Properties();
                props.setProperty("user", DB_USER);
                props.setProperty("password", DB_PASSWORD);
                props.setProperty("ssl", "false");
                props.setProperty("connectTimeout", "5");
                conn = DriverManager.getConnection(DB_URL, props);
                conn.setAutoCommit(false);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Connect error", e);
        }
        return conn;
    }
}

