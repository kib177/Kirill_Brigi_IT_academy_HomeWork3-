package by.HomeWork.service;

import by.HomeWork.service.api.IResultService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ResultService implements IResultService {

    @Override
    public Map<String, Integer> sortResults(Map<String, Integer> source) {
        return transformMap(
                source,
                Comparator.comparing(e -> e.getValue(), Comparator.reverseOrder()),
                Map.Entry::getKey,
                e -> e.getValue()
        );
    }

    @Override
    public Map<String, String> formatAboutResults(Map<LocalDateTime, String> source) {
        return transformMap(
                source,
                Map.Entry.comparingByKey(),
                e -> e.getKey().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                Map.Entry::getValue
        );
    }

    private <K, V, R, S> Map<R, S> transformMap(
            Map<K, V> source,
            Comparator<Map.Entry<K, V>> comparator,
            Function<Map.Entry<K, V>, R> keyExtractor,
            Function<Map.Entry<K, V>, S> valueExtractor
    ) {
        if (source == null || source.isEmpty()) {
            return new LinkedHashMap<>();
        }

        return source.entrySet().stream()
                .sorted(comparator)
                .collect(Collectors.toMap(
                        keyExtractor,
                        valueExtractor,
                        (existing, replacement) -> existing,
                        LinkedHashMap::new
                ));
    }
}
