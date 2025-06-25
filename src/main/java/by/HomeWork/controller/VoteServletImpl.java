package by.HomeWork.controller;

import by.HomeWork.connection.PostgreGet;
import by.HomeWork.model.Artists;
import by.HomeWork.model.Genres;
import by.HomeWork.service.VoteServiceImpl;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/vote")
public class VoteServletImpl extends HttpServlet {
    Artists artists = new Artists();
    Genres genres = new Genres();
    private static final String FORM = "form.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        PostgreGet get = new PostgreGet();
        req.setAttribute("artists", get.getArtists());
        req.setAttribute("genres", get.getGenres());

        req.getRequestDispatcher(FORM).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        VoteServiceImpl v = new VoteServiceImpl();
        String error = v.processVote(req);
        if (error != null) {
            req.setAttribute("error", error);
            req.getRequestDispatcher(FORM).forward(req, resp);
            return;
        }
        resp.sendRedirect("results");


    }
}
