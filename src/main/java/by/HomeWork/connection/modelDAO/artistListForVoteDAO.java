package by.HomeWork.connection.modelDAO;

import by.HomeWork.connection.modelDAO.api.IgetListForVoteDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static by.HomeWork.connection.connectDB.JDBCconnection.*;

public class artistListForVoteDAO implements IgetListForVoteDAO {
    List<String> artists = new ArrayList<>();

    @Override
    public List<String> getListForVote(){
        Connection connection = getDatabaseConnection();
        Statement statement = getStatement(connection);

        try(ResultSet rs = statement.executeQuery(
                "select * from artists order by name_artist ASC")) {
            while (rs.next()) {
                String name = rs.getString("name_artist");
                artists.add(name);
            }
        }catch (SQLException e) {
            throw new RuntimeException("ResultSet getListForVote error", e);
        }

        close(statement);
        close(connection);
        return artists;

    }
}
