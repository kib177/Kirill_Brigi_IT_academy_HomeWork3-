package by.HomeWork.storage;

import by.HomeWork.service.api.exception.StorageException;
import by.HomeWork.storage.api.ISaveVote;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;

public class SaveVote implements ISaveVote {
    private static final String SAVE_VOTE_SQL = "INSERT INTO votes (artist_id, vote_date, about)" +
            "VALUES ((SELECT id_artist " +
            "FROM artists " +
            "WHERE name_artist = ?), ?, ?) " +
            "RETURNING id_vote";
    private static final String SAVE_GENRE_SQL = "INSERT INTO vote_genres (vote_id, genre_id)" +
            "VALUES (?, (SELECT id_genre " +
            "FROM genres " +
            "WHERE name_genre = ?))";

    private final DataSource dataSource;

    public SaveVote(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void saveVote(String artist, List<String> genres, String about) {

        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);

            try (PreparedStatement voteStmt = connection.prepareStatement(SAVE_VOTE_SQL);
                 PreparedStatement genreStmt = connection.prepareStatement(SAVE_GENRE_SQL)) {

                voteStmt.setString(1, artist);
                voteStmt.setTimestamp(2,
                        Timestamp.valueOf(LocalDateTime.now()));
                voteStmt.setString(3, about);

                ResultSet rs = voteStmt.executeQuery();
                if (!rs.next()) throw new StorageException("No ID obtained");
                long voteId = rs.getLong(1);

                for (String genre : genres) {
                    genreStmt.setLong(1, voteId);
                    genreStmt.setString(2, genre);
                    genreStmt.addBatch();
                }
                genreStmt.executeBatch(); // без пакета не записывает genre
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw e;
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("Failed to save vote", e);
        }
    }
}
