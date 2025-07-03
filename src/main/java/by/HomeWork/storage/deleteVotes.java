package by.HomeWork.storage;

import by.HomeWork.service.api.exception.StorageException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class deleteVotes {
    private final DataSource dataSource;

    public deleteVotes(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void deleteAllVotes() {
        String deleteVoteGenresSQL = "DELETE FROM vote_genres";
        String deleteVotesSQL = "DELETE FROM votes";

        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);

            try (PreparedStatement stmtGenre = connection.prepareStatement(deleteVoteGenresSQL);
                 PreparedStatement stmtVotes = connection.prepareStatement(deleteVotesSQL)) {
                stmtGenre.executeUpdate();
                stmtVotes.executeUpdate();
                connection.commit();
                System.out.println("Все голоса успешно удалены");

            } catch (SQLException e) {
                connection.rollback();
                throw new StorageException("Ошибка удаления голосов: " + e.getMessage(), e);
            }
        } catch (SQLException e) {
            throw new StorageException("Ошибка подключения к БД: " + e.getMessage(), e);
        }
    }
}
