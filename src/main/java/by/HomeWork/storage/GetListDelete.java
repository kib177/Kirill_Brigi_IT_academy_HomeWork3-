package by.HomeWork.storage;

import by.HomeWork.service.api.exception.StorageException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static by.HomeWork.service.api.Connection.getDataSource;

public class GetListDelete {

    private final DataSource dataSource;

    public GetListDelete(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Map<LocalDateTime, String> getAboutList() {
        String sql = "SELECT vote_date, about FROM votes ORDER BY vote_date ASC";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             ResultSet rs = stmt.executeQuery();

            Map<LocalDateTime, String> results = new HashMap<>();
            while (rs.next()) {
                results.put(
                        rs.getTimestamp("vote_date").toLocalDateTime(),
                        rs.getString("about")
                );
            }
            return results;
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }
}
