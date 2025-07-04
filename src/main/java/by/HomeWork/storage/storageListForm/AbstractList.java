package by.HomeWork.storage.storageListForm;

import by.HomeWork.storage.storageListForm.api.IListForForm;
import by.HomeWork.service.api.exception.StorageException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractList implements IListForForm {
    private final DataSource dataSource;
    private final String sqlQuery;

    protected AbstractList(DataSource dataSource, String sqlQuery) {
        this.dataSource = dataSource;
        this.sqlQuery = sqlQuery;
    }

    @Override
    public List<String> getListForVote() {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sqlQuery);
             ResultSet rs = stmt.executeQuery()) {

            List<String> items = new ArrayList<>();
            while (rs.next()) {
                items.add(rs.getString(2));
            }
            return items;
        } catch (SQLException e) {
            throw new StorageException("Failed to load list: " + sqlQuery, e);
        }
    }
}