package by.HomeWork.service;

import by.HomeWork.connection.modelDAO.SaveVoteDAO;
import by.HomeWork.connection.modelDAO.api.ISaveVoteDAO;
import by.HomeWork.connection.modelDAO.api.IgetListForVoteDAO;
import by.HomeWork.connection.modelDAO.artistListForVoteDAO;
import by.HomeWork.connection.modelDAO.genresListForVoteDAO;
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

        saveVoteDAO.saveVote(artist,
                Arrays.asList(genres),
                about);
        return null;
    }

    @Override
    public void getListAtristsGenres(HttpServletRequest req){
        IgetListForVoteDAO getArtists = new artistListForVoteDAO();
        IgetListForVoteDAO getGenres = new genresListForVoteDAO();

        req.setAttribute("artists", getArtists.getListForVote());
        req.setAttribute("genres", getGenres.getListForVote());
    }
}
