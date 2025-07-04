package by.HomeWork.service;

import by.HomeWork.service.api.IDeleteService;
import by.HomeWork.storage.DeleteVotes;
import jakarta.servlet.http.HttpServletRequest;

import static by.HomeWork.service.api.Connection.getDataSource;

public class DeleteService implements IDeleteService {

    @Override
    public boolean deleteAllVote(HttpServletRequest req) {
        DeleteVotes deleteVotes = new DeleteVotes(getDataSource());

        String Value1 = req.getParameter("deleteAll");
        String Value2 = req.getParameter("deleteAll");


        if (Value1 != null) {
            deleteVotes.deleteAllVotes();
            return true;
        }else if (Value2 != null) {

        }
        else {
            return false;
        }
    }
}
