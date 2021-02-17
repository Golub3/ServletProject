package com.golub.servlet.model.dao.mapper;

import com.golub.servlet.model.entity.Ticket;
import com.golub.servlet.model.service.ExpositionService;
import com.golub.servlet.model.service.UserService;
import com.sun.xml.internal.bind.v2.model.core.ID;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class TicketMapper implements ObjectMapper<Ticket>{
    private static final String ID_TICKET = "ticket_id";
    private static final String ID_USER = "id";
    private static final String ID_EXPOSITION = "id_exp";

    @Override
    public Ticket extractFromResultSet(ResultSet rs) throws SQLException {
        ExpositionService expositionService = ExpositionService.getInstance();
        UserService userService = UserService.getInstance();
        return Ticket.builder()
                .withTicket_id(rs.getInt(ID_TICKET))
                .withUser(userService.getUserById(rs.getLong(ID_USER)))
                .withExposition(expositionService.getExpositionById(rs.getLong(ID_EXPOSITION)))
                .build();
    }

}
