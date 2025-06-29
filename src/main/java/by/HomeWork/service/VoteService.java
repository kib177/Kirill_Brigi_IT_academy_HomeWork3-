package by.HomeWork.service;

import by.HomeWork.connection.modelDAO.api.IArtistDAO;
import by.HomeWork.connection.modelDAO.api.IGenreDAO;
import by.HomeWork.connection.modelDAO.api.ISaveVoteDAO;
import by.HomeWork.service.api.IVoteService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Arrays;

public class VoteService implements IVoteService {
    private final ISaveVoteDAO saveVoteDAO;
    private final IArtistDAO artistDAO;
    private final IGenreDAO genreDAO;

    public VoteService(ISaveVoteDAO saveVoteDAO,
                       IArtistDAO artistDAO,
                       IGenreDAO genreDAO) {
        this.saveVoteDAO = saveVoteDAO;
        this.artistDAO = artistDAO;
        this.genreDAO = genreDAO;
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

        if (Arrays.stream(genres).anyMatch(String::isBlank)) {
            return "Некорректные значения жанров";
        }

        saveVoteDAO.saveVote(artist,
                Arrays.asList(genres),
                about);
        return null;
    }

    @Override
    public void getListAtristsGenres(HttpServletRequest req){
        req.setAttribute("artists", artistDAO.getListForVote());
        req.setAttribute("genres", genreDAO.getListForVote());
    }
}
