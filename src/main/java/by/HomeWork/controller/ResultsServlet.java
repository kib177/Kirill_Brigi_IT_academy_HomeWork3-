package by.HomeWork.controller;

import by.HomeWork.connection.modelDAO.GetResultsDAO;
import by.HomeWork.connection.modelDAO.api.IGetResultsDAO;
import by.HomeWork.service.ResultService;
import by.HomeWork.service.api.IResultService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/results")
public class ResultsServlet extends HttpServlet {
    IGetResultsDAO resultsDAO = new GetResultsDAO();
    IResultService resultService = new ResultService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setAttribute("artistResults", resultService.sortResults(resultsDAO.getArtistResults()));
        req.setAttribute("genreResults", resultService.sortResults(resultsDAO.getGenreResults()));
        req.setAttribute("aboutResults", resultService.formatAboutResults(resultsDAO.getAboutResults()));

        req.getRequestDispatcher("results.jsp").forward(req, resp);
    }


}
