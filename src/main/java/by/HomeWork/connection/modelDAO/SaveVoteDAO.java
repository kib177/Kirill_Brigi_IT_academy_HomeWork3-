package by.HomeWork.connection.modelDAO;

import by.HomeWork.connection.modelDAO.api.ISaveVoteDAO;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;

import static by.HomeWork.connection.connectDB.JDBCconnection.*;

public class SaveVoteDAO implements ISaveVoteDAO {

    @Override
    public void saveVote(String artist, List<String> genres, String about){
        String sqlvotes = "INSERT INTO votes (artist_id, vote_date, about)" +
                "VALUES ((SELECT id_artist " +
                "FROM artists " +
                "WHERE name_artist = ?), ?, ?) " +
                "RETURNING id_vote";

        String sqlVoteGenre = "INSERT INTO vote_genres (vote_id, genre_id)" +
                "VALUES (?, (SELECT id_genre " +
                "FROM genres " +
                "WHERE name_genre = ?))";

        Connection connection = getDatabaseConnection();
        try( PreparedStatement voteStmt = connection.prepareStatement(sqlvotes);
             PreparedStatement genreStmt = connection.prepareStatement(sqlVoteGenre)){

            voteStmt.setString(1, artist);
            voteStmt.setTimestamp(2,
                    Timestamp.valueOf(LocalDateTime.now()));
            voteStmt.setString(3, about);

            ResultSet rs = voteStmt.executeQuery();
            if (!rs.next()) throw new SQLException("No ID obtained");
            long voteId = rs.getLong(1);

            for (String genre : genres) {
                genreStmt.setLong(1, voteId);
                genreStmt.setString(2, genre);
                genreStmt.addBatch();
            }
            genreStmt.executeBatch();
            connection.commit();
        } catch (SQLException e) {
            rollbackConnection(connection);
            throw new RuntimeException("Failed to save vote", e);
        }

        close(connection);
    }
}
