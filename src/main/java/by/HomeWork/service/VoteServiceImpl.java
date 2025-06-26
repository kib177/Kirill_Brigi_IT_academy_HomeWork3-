package by.HomeWork.service;

import by.HomeWork.connection.PostgreGet;
import by.HomeWork.connection.PostgresVote;
import by.HomeWork.interfaces.IVoteService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Arrays;

public class VoteServiceImpl implements IVoteService {

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

        PostgresVote postgresVote = new PostgresVote();
        postgresVote.saveVote(artist,
                Arrays.asList(genres),
                about);
        return null;
    }

    public static void getListAtristsGenres(HttpServletRequest req){
        PostgreGet get = new PostgreGet();
        req.setAttribute("artists", get.getArtists());
        req.setAttribute("genres", get.getGenres());
    }
}
