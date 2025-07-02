package by.HomeWork.connection.modelDAO;

import by.HomeWork.connection.modelDAO.api.IArtistsListDAO;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArtistListDAO implements IArtistsListDAO {
    private final String sql = "select * from artists " +
            "order by name_artist ASC";
    private final DataSource dataSource;

    public ArtistListDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<String> getListForVote() {
        List<String> artists = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                String name = rs.getString("name_artist");
                artists.add(name);
            }
        } catch (SQLException e) {
            throw new RuntimeException("ResultSet getListForVote error", e);
        }
        return artists;
    }
}
