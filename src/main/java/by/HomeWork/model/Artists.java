package by.HomeWork.model;

import java.util.Arrays;
import java.util.List;

public class Artists {
    private final List<String> artists = Arrays.asList(
            "Taylor Swift", "The Weeknd","Drake",
            "Beyonce"
    );

    public List<String> getArtists() {
        return artists;
    }
}
