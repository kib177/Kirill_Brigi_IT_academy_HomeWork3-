package by.HomeWork.service.api;

import jakarta.servlet.http.HttpServletRequest;

public interface IVoteService {
    String processVote(HttpServletRequest request);
    void getListAtristsGenres(HttpServletRequest request);
}
