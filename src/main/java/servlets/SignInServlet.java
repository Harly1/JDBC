package servlets;

import dbService.DBException;
import dbService.DBService;
import dbService.dataSets.UsersDataSet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignInServlet extends HttpServlet {

    private final DBService dbService;

    public SignInServlet(DBService dbService) {

        this.dbService = dbService;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        try {
//            String loginFromBD = accountService.getUserByLogin(login).getLogin();
            String loginFromBD = dbService.getUser(login);
            String PasswordFromBD = dbService.getUserPassword(password);
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(200);
            response.getWriter().println("Authorized: " + login);
//            response.setStatus(HttpServletResponse.SC_OK);

        } catch (NullPointerException | DBException e) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(401);
            response.getWriter().println("Unauthorized");
//            response.setStatus(HttpServletResponse.SC_OK);
        }
    }
}
