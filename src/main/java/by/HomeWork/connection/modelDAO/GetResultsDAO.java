package by.HomeWork.connection.modelDAO;

import by.HomeWork.connection.modelDAO.api.IGetResultsDAO;
import jdk.jshell.execution.LoaderDelegate;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static by.HomeWork.connection.connectDB.JDBCconnection.*;

public class GetResultsDAO implements IGetResultsDAO {

    // Общий метод для выполнения запросов
    private <T> T executeQuery(String sql, ResultSetProcessor<T> processor, Object... params) {
        try (Connection connection = getDatabaseConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            // Установка параметров
            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }

            try (ResultSet rs = statement.executeQuery()) {
                return processor.process(rs);
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("Failed connection: ", e);
        }
    }

    @FunctionalInterface
    private interface ResultSetProcessor<T> {
        T process(ResultSet rs) throws SQLException;
    }

    @Override
    public Map<String, Integer> getArtistResults() {
        String sql = "SELECT a.name_artist, COUNT(v.id_vote) as votes "
                + "FROM artists a "
                + "LEFT JOIN votes v ON a.id_artist = v.artist_id "
                + "GROUP BY a.name_artist";

        return executeQuery(sql, rs -> {
            Map<String, Integer> results = new HashMap<>();
            while (rs.next()) {
                results.put(rs.getString("name_artist"), rs.getInt("votes"));
            }
            return results;
        });
    }

    @Override
    public Map<String, Integer> getGenreResults() {
        String sql = "SELECT g.name_genre, COUNT(vg.vote_id) as votes "
                + "FROM genres g "
                + "LEFT JOIN vote_genres vg ON g.id_genre = vg.genre_id "
                + "GROUP BY g.name_genre";

        return executeQuery(sql, rs -> {
            Map<String, Integer> results = new HashMap<>();
            while (rs.next()) {
                results.put(rs.getString("name_genre"), rs.getInt("votes"));
            }
            return results;
        });
    }

    @Override
    public Map<LocalDateTime, String> getAboutResults() {
        String sql = "SELECT vote_date, about FROM votes ORDER BY vote_date ASC";

        return executeQuery(sql, rs -> {
            Map<LocalDateTime, String> results = new HashMap<>();
            while (rs.next()) {
                results.put(
                        rs.getTimestamp("vote_date").toLocalDateTime(),
                        rs.getString("about")
                );
            }
            return results;
        });
    }

}
