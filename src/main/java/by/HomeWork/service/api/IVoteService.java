package by.HomeWork.service.api;

import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface IVoteService {
    String processVote(HttpServletRequest request);
    List<String> getGenres();
    List<String> getArtists();


}
