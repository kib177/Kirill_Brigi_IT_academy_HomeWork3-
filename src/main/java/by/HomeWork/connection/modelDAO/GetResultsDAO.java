package by.HomeWork.connection.modelDAO;

import by.HomeWork.connection.modelDAO.api.IGetResultsDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static by.HomeWork.connection.connectDB.JDBCconnection.*;

public class GetResultsDAO implements IGetResultsDAO {

    @Override
    public Map<String, Integer> getArtistResults() {
        Map<String, Integer> results = new HashMap<>();
        String sqlArtist = "SELECT a.name_artist, COUNT(v.id_vote) as votes " +
                "FROM artists a " +
                "LEFT JOIN votes v ON a.id_artist = v.artist_id " +
                "GROUP BY a.name_artist";

        Connection connection = getDatabaseConnection();
        Statement statement = getStatement(connection);
        try (ResultSet rs = statement.executeQuery(sqlArtist)) {
            while (rs.next()) {
                results.put(rs.getString("name_artist"),
                        rs.getInt("votes"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get artist results", e);
        }
        closeAll(connection, statement);
        return results;

    }

    @Override
    public Map<String, Integer> getGenreResults(){
        Map<String, Integer> results = new HashMap<>();
        String sqlGenre = "SELECT g.name_genre, COUNT(vg.vote_id) as votes " +
                "FROM genres g " +
                "LEFT JOIN vote_genres vg ON g.id_genre = vg.genre_id " +
                "GROUP BY g.name_genre";

        Connection connection = getDatabaseConnection();
        Statement statement = getStatement(connection);
        try (ResultSet rs = statement.executeQuery(sqlGenre)) {
            while (rs.next()) {
                results.put(rs.getString("name_genre"),
                        rs.getInt("votes"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get genre results", e);
        }
        closeAll(connection, statement);
        return results;
    }

    @Override
    public Map<LocalDateTime, String> getAboutResults(){
        Map<LocalDateTime, String> results = new HashMap<>();
        String sqlAbout = "SELECT vote_date, about " +
                "FROM votes " +
                "ORDER BY vote_date ASC";

        Connection connection = getDatabaseConnection();
        Statement statement = getStatement(connection);
        try (ResultSet rs = statement.executeQuery(sqlAbout)) {
            while (rs.next()) {
                results.put(
                        rs.getTimestamp("vote_date").toLocalDateTime(),
                        rs.getString("about"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get artist results", e);
        }
        closeAll(connection, statement);
        return results;
    }

    public void closeAll(Connection conn, Statement stmt) {
        close(conn);
        close(stmt);
    }

}
