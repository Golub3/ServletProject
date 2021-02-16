package com.golub.servlet.controller.command.account;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import com.golub.servlet.controller.command.Command;
import com.golub.servlet.model.entity.User;
import com.golub.servlet.model.exception.AlreadyExistingDBRecordException;
import com.golub.servlet.model.service.UserService;
import com.golub.servlet.model.validator.UserValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.math.BigDecimal;

import static com.golub.servlet.controller.command.TextConstants.Parameters.*;
import static com.golub.servlet.controller.command.TextConstants.Routes.*;


/**
 * Processes registration.
 *
 * @author Vitalii Holub
 */

public class Registration implements Command {
    private static final Logger logger = LogManager.getLogger(Registration.class);

    private UserService userService;

    public Registration() {
        this.userService = UserService.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        final String email = request.getParameter(EMAIL);
        final String role = request.getParameter(ROLE);
        final String password = request.getParameter(PASSWORD);
        final String confirmPassword = request.getParameter(CONFIRM_PASSWORD);
        final String firstName = request.getParameter(FIRST_NAME);
        final String lastName = request.getParameter(LAST_NAME);

        if (!(UserValidator.validateEmail(email) && UserValidator.validatePassword(password))) {
            logger.info("User [" + email + "]" + " entered invalid data.");
            return REGISTRATION_FAIL_INVALID_DATA;
        }

        if (!password.equals(confirmPassword)) {
            logger.info("User [" + email + "]" + " password and its confirmation are not equal.");
            return REGISTRATION_FAIL_PASSWORDS_DIFFERENT;
        }

        User user = accomplishNewUser(email, password, firstName, lastName);

        try {
            userService.registerUserAccount(user);
        } catch (AlreadyExistingDBRecordException e) {
            e.printStackTrace();
            logger.info(e.getMessage());
            return REGISTRATION_FAIL_USER_EXIST;
        }

        logger.info("User [" + email + "]" + " role[" + "USER" + "]" + " successfully registered.");
        return REGISTRATION_SUCCESS;
    }

    private User accomplishNewUser(String email, String password, String firstName, String lastName){
        final User user = new User();
        user.setRole(User.ROLE.USER);
        user.setPassword(userService.encodePassword(password));
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);

        return user;
    }

}
