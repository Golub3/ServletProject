package com.golub.servlet.model.dao;

import com.golub.servlet.model.entity.Ticket;
import com.golub.servlet.model.service.TicketService;
import com.golub.servlet.model.service.UserService;

public interface TicketDao extends GenericDao<Ticket>{
    void createByIds(long exp_id, long user_id);

    boolean ticketIsBoughtByUser(long exp_id, long user_id);

    long countOfTicketsBoughtByUser(long exp_id, long user_id);

    TicketService.PaginationResult findByPagination(long user_id, int offset, int noOfRecords);

}
