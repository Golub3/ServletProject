package com.golub.servlet.controller.command.actions;

import com.golub.servlet.controller.command.Command;
import com.golub.servlet.model.service.ScheduleService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.golub.servlet.controller.command.TextConstants.Routes.REDIRECT;

/**
 * This command is responsible for
 * delete schedule.
 *
 * @author Vitalii Holub
 */
public class ScheduleDelete implements Command {
    private static final Logger logger = LogManager.getLogger(TicketBuy.class);
    private final ScheduleService scheduleService;

    public ScheduleDelete() {
        this.scheduleService = ScheduleService.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        scheduleService.deleteScheduleById(Long.parseLong(request.getParameter("id")));
        return REDIRECT+request.getHeader("referer");
    }
}