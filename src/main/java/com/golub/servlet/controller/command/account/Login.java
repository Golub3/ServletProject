package com.golub.servlet.controller.command.account;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import com.golub.servlet.controller.command.Command;
import com.golub.servlet.controller.command.CommandUtility;
import com.golub.servlet.model.entity.User;
import com.golub.servlet.model.service.UserService;
import com.golub.servlet.model.validator.UserValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.golub.servlet.controller.command.TextConstants.Parameters.*;
import static com.golub.servlet.controller.command.TextConstants.Routes.*;

/**
 * Processes logging in.
 *
 * @author Vitalii Holub
 */
public class Login implements Command {

    private static final Logger logger = LogManager.getLogger(Login.class);
    private UserService userService;

    public Login() {
        this.userService = UserService.getInstance();
    }


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        final String email = request.getParameter(EMAIL);
        final String password = request.getParameter(PASSWORD);

        if (!(UserValidator.validateEmail(email) && UserValidator.validatePassword(password))) {
            logger.info("User [" + email + "]" + " entered wrong data.");
            return LOGIN_FAIL_INVALID_INPUT;
        }

        if (userService.isExistingUser(email, password)) {
            //in order to prevent logging into one account at the same time
            if (CommandUtility.checkUserIsLogged(request, email)) {
                return MULTILOGIN_ERROR;
            }

            final User.ROLE role = userService.getRoleByEmailAndPass(email, password);
            CommandUtility.logUser(request, email, password, role);
            logger.info("User [" + email + "] role [" + role + "] signed in successfully.");
        } else {
            logger.info("Invalid attempt of login user: [" + email + "]");
            request.getSession().setAttribute(ROLE, User.ROLE.UNKNOWN);
            return USER_NOT_EXIST;
        }

        String path = request.getServletContext().getContextPath();
        return REDIRECT + path + TO_PERSONAL_CABINET;
    }

}

