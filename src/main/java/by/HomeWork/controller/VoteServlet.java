package by.HomeWork.controller;

import by.HomeWork.service.VoteService;
import by.HomeWork.service.api.IVoteService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/vote")
public class VoteServlet extends HttpServlet {
    private static final String FORM = "form.jsp";
    IVoteService voteService = new VoteService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        voteService.getListAtristsGenres(req);
        req.getRequestDispatcher(FORM).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String error = voteService.processVote(req);
        if (error != null) {
            req.setAttribute("error", error);
            voteService.getListAtristsGenres(req);
            req.getRequestDispatcher(FORM).forward(req, resp);
            return;
        }
        resp.sendRedirect("results");
        }
}
