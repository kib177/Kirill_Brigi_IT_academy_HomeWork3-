package by.HomeWork.model;

import by.HomeWork.interfaces.IDataProvider;

import java.util.Arrays;
import java.util.List;

public class Artists implements IDataProvider {
    private final List<String> artists = Arrays.asList(
            "Taylor Swift", "The Weeknd","Drake",
            "Beyonce"
    );

    @Override
    public List<String> getValue() {
        return artists;
    }
}
