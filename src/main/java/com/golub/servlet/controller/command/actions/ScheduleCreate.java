package com.golub.servlet.controller.command.actions;

import com.golub.servlet.controller.command.Command;
import com.golub.servlet.model.entity.Exposition;
import com.golub.servlet.model.entity.Schedule;
import com.golub.servlet.model.entity.Ticket;
import com.golub.servlet.model.service.ExpositionService;
import com.golub.servlet.model.service.HallService;
import com.golub.servlet.model.service.ScheduleService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import static com.golub.servlet.controller.command.TextConstants.Parameters.*;
import static com.golub.servlet.controller.command.TextConstants.Parameters.LAST_NAME;
import static com.golub.servlet.controller.command.TextConstants.Routes.*;

/**
 * This command is responsible for
 * create schedule.
 *
 * @author Vitalii Holub
 */
public class ScheduleCreate implements Command {
    private static final Logger logger = LogManager.getLogger(TicketBuy.class);
    private final ScheduleService scheduleService;
    private final ExpositionService expositionService;
    private final HallService hallService;

    public ScheduleCreate() {
        this.scheduleService = ScheduleService.getInstance();
        this.expositionService = ExpositionService.getInstance();
        this.hallService = HallService.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute(EXPOSITIONS, expositionService.getAllExpositions());
        request.setAttribute(HALLS, hallService.getAllHalls());

        if(request.getParameter("hall") != null) {
            final long hall_id = Long.parseLong(request.getParameter("hall"));
            final long exposition_id = Long.parseLong(request.getParameter("exposition"));
            scheduleService.createSchedule(Schedule.builder()
                    .withDate(LocalDate.parse(request.getParameter(DATE)))
                    .withTime_start(LocalTime.parse(request.getParameter(START_TIME)))
                    .withTime_end(LocalTime.parse(request.getParameter(END_TIME)))
                    .withExposition(expositionService.getExpositionById(exposition_id))
                    .withHall(hallService.getHallById(hall_id))
                    .build());
        }

        return TO_SCHEDULE_CREATE;
    }
}
