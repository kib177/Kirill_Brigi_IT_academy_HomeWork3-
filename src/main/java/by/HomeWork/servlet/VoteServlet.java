package by.HomeWork.servlet;

import by.HomeWork.model.VoteResult;
import by.HomeWork.service.VoteService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet("/vote")
public class VoteServlet extends HttpServlet {

    private final List<String> artists = Arrays.asList(
            "Taylor Swift", "The Weeknd","Drake",
            "Beyonce"
    );

    private final List<String> genres = Arrays.asList(
            "Pop", "Rock", "Hip-Hop", "Electronic",
            "Jazz", "Classical", "Country", "Reggae",
            "Metal", "Blues"
    );

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Передаем списки в JSP
        req.setAttribute("artists", artists);
        req.setAttribute("genres", genres);

        req.getRequestDispatcher("form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        ServletContext context = getServletContext();
        VoteResult voteResult = (VoteResult) context.getAttribute("voteResult");
        VoteService voteService = new VoteService(voteResult);

        if (voteResult == null) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    "Voting system is not initialized");
            return;
        }

        String error = voteService.processVote(req);
        if (error != null) {
            req.setAttribute("error", error);
            req.getRequestDispatcher("form.jsp").forward(req, resp);
            return;
        }
        resp.sendRedirect("results");
    }
}
