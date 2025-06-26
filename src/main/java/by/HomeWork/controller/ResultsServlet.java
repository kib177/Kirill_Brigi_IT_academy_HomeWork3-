package by.HomeWork.controller;

import by.HomeWork.connection.PostgresVote;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/results")
public class ResultsServlet extends HttpServlet {
    PostgresVote postgresVote = new PostgresVote();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setAttribute("artistResults", postgresVote.getArtistResults());
        req.setAttribute("genreResults", postgresVote.getGenreResults());
        req.setAttribute("aboutResults", postgresVote.getAboutResults());

        req.getRequestDispatcher("results.jsp").forward(req, resp);
    }


}
