package by.HomeWork.servlet;

import by.HomeWork.service.VoteService;
import by.HomeWork.model.VoteResult;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/vote")
public class VoteServlet extends HttpServlet {
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
