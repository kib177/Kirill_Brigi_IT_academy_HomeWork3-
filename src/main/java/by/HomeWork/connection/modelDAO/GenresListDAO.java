package by.HomeWork.connection.modelDAO;

import by.HomeWork.connection.modelDAO.api.IGenreListDAO;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GenresListDAO implements IGenreListDAO {
    private final String sql = "select * from genres " +
            "order by name_genre ASC";
    private final DataSource dataSource;

    public GenresListDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<String> getListForVote() {
        List<String> genres = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                String name = rs.getString("name_genre");
                genres.add(name);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get artist results", e);
        }
        return genres;
    }
}
