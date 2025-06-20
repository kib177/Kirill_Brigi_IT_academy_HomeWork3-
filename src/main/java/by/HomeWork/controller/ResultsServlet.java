package by.HomeWork.controller;

import by.HomeWork.model.VoteResult;
import by.HomeWork.service.ResultServiceImpl;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/results")
public class ResultsServlet extends HttpServlet {
    ResultServiceImpl service = new ResultServiceImpl();
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
        req.setAttribute("artistResults", service.sortResults(voteResult.getArtistResults()));
        req.setAttribute("genreResults", service.sortResults(voteResult.getGenreResults()));
        req.setAttribute("aboutResults", service.formatAboutResults(voteResult.getAboutResults()));

        req.getRequestDispatcher("results.jsp").forward(req, resp);
    }


}
