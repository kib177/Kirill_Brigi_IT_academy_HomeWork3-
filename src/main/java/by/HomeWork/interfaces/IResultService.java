package by.HomeWork.interfaces;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public interface IResultService {
    Map<String, Integer> sortResults(Map<String, AtomicInteger> source);
    Map<String, String> formatAboutResults(Map<LocalDateTime, String> source);

}
