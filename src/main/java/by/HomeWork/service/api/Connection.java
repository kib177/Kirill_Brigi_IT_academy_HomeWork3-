package by.HomeWork.service.api;

import by.HomeWork.service.api.exception.StorageException;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;

public class Connection {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/Vote";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "1998177";
    private static final String JDBCDriver = "org.postgresql.Driver";
    private static DataSource dataSource;


    static {
        try {
            ComboPooledDataSource config = new ComboPooledDataSource();
            config.setDriverClass(JDBCDriver);
            config.setJdbcUrl(DB_URL);
            config.setUser(DB_USER);
            config.setPassword(DB_PASSWORD);
            config.setMaxPoolSize(10);
            config.setMinPoolSize(5);
            dataSource = config;
        } catch (Exception e) {
            throw new StorageException("Connection pool initialization failed", e);
        }
    }

    public static DataSource getDataSource() {
        return dataSource;
    }
}

