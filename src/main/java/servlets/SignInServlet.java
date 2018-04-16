package servlets;

import accounts.AccountService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignInServlet extends HttpServlet {

    private final AccountService accountService;

    public SignInServlet(AccountService accountService) {

        this.accountService = accountService;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        try {
            String loginFromBD = accountService.getUserByLogin(login).getLogin();
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(200);
            response.getWriter().println("Authorized: " + login);
//            response.setStatus(HttpServletResponse.SC_OK);

        } catch (NullPointerException e) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(401);
            response.getWriter().println("Unauthorized");
//            response.setStatus(HttpServletResponse.SC_OK);
        }
    }
}
