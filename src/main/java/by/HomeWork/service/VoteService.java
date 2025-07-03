package by.HomeWork.service;

import by.HomeWork.storage.storageGetListForForm.api.IArtistsList;
import by.HomeWork.storage.storageGetListForForm.api.IGenreList;
import by.HomeWork.storage.api.ISaveVote;
import by.HomeWork.service.api.IVoteService;
import by.HomeWork.service.api.exception.StorageException;
import jakarta.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VoteService implements IVoteService {
    private final IArtistsList artistDAO;
    private final IGenreList genreDAO;
    private final ISaveVote SaveVoteDAO;
    private final List<String> validGenres = new ArrayList<>();
    private final List<String> validArtists = new ArrayList<>();

    public VoteService(IArtistsList artistDAO, IGenreList genreDAO, ISaveVote SaveVoteDAO) {
        this.artistDAO = artistDAO;
        this.genreDAO = genreDAO;
        this.SaveVoteDAO = SaveVoteDAO;
        initializeValidData();
    }

    private void initializeValidData() {
        validArtists.addAll(artistDAO.getListForVote());
        validGenres.addAll(genreDAO.getListForVote());
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

        try {
            SaveVoteDAO.saveVote(artist, Arrays.asList(genres), about);
            return null;
        } catch (StorageException e) {
            return "Ошибка сохранения данных";
        }
    }

    @Override
    public List<String> getGenres() {
        return validGenres;
    }

    @Override
    public List<String> getArtists() {
        return validArtists;
    }

}
