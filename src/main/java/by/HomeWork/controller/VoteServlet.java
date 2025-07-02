package by.HomeWork.controller;

import by.HomeWork.connection.modelDAO.ArtistListDAO;
import by.HomeWork.connection.modelDAO.GenresListDAO;
import by.HomeWork.connection.modelDAO.SaveVoteDAO;
import by.HomeWork.service.VoteService;
import by.HomeWork.service.api.IVoteService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static by.HomeWork.service.api.Connection.getDataSource;

@WebServlet("/vote")
public class VoteServlet extends HttpServlet {
    private static final String FORM = "form.jsp";
    private final IVoteService voteService;

    public VoteServlet() {
        this.voteService = new VoteService(
                new ArtistListDAO(getDataSource()),
                new GenresListDAO(getDataSource()),
                new SaveVoteDAO(getDataSource())
        );
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setAttribute("genres", voteService.getGenres());
        req.setAttribute("artists", voteService.getArtists());
        req.getRequestDispatcher(FORM).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String error = voteService.processVote(req);
        if (error != null) {
            req.setAttribute("error", error);
            doGet(req, resp);
            req.getRequestDispatcher(FORM).forward(req, resp);
            return;
        }
        resp.sendRedirect("results");
        }
}
