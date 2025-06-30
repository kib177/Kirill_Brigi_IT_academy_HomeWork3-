package by.HomeWork.connection.modelDAO;

import by.HomeWork.connection.modelDAO.api.IGenreListDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static by.HomeWork.connection.connectDB.JDBCconnection.*;

public class GenresListDAO implements IGenreListDAO {
    List<String> genres = new ArrayList<>();

    @Override
    public List<String> getListForVote() {
        try (Connection connection = getDatabaseConnection();
        PreparedStatement statement = connection.prepareStatement("select * from genres " +
                                                                      "order by name_genre ASC");
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
