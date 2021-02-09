package com.golub.servlet.controller.command.account;

import com.golub.servlet.controller.command.Command;
import com.golub.servlet.controller.command.CommandUtility;
import com.golub.servlet.model.entity.Ticket;
import com.golub.servlet.model.entity.User;
import com.golub.servlet.model.service.TicketService;
import com.golub.servlet.model.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

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

    private final TicketService ticketService;
    private final UserService userService;

    public PersonalCabinet() {
        this.ticketService = TicketService.getInstance();
        this.userService = UserService.getInstance();
    }

    /**
     * Here pagination is provided.
     * <p>
     * Has hardcoded quantity of displayed records(recordsPerPage).
     * Operates by getting page-number from request and delegat es it to service
     * which returns certain records.
     *
     * @author Vitalii Holub
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        final HttpSession session = request.getSession();
        final User.ROLE role = (User.ROLE) session.getAttribute(ROLE);
        session.setAttribute(BALANCE, userService.getUserByEmail(session.getAttribute(EMAIL).toString()).getBalance());
        if ( session.getAttribute(ROLE) != User.ROLE.UNKNOWN) {
            //to prevent user coming back to cached pages after logout by clicking "back arrow" in browser
            CommandUtility.disallowBackToCached(request, response);
        }

        int RECORDS_PER_PAGE = 5;
        int currentPage = 1;
        if (request.getParameter(CURRENT_PAGE) != null) {
            currentPage = Integer.parseInt(request.getParameter(CURRENT_PAGE));
        }

        performPagination(request, currentPage, RECORDS_PER_PAGE);

        if (role.equals(User.ROLE.ADMIN) || role.equals(User.ROLE.USER)) {
            return BASE;
        } else {
            return USER_NOT_EXIST;
        }
    }

    private void performPagination(HttpServletRequest request, int currentPage, int recordsPerPage) {
        int lowerBound = calcLowerBound(currentPage, recordsPerPage);
        final HttpSession session = request.getSession();
        TicketService.PaginationResult paginationResult =
                ticketService.getTicketsByPagination(userService.getUserByEmail(session.getAttribute(EMAIL).toString()).getId(),
                        lowerBound, recordsPerPage);

        List<Ticket> tickets = paginationResult.getResultList();
        int noOfRecords = paginationResult.getNoOfRecords();
        int noOfPages = calcNoOfPages(noOfRecords, recordsPerPage);

        request.setAttribute(TICKETS, tickets);
        request.setAttribute(NO_OF_PAGES, noOfPages);
        request.setAttribute(CURRENT_PAGE, currentPage);
    }

    private int calcLowerBound(int currentPage, int recordsPerPage) {
        return (currentPage - 1) * recordsPerPage;
    }

    private int calcNoOfPages(int noOfRecords, int recordsPerPage) {
        return (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
    }
}
