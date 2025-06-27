package by.HomeWork.connection.modelDAO.api;

import java.time.LocalDateTime;
import java.util.Map;

public interface IGetResultsDAO {
    Map<String, Integer> getArtistResults();
    Map<String, Integer> getGenreResults();
    Map<LocalDateTime, String> getAboutResults();
}
