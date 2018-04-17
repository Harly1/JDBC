package main;


import accounts.AccountService;
import dbService.DBException;
import dbService.DBService;
import dbService.dataSets.UsersDataSet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.SignInServlet;
import servlets.SignUpServlet;

import java.util.logging.Logger;

/**
 * @author v.chibrikov
 *         <p>
 *         Пример кода для курса на https://stepic.org/
 *         <p>
 *         Описание курса и лицензия: https://github.com/vitaly-chibrikov/stepic_java_webserver
 */
public class Main {
    public static void main(String[] args) throws Exception {
        DBService dbService = new DBService();
        dbService.printConnectInfo();
        AccountService accountService = new AccountService();

  /*      accountService.addNewUser(new UserProfile("admin"));
        accountService.addNewUser(new UserProfile("test"));*/

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new SignInServlet(accountService)), "/signin");
        context.addServlet(new ServletHolder(new SignUpServlet(accountService)), "/signup");

        Server server = new Server(8080);
        server.setHandler(context);

        server.start();
        Logger.getGlobal().info("Server started");
        server.join();

        try {
            long userId = dbService.addUser("tully");
            System.out.println("Added user id: " + userId);

            UsersDataSet dataSet = dbService.getUser(userId);
            System.out.println("User data set: " + dataSet);

            dbService.cleanUp();

        } catch (DBException e) {
            e.printStackTrace();
        }
    }
}
