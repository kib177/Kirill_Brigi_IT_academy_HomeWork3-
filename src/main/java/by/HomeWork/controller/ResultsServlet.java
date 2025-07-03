package by.HomeWork.controller;

import by.HomeWork.dto.VotingResults;
import by.HomeWork.storage.GetResults;
import by.HomeWork.storage.api.IGetResults;
import by.HomeWork.service.ResultService;
import by.HomeWork.service.api.IResultService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.HomeWork.service.api.Connection.getDataSource;

@WebServlet("/results")
public class ResultsServlet extends HttpServlet {
    IGetResults resultsDAO = new GetResults(getDataSource());
    IResultService resultService = new ResultService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        VotingResults results = resultsDAO.getAllResults();

        req.setAttribute("artistResults", resultService.sortResults(results.getArtistVotes()));
        req.setAttribute("genreResults", resultService.sortResults(results.getGenreVotes()));
        req.setAttribute("aboutResults", resultService.formatAboutResults(results.getAboutInfo()));

        req.getRequestDispatcher("results.jsp").forward(req, resp);
    }


}
