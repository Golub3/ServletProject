package com.golub.servlet.controller.command.account;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import com.golub.servlet.controller.command.Command;
import com.golub.servlet.controller.command.CommandUtility;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.golub.servlet.controller.command.TextConstants.Routes.REDIRECT;


/**
 * Processes logging out.
 * @author Vitalii Holub
 */
public class Logout implements Command {
    private static final Logger logger = LogManager.getLogger(Logout.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        final String email = (String)request.getSession().getAttribute("email");
        CommandUtility.logoutUser(request, email);
        logger.info("User [" + email + "] " + "logged out." );

        String path = request.getServletContext().getContextPath();
        return REDIRECT + path + "/";
    }
}
