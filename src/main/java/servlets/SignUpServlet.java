package servlets;

import accounts.AccountService;
import accounts.UserProfile;
import dbService.DBException;
import dbService.DBService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUpServlet extends HttpServlet {

    private final DBService dbService;

    public SignUpServlet(DBService dbService) {

        this.dbService = dbService;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String eMail = password;

        if(login == null || password == null){
/*            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("Error login/password is empty, please try again");
            response.setStatus(HttpServletResponse.SC_OK);*/

        } else {
            try {
                dbService.addUser(login,password);
            } catch (DBException e) {
                e.printStackTrace();
            }
/*            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("Ok");
            response.setStatus(HttpServletResponse.SC_OK);*/
        }


    }
}
