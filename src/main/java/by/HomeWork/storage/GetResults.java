package by.HomeWork.storage;

import by.HomeWork.dto.VotingResults;
import by.HomeWork.storage.api.IGetResults;
import by.HomeWork.service.api.exception.StorageException;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class GetResults implements IGetResults {
    private final DataSource dataSource;

    public GetResults(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // Общий метод для выполнения запросов
    private <T> T executeQuery(String sql, ResultSetProcessor<T> processor, Object... params) {

        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            // Установка параметров
            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }

            try (ResultSet rs = statement.executeQuery()) {
                return processor.process(rs);
            }
        } catch (SQLException e) {
            throw new StorageException("Проблема с выгрузкой данных: ", e);
        }
    }

    @Override
    public VotingResults getAllResults() {
        return new VotingResults.Builder()
                .artistVotes(getArtistResults())
                .genreVotes(getGenreResults())
                .aboutInfo(getAboutResults())
                .build();
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
