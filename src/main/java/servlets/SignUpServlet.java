package servlets;

import accounts.AccountService;
import accounts.UserProfile;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUpServlet extends HttpServlet {

    private final AccountService accountService;

    public SignUpServlet(AccountService accountService) {

        this.accountService = accountService;
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
            accountService.addNewUser(new UserProfile(login,password,eMail));
/*            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("Ok");
            response.setStatus(HttpServletResponse.SC_OK);*/
        }


    }
}
