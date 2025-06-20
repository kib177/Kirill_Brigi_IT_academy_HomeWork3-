package by.HomeWork.interfaces;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

public interface IVoteService {
    String processVote(HttpServletRequest request);
}
