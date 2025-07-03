package by.HomeWork.dto;

import java.time.LocalDateTime;
import java.util.Map;

public class VotingResults {
    private final Map<String, Integer> artistVotes;
    private final Map<String, Integer> genreVotes;
    private final Map<LocalDateTime, String> aboutInfo;

    public VotingResults(
            Map<String, Integer> artistVotes,
            Map<String, Integer> genreVotes,
            Map<LocalDateTime, String> aboutInfo
    ) {
        this.artistVotes = artistVotes;
        this.genreVotes = genreVotes;
        this.aboutInfo = aboutInfo;
    }

    public Map<String, Integer> getArtistVotes() {
        return artistVotes;
    }

    public Map<String, Integer> getGenreVotes() {
        return genreVotes;
    }

    public Map<LocalDateTime, String> getAboutInfo() {
        return aboutInfo;
    }

    public static class Builder {
        private Map<String, Integer> artistVotes;
        private Map<String, Integer> genreVotes;
        private Map<LocalDateTime, String> aboutInfo;

        public Builder artistVotes(Map<String, Integer> artistVotes) {
            this.artistVotes = artistVotes;
            return this;
        }

        public Builder genreVotes(Map<String, Integer> genreVotes) {
            this.genreVotes = genreVotes;
            return this;
        }

        public Builder aboutInfo( Map<LocalDateTime, String> aboutInfo) {
            this.aboutInfo = aboutInfo;
            return this;
        }

        public VotingResults build() {
            return new VotingResults(artistVotes, genreVotes, aboutInfo);
        }
    }
}

