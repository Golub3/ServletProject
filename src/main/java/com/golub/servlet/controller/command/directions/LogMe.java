package com.golub.servlet.controller.command.directions;



import com.golub.servlet.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.golub.servlet.controller.command.TextConstants.Routes.TO_LOGIN;

/**
 * This class is responsible for forwarding
 * to logging page from home page.
 *
 * @author Vitalii Holub
 */

public class LogMe implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        return TO_LOGIN;
    }
}
