package by.HomeWork.servlet;

import by.HomeWork.service.VoteService;
import by.HomeWork.model.VoteResult;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/vote")
public class VoteServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Получение контекста сервлета для доступа к данным уровня приложения
        ServletContext context = getServletContext();
        // Извлечение объекта результатов голосования из контекста
        VoteResult voteResult = (VoteResult) context.getAttribute("voteResult");
        // Создание сервиса для обработки голоса
        VoteService voteService = new VoteService(voteResult);

        // Проверка инициализации системы голосования
        if (voteResult == null) {
            // Отправка клиенту HTTP-ошибки 500 (Internal Server Error)
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    "Voting system is not initialized");
            return;
        }

        // Обработка голоса и проверка результата
        if (voteService.processVote(req) != null) {
            // Установка атрибута ошибки для передачи на HTML страницу
            req.setAttribute("error", voteService.processVote(req));
            // Перенаправление обратно на страницу голосования с сообщением об ошибке
            req.getRequestDispatcher("index.html").forward(req, resp);
        }
        resp.sendRedirect("results");
    }
}
