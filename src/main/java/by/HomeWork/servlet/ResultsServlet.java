package by.HomeWork.servlet;

import by.HomeWork.model.VoteResult;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@WebServlet("/results")
public class ResultsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Получение контекста сервлета
        ServletContext context = getServletContext();
        // Извлечение объекта результатов голосования из контекста
        VoteResult voteResult = (VoteResult) context.getAttribute("voteResult");

        // Проверка инициализации результатов
        if (voteResult == null) {
            System.err.println("ERROR: VoteResult is null in context!");
            // Отправка ошибки 500 если результаты не найдены
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Voting system is not initialized");
            return; // Прерывание выполнения
        }

        // Настройка типа контента и кодировки ответа
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter(); // Получение потока вывода

        // Формирование HTML-страницы
        out.write("<html><head><title>Результаты голосования</title>" +
                "<style>body { font-family: Arial, sans-serif; margin: 20px; }</style>" +
                "</head><body>" + "<h1>Результаты голосования</h1>");

        // Раздел: результаты для исполнителей
        out.write("<h2>Лучшие исполнители:</h2>");
        displayResults(out, voteResult.getArtistResults());

        // Раздел: результаты для жанров
        out.write("<h2>Любимые жанры:</h2>");
        displayResults(out, voteResult.getGenreResults());

        // Раздел: информация "О себе"
        out.write("<h2>О себе</h2>");
        displayResultsAbout(out, voteResult.getAboutResults());

        // Ссылка для возврата на главную страницу
        out.write("<p style='margin-top: 20px;'><a href='index.html'>Вернуться к голосованию</a></p>" + "</body></html>");
    }

    // Вспомогательный метод для отображения результатов голосования
    private void displayResults(PrintWriter out, Map<String, AtomicInteger> results) {
        // Проверка на пустые данные
        if (results.isEmpty()) {
            out.write("<p>Нет данных для отображения</p>");
            return;
        }

        // Создание таблицы для отображения
        out.write("<table border='1' cellpadding='5' style='margin-bottom: 20px;'>" +
                "<tr><th>Название</th><th>Голоса</th></tr>");

        // Обработка результатов с использованием Stream API:
        results.entrySet().stream()
                // Сортировка по убыванию количества голосов
                .sorted(Map.Entry.comparingByValue(Comparator.comparingInt(AtomicInteger::get).reversed()))
                // Формирование строк таблицы
                .forEach(entry -> out.write("<tr>" +
                        "<td>" + entry.getKey() + "</td>" +
                        "<td>" + entry.getValue().get() + "</td>" + "</tr>"));
        out.write("</table>"); // Закрытие таблицы
    }

    // Метод для отображения информации "О себе"
    private void displayResultsAbout(PrintWriter out, Map<LocalDateTime, String> results) {
        if (results.isEmpty()) {
            out.write("<p>Нет данных для отображения</p>");
            // Важно: здесь отсутствует return, чтобы таблица создавалась даже при пустых данных
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Создание таблицы для информации
        out.write("<table border='1' cellpadding='5' style='margin-bottom: 20px;'>"
                + "<tr><th>Время</th><th>О себе</th></tr>"); // Исправлено: вместо "Название" - "Время"

        results.entrySet().stream()
                // Сортировка по времени (ключу - дата/время)
                .sorted(Map.Entry.comparingByKey()).forEach(e -> out.write("<tr>" +
                        "<td>" + e.getKey().format(formatter) + "</td>" + // Столбец времени
                        "<td>" + e.getValue() + "</td>" + // Столбец с информацией
                        "</tr>"));
        out.write("</table>"); // Закрытие таблицы
    }
}
