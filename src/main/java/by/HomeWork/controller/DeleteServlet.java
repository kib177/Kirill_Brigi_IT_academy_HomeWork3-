package by.HomeWork.controller;

import by.HomeWork.service.DeleteService;
import by.HomeWork.service.api.IDeleteService;
import by.HomeWork.storage.DeleteVotes;
import by.HomeWork.storage.GetListDelete;
import by.HomeWork.storage.GetResults;
import by.HomeWork.storage.api.IGetResults;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static by.HomeWork.service.api.Connection.getDataSource;

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {

    IDeleteService deleteService = new DeleteService();
    GetListDelete delete = new GetListDelete(getDataSource());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setAttribute("aboutDelete", delete.getAboutList());
        req.getRequestDispatcher("deleteVote.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {

        if(deleteService.deleteAllVote(req)){
            resp.sendRedirect("results");
        } else {
            req.setAttribute("error", "вы не выбрали действие");
            doGet(req, resp);
        }



    }
}
