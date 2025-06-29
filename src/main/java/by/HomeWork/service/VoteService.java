package by.HomeWork.service;

import by.HomeWork.connection.modelDAO.SaveVoteDAO;
import by.HomeWork.connection.modelDAO.api.IArtistsListDAO;
import by.HomeWork.connection.modelDAO.api.IGenreListDAO;
import by.HomeWork.connection.modelDAO.api.ISaveVoteDAO;
import by.HomeWork.connection.modelDAO.ArtistListListDAO;
import by.HomeWork.connection.modelDAO.GenresListDAO;
import by.HomeWork.service.api.IVoteService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Arrays;

public class VoteService implements IVoteService {
    ISaveVoteDAO saveVoteDAO = new SaveVoteDAO();

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
    public void getList(HttpServletRequest req){
        IArtistsListDAO getArtists = new ArtistListListDAO();
        IGenreListDAO getGenres = new GenresListDAO();

        req.setAttribute("genres", getGenres.getListForVote());
        req.setAttribute("artists", getArtists.getListForVote());
    }

}
