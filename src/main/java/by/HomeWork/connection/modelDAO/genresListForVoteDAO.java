package by.HomeWork.connection.modelDAO;

import by.HomeWork.connection.modelDAO.api.IArtistsDAO;
import by.HomeWork.connection.modelDAO.api.IGenreDAO;
import by.HomeWork.connection.modelDAO.api.IgetListForVoteDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static by.HomeWork.connection.connectDB.JDBCconnection.*;

public class genresListForVoteDAO implements IGenreDAO {
    List<String> genres = new ArrayList<>();

    @Override
    public List<String> getListForVote() {
        Connection connection = getDatabaseConnection();
        Statement statement = getStatement(connection);
        try (ResultSet rs = statement.executeQuery(
                "select * from genres order by name_genre ASC")) {
            while (rs.next()) {
                String name = rs.getString("name_genre");
                genres.add(name);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get artist results", e);
        }
        close(statement);
        close(connection);
        return genres;
    }
}
