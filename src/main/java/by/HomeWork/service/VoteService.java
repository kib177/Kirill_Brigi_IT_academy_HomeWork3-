package by.HomeWork.service;

import by.HomeWork.model.VoteResult;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.time.LocalDateTime;

public class VoteService {
    private final VoteResult voteResult;

    public VoteService(VoteResult voteResult) {
        this.voteResult = voteResult;
    }

    public String processVote(HttpServletRequest req) throws ServletException, IOException {
        String artist = req.getParameter("artist");
        String[] genres = req.getParameterValues("genre");
        String about = req.getParameter("about");

       // проверка выбора исполнителя
        if (artist == null) {
            return "Выберите исполнителя!";
        }

        // проверка выбора жанра
        if (genres == null || genres.length < 3 || genres.length > 5) {
            return "Пожалуйста, выберите от 3 до 5 жанров!";
        }

        synchronized (voteResult) {
            // Добавляем голос за исполнителя
            voteResult.addArtistVote(artist);

            // Добавляем голоса за жанры
            for (String genre : genres) {
                voteResult.addGenreVote(genre);
            }
            // заполняем о себе и привязываем дату
            voteResult.addAboutVote(LocalDateTime.now(), about);
        }

        return null; // Успешная обработка
    }
}
