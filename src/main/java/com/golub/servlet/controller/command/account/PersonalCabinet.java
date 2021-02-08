package com.golub.servlet.controller.command.account;

import com.golub.servlet.controller.command.Command;
import com.golub.servlet.controller.command.CommandUtility;
import com.golub.servlet.model.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.golub.servlet.controller.command.TextConstants.Parameters.*;
import static com.golub.servlet.controller.command.TextConstants.Parameters.ROLE;
import static com.golub.servlet.controller.command.TextConstants.Routes.*;
import static com.golub.servlet.controller.command.TextConstants.Routes.BASE;
import static com.golub.servlet.controller.command.TextConstants.Routes.USER_NOT_EXIST;

/**
 * Move user to menu.
 * If access 'admin' move to admin menu.
 * If access 'user' move to user menu.
 *
 * @author Vitalii Holub
 */
public class PersonalCabinet implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        final HttpSession session = request.getSession();
        final User.ROLE role = (User.ROLE) session.getAttribute(ROLE);

        if ( session.getAttribute(ROLE) != User.ROLE.UNKNOWN) {
            //to prevent user coming back to cached pages after logout by clicking "back arrow" in browser
            CommandUtility.disallowBackToCached(request, response);
        }

        if (role.equals(User.ROLE.ADMIN) || role.equals(User.ROLE.USER)) {
            return BASE;
        } else {
            return USER_NOT_EXIST;
        }
    }
}
