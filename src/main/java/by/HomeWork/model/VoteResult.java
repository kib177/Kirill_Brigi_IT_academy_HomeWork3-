package by.HomeWork.model;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class VoteResult {
    private final Map<String, AtomicInteger> artistResults = new ConcurrentHashMap<>();
    private final Map<String, AtomicInteger> genreResults = new ConcurrentHashMap<>();
    private final Map<LocalDateTime, String> aboutResults = new HashMap<>();

    public void addArtistVote(String artist) {
        artistResults.computeIfAbsent(artist, k -> new AtomicInteger(0)).incrementAndGet();
    }

    public void addGenreVote(String genre) {
        genreResults.computeIfAbsent(genre, k -> new AtomicInteger(0)).incrementAndGet();
    }

    public void addAboutVote(LocalDateTime time, String about) {
        aboutResults.put(time, about);
    }

    public Map<LocalDateTime, String> getAboutResults() {
        return aboutResults;
    }

    public Map<String, AtomicInteger> getArtistResults() {
        return artistResults;
    }

    public Map<String, AtomicInteger> getGenreResults() {
        return genreResults;
    }
}