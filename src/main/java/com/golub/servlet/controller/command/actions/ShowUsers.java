package com.golub.servlet.controller.command.actions;


import com.golub.servlet.controller.command.Command;
import com.golub.servlet.controller.command.CommandUtility;
import com.golub.servlet.model.entity.User;
import com.golub.servlet.model.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.golub.servlet.controller.command.TextConstants.Parameters.*;
import static com.golub.servlet.controller.command.TextConstants.Routes.TO_SHOW_USERS;
import static com.golub.servlet.model.service.UserService.PaginationResult;


/**
 * This command is responsible for
 * showing list of users.
 *
 * @author Vitalii Holub
 */
public class ShowUsers implements Command {

    private final UserService userService;


    public ShowUsers() {
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

        //to prevent user coming back to cached pages after logout
        CommandUtility.disallowBackToCached(request, response);

        int RECORDS_PER_PAGE = 3;
        int currentPage = 1;
        if (request.getParameter(CURRENT_PAGE) != null) {
            currentPage = Integer.parseInt(request.getParameter(CURRENT_PAGE));
        }

        performPagination(request, currentPage, RECORDS_PER_PAGE);
        return TO_SHOW_USERS;
    }

    private void performPagination(HttpServletRequest request, int currentPage, int recordsPerPage) {
        int lowerBound = calcLowerBound(currentPage, recordsPerPage);

        PaginationResult paginationResult =
                userService.getUsersByPagination(lowerBound, recordsPerPage);

        List<User> users = paginationResult.getResultList();
        int noOfRecords = paginationResult.getNoOfRecords();
        int noOfPages = calcNoOfPages(noOfRecords, recordsPerPage);

        request.setAttribute(USERS, users);
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
