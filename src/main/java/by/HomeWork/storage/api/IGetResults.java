package by.HomeWork.storage.api;

import by.HomeWork.dto.VotingResults;

import java.time.LocalDateTime;
import java.util.Map;

public interface IGetResults<T> {
    VotingResults getAllResults();
    Map<String, Integer> getArtistResults();
    Map<String, Integer> getGenreResults();
    Map<LocalDateTime, String> getAboutResults();
}
