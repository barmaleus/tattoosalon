package by.rekuts.tattoosalon.servlet;

import by.rekuts.tattoosalon.command.ActionCommand;
import by.rekuts.tattoosalon.command.ActionFactory;
import by.rekuts.tattoosalon.db.ConnectionPool;
import by.rekuts.tattoosalon.resource.ConfigurationManager;
import by.rekuts.tattoosalon.resource.MessageManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends javax.servlet.http.HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.getRequestDispatcher("page/main.jsp").forward(request, response);
        processRequest(request, response);
    }
    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String page;
        // define the command that came from the JSP
        ActionFactory client = new ActionFactory();
        ActionCommand command = client.defineCommand(request);
        /*
         * call the implemented execute () method and pass parameters
         * to class-processor of a specific command
        */
        page = command.execute(request);
        // method returns a response page
        if (page != null) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            // call a response page to the request
            dispatcher.forward(request, response);
        } else {
            // setting up a page with an error message
            page = ConfigurationManager.getProperty("path.page.index");
            request.getSession().setAttribute("nullPage", MessageManager.getProperty("message.nullpage"));
            response.sendRedirect(request.getContextPath() + page);
        }
    }

    @Override
    public void destroy() {
        ConnectionPool.getInstance().closeConnectionsInPool();
    }
}
