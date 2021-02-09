package com.golub.servlet.controller;

import com.golub.servlet.controller.command.Command;
import com.golub.servlet.controller.command.account.Login;
import com.golub.servlet.controller.command.account.Logout;
import com.golub.servlet.controller.command.account.PersonalCabinet;
import com.golub.servlet.controller.command.account.Registration;
import com.golub.servlet.controller.command.actions.*;
import com.golub.servlet.controller.command.directions.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.golub.servlet.controller.command.TextConstants.CommandPaths.*;
import static com.golub.servlet.controller.command.TextConstants.Routes.*;

/**
 * <h1>Final project</h1>
 *
 * @author  Vitalii Holub
 * @version 1.0
 */


public class Servlet extends HttpServlet {
    private Map<String, Command> commands = new HashMap<>();


    public void init(ServletConfig servletConfig){

        //account
        commands.put(REGISTRATION,
                new Registration());
        commands.put(LOGIN,
                new Login());
        commands.put(LOGOUT,
                new Logout());
        commands.put(PERSONAL_CABINET,
                new PersonalCabinet());


        //directions
        commands.put(HOME,
                new Home());
        commands.put(REG_ME,
                new RegMe());
        commands.put(LOG_ME,
                new LogMe());
        commands.put(SCHEDULE_CREATE_PAGE,
                new ToScheduleCreate());

        //actions
        commands.put(SHOW_USERS,
                new ShowUsers());
        commands.put(SHOW_SCHEDULES,
                new ShowSchedules());
        commands.put(TICKET_BUY,
                new TicketBuy());
        commands.put(SCHEDULE_DELETE,
                new ScheduleDelete());
        commands.put(SCHEDULE_CREATE,
                new ScheduleCreate());
    }



    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    /**
     * This is the working method.
     * It obtains the path from Command->execute() method
     * If path contains "redirect@" it truncates it at send redirect
     * else it makes forward.
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return Nothing.
     */
    private void processRequest(HttpServletRequest request,
                                HttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getRequestURI();
        path = path.replaceAll(APPLICATION_PATH_REGEX , EMPTY_STRING);

        Command command = commands.getOrDefault(path, (req, resp)->DEFAULT_PATH);
        String page = command.execute(request, response);

        if (page.contains(REDIRECT)) {
            response.sendRedirect(page.replace(REDIRECT, EMPTY_STRING));
        } else {
            request.getRequestDispatcher(page).forward(request,response);
        }
    }
}
