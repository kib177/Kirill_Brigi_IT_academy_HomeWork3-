package by.HomeWork.connection.modelDAO.api;

import java.util.List;

public interface IArtistDAO extends IgetListForVoteDAO{
    @Override
    List<String> getListForVote();
}
