package com.golub.servlet.controller.command.actions;


import com.golub.servlet.controller.command.Command;
import com.golub.servlet.controller.command.CommandUtility;
import com.golub.servlet.controller.command.account.Login;
import com.golub.servlet.model.entity.Schedule;
import com.golub.servlet.model.service.ScheduleService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.ejb.Local;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

import static com.golub.servlet.controller.command.TextConstants.Parameters.*;
import static com.golub.servlet.controller.command.TextConstants.Routes.TO_SHOW_SCHEDULES_USER;
import static com.golub.servlet.model.service.ScheduleService.PaginationResult;


/**
 * This command is responsible for
 * showing list of schedules.
 *
 * @author Vitalii Holub
 */
public class ShowSchedules implements Command {

    private final ScheduleService scheduleService;

    private static final Logger logger = LogManager.getLogger(Login.class);

    public ShowSchedules() {
        this.scheduleService = ScheduleService.getInstance();
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

        //to prevent schedule coming back to cached pages after logout
        CommandUtility.disallowBackToCached(request, response);

        int RECORDS_PER_PAGE = 3;
        int currentPage = 1;
        if (request.getParameter(CURRENT_PAGE) != null) {
            currentPage = Integer.parseInt(request.getParameter(CURRENT_PAGE));
        }

        performPagination(request, currentPage, RECORDS_PER_PAGE);
        return TO_SHOW_SCHEDULES_USER;
    }

    private void performPagination(HttpServletRequest request, int currentPage, int recordsPerPage) {
        int lowerBound = calcLowerBound(currentPage, recordsPerPage);
        request.setAttribute("sort_way", SORT_FIELD);
        request.setAttribute("dir_way", DIR);
        request.setAttribute("start_date_way", START_DATE);
        request.setAttribute("end_date_way", END_DATE);
        String sort_field = "exposition.theme";
        String dir = "asc";
        LocalDate start_date = LocalDate.now().minusDays(14);
        LocalDate end_date = LocalDate.now().plusMonths(6);

        if (request.getParameter(SORT_FIELD) != null) {
            sort_field = request.getParameter(SORT_FIELD);
        }
        request.setAttribute("sort_field", sort_field);

        if (request.getParameter(DIR) != null) {
            dir = request.getParameter(DIR);
        }
        request.setAttribute("dir", dir);

        if (request.getParameter("start") != null) {
            start_date = LocalDate.parse(request.getParameter("start"));
        }
        request.setAttribute(START_DATE, start_date);

        if (request.getParameter("end") != null) {
            end_date = LocalDate.parse(request.getParameter("end"));
        }
        request.setAttribute(END_DATE, end_date);

        PaginationResult paginationResult =
                scheduleService.getSchedulesByPagination(sort_field, dir, start_date, end_date,
                        lowerBound, recordsPerPage);

        List<Schedule> schedules = paginationResult.getResultList();
        int noOfRecords = paginationResult.getNoOfRecords();
        int noOfPages = calcNoOfPages(noOfRecords, recordsPerPage);

        request.setAttribute(SCHEDULES, schedules);
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
