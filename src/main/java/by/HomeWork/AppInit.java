package by.HomeWork;

import by.HomeWork.model.VoteResult;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class AppInit implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        VoteResult voteResult = new VoteResult();
        sce.getServletContext().setAttribute("voteResult", voteResult);
    }
  
}
