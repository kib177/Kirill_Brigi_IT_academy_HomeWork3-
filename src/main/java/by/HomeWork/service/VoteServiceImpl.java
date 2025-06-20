package by.HomeWork.service;

import by.HomeWork.interfaces.IVoteService;
import by.HomeWork.model.VoteResult;
import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalDateTime;

public class VoteServiceImpl implements IVoteService {
    private final VoteResult voteResult;

    public VoteServiceImpl(VoteResult voteResult) {
        this.voteResult = voteResult;
    }
    @Override
    public String processVote(HttpServletRequest req){
        String artist = req.getParameter("artist");
        String[] genres = req.getParameterValues("genre");
        String about = req.getParameter("about");

        if (artist == null) {
            return "Выберите исполнителя!";
        }

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
