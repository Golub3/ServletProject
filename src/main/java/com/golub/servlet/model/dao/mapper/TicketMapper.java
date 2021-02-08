package com.golub.servlet.model.dao.mapper;

import com.golub.servlet.model.entity.Hall;
import com.golub.servlet.model.entity.Schedule;
import com.golub.servlet.model.entity.Ticket;
import com.golub.servlet.model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class TicketMapper implements ObjectMapper<Ticket>{
    private static final String ID_TICKET = "ticket_id";
    private final UserMapper userMapper = new UserMapper();
    private final ExpositionMapper expositionMapper = new ExpositionMapper();


    @Override
    public Ticket extractFromResultSet(ResultSet rs) throws SQLException {
        return Ticket.builder()
                .withTicket_id(rs.getInt(ID_TICKET))
                .withUser(userMapper.extractFromResultSet(rs))
                .withExposition(expositionMapper.extractFromResultSet(rs))
                .build();
    }


    public Ticket makeUnique(Map<Long, Ticket> existing, Ticket entity) {
        existing.putIfAbsent(entity.getTicket_id(), entity);
        return existing.get(entity.getTicket_id());
    }
}
