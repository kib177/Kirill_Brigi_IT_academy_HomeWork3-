package by.HomeWork.connection.modelDAO;

import by.HomeWork.connection.modelDAO.api.IArtistsListDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static by.HomeWork.connection.connectDB.JDBCconnection.*;

public class ArtistListDAO implements IArtistsListDAO {
    List<String> artists = new ArrayList<>();

    @Override
    public List<String> getListForVote(){
        Connection connection = getDatabaseConnection();
        try(PreparedStatement statement = connection.prepareStatement("select * from artists " +
                                                                      "order by name_artist ASC");
        ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                String name = rs.getString("name_artist");
                artists.add(name);
            }
        }catch (SQLException e) {
            throw new RuntimeException("ResultSet getListForVote error", e);
        }
        return artists;
    }
}
