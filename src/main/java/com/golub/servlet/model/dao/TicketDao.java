package com.golub.servlet.model.dao;

import com.golub.servlet.model.entity.Ticket;
import com.golub.servlet.model.service.TicketService;
import com.golub.servlet.model.service.UserService;

import java.math.BigDecimal;

public interface TicketDao extends GenericDao<Ticket>{
    void createByIdsAndAlterBalance(long exp_id, long user_id, BigDecimal balance, long id);

    boolean ticketIsBoughtByUser(long exp_id, long user_id);

    long countOfTicketsBoughtByUser(long exp_id, long user_id);

    TicketService.PaginationResult findByPagination(long user_id, int offset, int noOfRecords);

}
