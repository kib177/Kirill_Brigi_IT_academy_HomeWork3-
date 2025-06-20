package by.HomeWork.model;

import by.HomeWork.interfaces.IDataProvider;

import java.util.Arrays;
import java.util.List;

public class Genres implements IDataProvider {
    private final List<String> genres = Arrays.asList(
            "Pop", "Rock", "Hip-Hop", "Electronic",
            "Jazz", "Classical", "Country", "Reggae",
            "Metal", "Blues"
    );

    @Override
    public List<String> getValue() {
        return genres;
    }
}
