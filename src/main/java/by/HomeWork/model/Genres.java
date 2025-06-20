package by.HomeWork.model;

import java.util.Arrays;
import java.util.List;

public class Genres {
    private final List<String> genres = Arrays.asList(
            "Pop", "Rock", "Hip-Hop", "Electronic",
            "Jazz", "Classical", "Country", "Reggae",
            "Metal", "Blues"
    );

    public List<String> getGenres() {
        return genres;
    }
}
