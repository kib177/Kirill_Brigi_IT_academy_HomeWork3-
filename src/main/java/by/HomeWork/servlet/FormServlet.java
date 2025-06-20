package by.HomeWork.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet("/form")
public class FormServlet extends HttpServlet {

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
}
