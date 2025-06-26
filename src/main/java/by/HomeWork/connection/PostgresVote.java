package by.HomeWork.connection;

import java.sql.*;
import java.time.LocalDateTime;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static by.HomeWork.connection.JDBCconnection.connect;

public class PostgresVote {

    public void saveVote(String artist, List<String> genres, String about) {
        String sqlvotes = "INSERT INTO votes (artist_id, vote_date, about)" +
                "VALUES ((SELECT id_artist " +
                        "FROM artists " +
                        "WHERE name_artist = ?), ?, ?) " +
                "RETURNING id_vote";

        String sqlVoteGenre = "INSERT INTO vote_genres (vote_id, genre_id)" +
                "VALUES (?, (SELECT id_genre " +
                "FROM genres " +
                "WHERE name_genre = ?))";

        try (Connection conn = connect();
             PreparedStatement voteStmt = conn.prepareStatement(sqlvotes);
             PreparedStatement genreStmt = conn.prepareStatement(sqlVoteGenre)) {

            // Сохраняем основной голос
            voteStmt.setString(1, artist);
            voteStmt.setTimestamp(2,
                    Timestamp.valueOf(LocalDateTime.now()));
            voteStmt.setString(3, about);

            ResultSet rs = voteStmt.executeQuery();
            if (!rs.next()) throw new SQLException("No ID obtained");
            long voteId = rs.getLong(1);

            // Сохраняем жанры
            for (String genre : genres) {
                genreStmt.setLong(1, voteId);
                genreStmt.setString(2, genre);
                genreStmt.addBatch();
            }
            genreStmt.executeBatch();
            conn.commit();
        } catch (SQLException | ClassNotFoundException e) {
            // conn.rollback();
            throw new RuntimeException("Failed to save vote", e);
        }
    }


    public Map<String, Integer> getArtistResults() {
        Map<String, Integer> results = new HashMap<>();
        String sql = "SELECT a.name_artist, COUNT(v.id_vote) as votes " +
                     "FROM artists a " +
                     "LEFT JOIN votes v ON a.id_artist = v.artist_id " +
                     "GROUP BY a.name_artist";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                results.put(rs.getString("name_artist"),
                        rs.getInt("votes"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Failed to get artist results", e);
        }
        return results;
    }

    public Map<String, Integer> getGenreResults() {
        Map<String, Integer> results = new HashMap<>();
        String sqlGenre = "SELECT g.name_genre, COUNT(vg.vote_id) as votes " +
                     "FROM genres g " +
                     "LEFT JOIN vote_genres vg ON g.id_genre = vg.genre_id " +
                     "GROUP BY g.name_genre";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sqlGenre)) {

            while (rs.next()) {
                results.put(rs.getString("name_genre"),
                        rs.getInt("votes"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Failed to get genre results", e);
        }
        return results;
    }

    public Map<LocalDateTime, String> getAboutResults() {
        Map<LocalDateTime, String> results = new HashMap<>();
        String sqlAbout = "SELECT vote_date, about " +
                "FROM votes " +
                "ORDER BY vote_date DESC";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sqlAbout)) {

            while (rs.next()) {
                results.put(
                        rs.getTimestamp("vote_date").toLocalDateTime(),
                        rs.getString("about"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Failed to get about results", e);
        }
        return results;
    }

}
