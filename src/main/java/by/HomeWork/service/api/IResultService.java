package by.HomeWork.service.api;

import java.time.LocalDateTime;
import java.util.Map;

public interface IResultService {
    Map<String, Integer> sortResults(Map<String, Integer> source);
    Map<String, String> formatAboutResults(Map<LocalDateTime, String> source);
}
