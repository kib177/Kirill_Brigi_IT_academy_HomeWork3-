package by.HomeWork.service;

import by.HomeWork.interfaces.IResultService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class ResultServiceImpl implements IResultService {

    @Override
    public Map<String, Integer> sortResults(Map<String, AtomicInteger> source) {
        Map<String, Integer> result = new LinkedHashMap<>();
        source.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(
                        Comparator.comparingInt(AtomicInteger::get).reversed()
                ))
                .forEach(e -> result.put(e.getKey(), e.getValue().get()));
        return result;
    }

    @Override
    public Map<String, String> formatAboutResults(Map<LocalDateTime, String> source) {
        Map<String, String> result = new LinkedHashMap<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        source.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(e -> result.put(
                        e.getKey().format(formatter),
                        e.getValue()
                ));
        return result;
    }
}
