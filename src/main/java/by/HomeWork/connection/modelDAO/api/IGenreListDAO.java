package by.HomeWork.connection.modelDAO.api;

import java.util.List;

public interface IGenreListDAO extends IgetListForVoteDAO{
    @Override
    List<String> getListForVote();
}
