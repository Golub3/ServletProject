package com.golub.servlet.controller.command.actions;

import com.golub.servlet.controller.command.Command;
import com.golub.servlet.model.entity.Exposition;
import com.golub.servlet.model.entity.Schedule;
import com.golub.servlet.model.entity.Ticket;
import com.golub.servlet.model.service.ExpositionService;
import com.golub.servlet.model.service.HallService;
import com.golub.servlet.model.service.ScheduleService;
import com.golub.servlet.model.validator.ExpositionValidator;
import com.golub.servlet.model.validator.UserValidator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import static com.golub.servlet.controller.command.TextConstants.Parameters.*;
import static com.golub.servlet.controller.command.TextConstants.Parameters.LAST_NAME;
import static com.golub.servlet.controller.command.TextConstants.Routes.*;

/**
 * This command is responsible for
 * create exposition.
 *
 * @author Vitalii Holub
 */
public class ExpositionCreate implements Command {
    private static final Logger logger = LogManager.getLogger(TicketBuy.class);
    private final ExpositionService expositionService;

    public ExpositionCreate() {
        this.expositionService = ExpositionService.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        if(request.getParameter(PRICE) != null && request.getParameter(THEME) != null) {

            if (!(ExpositionValidator.validateTheme(request.getParameter(THEME)))){
                return THEME_FAIL_INVALID_DATA;
            }
            if (!(ExpositionValidator.validatePrice(request.getParameter(PRICE)))){
                return PRICE_FAIL_INVALID_DATA;
            }
            expositionService.createExposition(Exposition.builder()
                    .withTheme(request.getParameter(THEME))
                    .withPrice(new BigDecimal(request.getParameter(PRICE))).build()
            );
            return EXPOSITION_SUCCESS;
        }

        return TO_EXPOSITION_CREATE;
    }
}
