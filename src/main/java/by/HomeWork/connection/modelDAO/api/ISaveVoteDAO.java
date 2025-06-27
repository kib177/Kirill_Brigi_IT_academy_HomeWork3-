package by.HomeWork.connection.modelDAO.api;

import java.util.List;

public interface ISaveVoteDAO{
    void saveVote(String artist, List<String> genres, String about);
}
