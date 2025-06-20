package by.HomeWork.controller;

import by.HomeWork.model.VoteResult;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import static by.HomeWork.service.ResultService.formatAboutResults;
import static by.HomeWork.service.ResultService.sortResults;

@WebServlet("/results")
public class ResultsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        ServletContext context = getServletContext();
        VoteResult voteResult = (VoteResult) context.getAttribute("voteResult");

        if (voteResult == null) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    "Voting system is not initialized");
            return;
        }
        req.setAttribute("artistResults", sortResults(voteResult.getArtistResults()));
        req.setAttribute("genreResults", sortResults(voteResult.getGenreResults()));
        req.setAttribute("aboutResults", formatAboutResults(voteResult.getAboutResults()));

        req.getRequestDispatcher("results.jsp").forward(req, resp);
    }


}
