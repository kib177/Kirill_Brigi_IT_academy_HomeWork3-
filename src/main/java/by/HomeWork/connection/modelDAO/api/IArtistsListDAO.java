package by.HomeWork.connection.modelDAO.api;

import java.util.List;

public interface IArtistsListDAO extends IgetListForVoteDAO{
    @Override
    List<String> getListForVote();
}
