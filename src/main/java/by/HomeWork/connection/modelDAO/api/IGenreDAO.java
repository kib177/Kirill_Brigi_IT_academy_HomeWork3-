package by.HomeWork.connection.modelDAO.api;

import java.util.List;

public interface IGenreDAO extends IgetListForVoteDAO{
    @Override
    List<String> getListForVote();
}
