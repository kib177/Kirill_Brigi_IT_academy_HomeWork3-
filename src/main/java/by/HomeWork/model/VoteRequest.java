package by.HomeWork.model;

import java.util.List;

public class VoteRequest {
    private String artist;
    private List<String> genres;
    private String about;


    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getArtist() {
        return artist;
    }

    public List<String> getGenres() {
        return genres;
    }

    public String getAbout() {
        return about;
    }
}
