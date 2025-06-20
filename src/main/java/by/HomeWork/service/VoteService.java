package by.HomeWork.service;

import by.HomeWork.model.VoteResult;
import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalDateTime;

public class VoteService {
    private final VoteResult voteResult;

    public VoteService(VoteResult voteResult) {
        this.voteResult = voteResult;
    }

    public String processVote(HttpServletRequest req) {
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
            voteResult.addArtistVote(artist);

            for (String genre : genres) {
                voteResult.addGenreVote(genre);
            }
            voteResult.addAboutVote(LocalDateTime.now(), about);
        }

        return null;
    }
}
