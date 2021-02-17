package com.golub.servlet.model.dao.impl;

import com.golub.servlet.model.dao.TicketDao;
import com.golub.servlet.model.dao.impl.queries.TicketSQL;
import com.golub.servlet.model.dao.impl.queries.UserSQL;
import com.golub.servlet.model.dao.mapper.TicketMapper;
import com.golub.servlet.model.dao.mapper.UserMapper;
import com.golub.servlet.model.entity.Schedule;
import com.golub.servlet.model.entity.Ticket;
import com.golub.servlet.model.entity.User;
import com.golub.servlet.model.service.TicketService;
import com.golub.servlet.model.service.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcTicketDao implements TicketDao {
    private Connection connection;
    private static final Logger logger = LogManager.getLogger(JdbcTicketDao.class);

    private static JdbcTicketDao instance;

    private JdbcTicketDao() {
    }

    public static JdbcTicketDao getInstance() {
        if (instance == null) {
            synchronized (JdbcTicketDao.class) {
                if (instance == null) {
                    instance = new JdbcTicketDao();
                }
            }
        }
        return instance;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }

    public void initConnection(Connection connection) {
        instance.setConnection(connection);
    }

    @Override
    public void create(Ticket ticket) {
        throw new UnsupportedOperationException("This action has not yet been developed.");
    }


    /**
     * create Ticket in database
     * and alter User balance.
     *
     * @param user_id long.
     * @param exp_id long.
     * @param balance BigDecimal.
     * @param id long.
     */
    public void createByIdsAndAlterBalance(long exp_id, long user_id, BigDecimal balance, long id) {
        try (PreparedStatement psTicket = connection.prepareStatement(TicketSQL.INSERT_BY_IDS.getQUERY());
             PreparedStatement psUser = connection.prepareStatement(UserSQL.ALTER_BALANCE_BY_ID.getQUERY())) {

            logger.info("Transaction opened");
            connection.setAutoCommit(false);

            psTicket.setLong(1, exp_id);
            psTicket.setLong(2, user_id);
            psTicket.execute();

            psUser.setBigDecimal(1, balance);
            psUser.setLong(2, id);
            psUser.execute();

            connection.setAutoCommit(true);
            logger.info("Transaction was successfully committed");

        } catch (SQLException e) {
            logger.fatal("Caught SQLException exception", e);
            try {
                connection.rollback();
            } catch (SQLException er) {
                logger.error("Exception occurred during connection rollback execution");
            }
            try {
                connection.setAutoCommit(true);
            } catch (SQLException er) {
                logger.error("An attempt to set connection in auto commit mode failed");
            }
            e.printStackTrace();
        }
    }


    /**
     * searches Tickets in database by exposition and user ids
     *
     * @param exp_id long
     * @param user_id long
     * @return boolean
     */
    @Override
    public boolean ticketIsBoughtByUser(final long exp_id, final long user_id) {

        try (PreparedStatement ps = connection.prepareStatement(TicketSQL.READ_BY_EXP_ID_AND_USER_ID.getQUERY())) {

            ps.setLong(1, exp_id);
            ps.setLong(2, user_id);

            final ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            logger.fatal("Caught SQLException exception", e);
            e.printStackTrace();
        }
        return false;
    }

    /**
     * finds Ticket in database.
     *
     * @param id ticket id.
     */
    @Override
    public Ticket findById(long id) {
        TicketMapper ticketMapper = new TicketMapper();

        Ticket result = new Ticket();
        result.setTicket_id(-1);

        try (PreparedStatement ps = connection.prepareStatement(TicketSQL.READ_ONE.getQUERY())) {

            ps.setLong(1, id);
            final ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result = ticketMapper.extractFromResultSet(rs);
            }
        } catch (SQLException e) {
            logger.fatal("Caught SQLException exception", e);
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public long countOfTicketsBoughtByUserOnExposition(long exp_id, long user_id){
        try(PreparedStatement countRowsPS =
                    connection.prepareStatement(TicketSQL.CALC_TICKETS_BY_USER_ID_AND_EXPOSITION_ID.getQUERY())){
            countRowsPS.setLong(1, exp_id);
            countRowsPS.setLong(2, user_id);
            return countRowsPS.executeQuery().getLong(1);
        } catch (SQLException e) {
            logger.fatal("Caught SQLException exception", e);
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int countOfTicketsBoughtByUser(long id){
        try(PreparedStatement countRowsPS =
                    connection.prepareStatement(TicketSQL.CALC_TICKETS_BY_USER_ID.getQUERY())){
            countRowsPS.setLong(1, id);
            return countRowsPS.executeQuery().getInt(1);
        } catch (SQLException e) {
            logger.fatal("Caught SQLException exception", e);
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Ticket> findAll() {
        throw new UnsupportedOperationException("This action has not yet been developed.");
    }

    @Override
    public void update(Ticket ticket) {
        throw new UnsupportedOperationException("This action has not yet been developed.");
    }

    @Override
    public void delete(long id) {
        throw new UnsupportedOperationException("This action has not yet been developed.");
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * SQL query obtains all Tickets s limited by lower and upper bounds ordered by descent according to time
     * and quantity of all records got from database.
     *
     * @param lowerBound integer value.
     * @param upperBound integer value.
     * @return object of ticket-defined class PaginationResult. Which contains of two fields:
     * 1)the List of obtained Tickets.
     * 2)number of records was read.
     */
    @Override
    public TicketService.PaginationResult findByPagination(long user_id, int lowerBound, int upperBound) {

        TicketService.PaginationResult paginationResult = new TicketService.PaginationResult();

        List<Ticket> tickets = new ArrayList<>();
        TicketMapper ticketMapper = new TicketMapper();

        try (PreparedStatement ticketsPS = connection.prepareStatement(TicketSQL.GET_TICKETS_BY_PAGINATION.getQUERY());
             PreparedStatement countRowsPS = connection.prepareStatement(TicketSQL.CALC_TICKETS_BY_USER_ID.getQUERY())) {
            ticketsPS.setLong(1, user_id);
            ticketsPS.setInt(2, lowerBound);
            ticketsPS.setInt(3, upperBound);

            countRowsPS.setLong(1, user_id);

            ResultSet rs = ticketsPS.executeQuery();
            while (rs.next()) {
                Ticket ticket = ticketMapper.extractFromResultSet(rs);
                tickets.add(ticket);
            }
            rs.close();

            rs = countRowsPS.executeQuery();
            if (rs.next()) {
                paginationResult.setNoOfRecords(rs.getInt(1));
            }
            rs.close();
        } catch (SQLException e) {
            logger.fatal("Caught SQLException exception", e);
            e.printStackTrace();
        }

        paginationResult.setResultList(tickets);
        return paginationResult;
    }
}
