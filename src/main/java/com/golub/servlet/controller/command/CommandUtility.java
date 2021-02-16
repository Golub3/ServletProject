package com.golub.servlet.controller.command;

import com.golub.servlet.model.entity.User;
import com.golub.servlet.model.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;

import static com.golub.servlet.controller.command.TextConstants.Parameters.EMAIL;
import static com.golub.servlet.controller.command.TextConstants.Parameters.LOGGED_USERS;
import static com.golub.servlet.controller.command.TextConstants.Parameters.PASSWORD;
import static com.golub.servlet.controller.command.TextConstants.Parameters.ROLE;
import static com.golub.servlet.controller.command.TextConstants.Routes.INVALID_SESSION_ERROR;


/**
 * This class realize some utility logic
 * for manipulation with commands.
 *
 * @author Vitalii Holub
 */
public class CommandUtility {

    private static UserService userService;

    static {
        userService = UserService.getInstance();
    }


    /**
     * This is the executing certain command method
     * which provides the concrete logic for each
     * class that implements it.
     *
     * @param request HttpServletRequest.
     * @param email String.
     *
     */
    public static boolean checkUserIsLogged(HttpServletRequest request, String email){

        @SuppressWarnings("unchecked")
        HashSet<String> loggedUsers = (HashSet<String>) request.getSession()
                .getServletContext()
                .getAttribute(LOGGED_USERS);


        if(loggedUsers.stream().anyMatch(email::equals)) {
            return true;
        }

        loggedUsers.add(email);
        request.getSession()
                .getServletContext()
                .setAttribute(LOGGED_USERS, loggedUsers);
        return false;
    }


    public static void logUser(HttpServletRequest request, String email, String password, User.ROLE role){
        request.getSession().setAttribute(PASSWORD, password);
        request.getSession().setAttribute(EMAIL, email);
        request.getSession().setAttribute(ROLE, role);
    }

    public static void logoutUser(HttpServletRequest request, String email) {

        @SuppressWarnings("unchecked")
        HashSet<String> loggedUsers = (HashSet<String>)
                request.getSession().getServletContext().getAttribute(LOGGED_USERS);

        loggedUsers.remove(email);
        request.getSession().getServletContext().setAttribute(LOGGED_USERS, loggedUsers);

        final HttpSession session = request.getSession();
        session.removeAttribute(EMAIL);
        session.removeAttribute(PASSWORD);
        session.removeAttribute(ROLE);
    }


    /**
     * Obtain user from current session.
     *
     * @param request HttpServletRequest.
     */
    public static User getCurrentSessionUser(HttpServletRequest request){

        final HttpSession session = request.getSession();
        String email = session.getAttribute(EMAIL).toString();

        UserService userService = UserService.getInstance();
        return userService.getUserByEmail(email);
    }


    /**
     * This method is invoked in commands which need to disallow using cache.
     * It solves the issue when user could logged out and than return
     * to its personal cabinet by clicking "back" button in browser.
     *
     * @param request HttpServletRequest.
     * @param response HttpServletResponse.
     */
    //to prevent user coming back to cached pages after logout
    public static void disallowBackToCached(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        final HttpSession session = request.getSession();
        final String path = request.getServletContext().getContextPath();

        //to prevent user coming back to cached pages after logout
        response.setHeader("Cache-Control","no-cache, no-store, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma","no-cache");
        response.setDateHeader ("Expires", 0);
        if (session.getAttribute(EMAIL) == null || session.getAttribute(PASSWORD) == null
                || session.getAttribute(ROLE) == null) {
            response.sendRedirect(path +  INVALID_SESSION_ERROR);
        }
    }

}


