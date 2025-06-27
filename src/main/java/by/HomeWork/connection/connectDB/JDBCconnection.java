package by.HomeWork.connection.connectDB;

import java.sql.*;
import java.util.Properties;

public class JDBCconnection {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/Vote";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "1998177";

    public static Connection getDatabaseConnection(){

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("JDBC driver error", e);
        }

        Properties props = new Properties();
        props.setProperty("user", DB_USER);
        props.setProperty("password", DB_PASSWORD);
        props.setProperty("ssl", "false");
        props.setProperty("connectTimeout", "5");

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL, props);
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            throw new RuntimeException("Connect error", e);
        }
        return conn;
    }

    public static Statement getStatement(Connection connection){
        try {
            return connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException("Statement error", e);
        }
    }

    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException("Error closing connection", e);
            }
        }
    }

    public static void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException("Error closing connection", e);
            }
        }
    }

    public static void rollbackConnection(Connection connection) {
        try {
            connection.rollback();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

