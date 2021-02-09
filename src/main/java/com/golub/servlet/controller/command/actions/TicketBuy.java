package com.golub.servlet.controller.command.actions;

import com.golub.servlet.controller.command.Command;
import com.golub.servlet.model.service.ExpositionService;
import com.golub.servlet.model.service.TicketService;
import com.golub.servlet.model.service.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.golub.servlet.controller.command.TextConstants.CommandPaths.SHOW_SCHEDULES;
import static com.golub.servlet.controller.command.TextConstants.Parameters.BALANCE;
import static com.golub.servlet.controller.command.TextConstants.Parameters.EMAIL;
import static com.golub.servlet.controller.command.TextConstants.Routes.REDIRECT;
import static com.golub.servlet.controller.command.TextConstants.Routes.TO_SHOW_SCHEDULES;

/**
 * This command is responsible for
 * buying ticket.
 *
 * @author Vitalii Holub
 */
public class TicketBuy implements Command {
    private static final Logger logger = LogManager.getLogger(TicketBuy.class);
    private final TicketService ticketService;

    public TicketBuy() {
        this.ticketService = TicketService.getInstance();
    }


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        final HttpSession session = request.getSession();
        session.setAttribute(BALANCE, ticketService.ticketBuy(session.getAttribute(EMAIL).toString(),
                Long.parseLong(request.getParameter("id"))));
        return REDIRECT+request.getHeader("referer");
    }
}
