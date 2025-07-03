package by.HomeWork.storage.api;

import java.util.List;

public interface ISaveVote {
    void saveVote(String artist, List<String> genres, String about);
}
