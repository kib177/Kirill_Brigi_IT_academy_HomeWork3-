package by.HomeWork.connection;

import java.sql.*;
import java.util.Properties;

public class JDBCconnection {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/Vote";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "1998177";

    public static Connection connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Properties props = new Properties();
        props.setProperty("user", DB_USER);
        props.setProperty("password", DB_PASSWORD);
        props.setProperty("ssl", "false");
        Connection conn = DriverManager.getConnection(DB_URL, props);
        conn.setAutoCommit(false);
        return conn;
    }
}

