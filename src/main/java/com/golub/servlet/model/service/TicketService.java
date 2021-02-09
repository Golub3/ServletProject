package com.golub.servlet.model.service;

import com.golub.servlet.controller.command.account.Login;
import com.golub.servlet.model.dao.DaoFactory;
import com.golub.servlet.model.dao.TicketDao;
import com.golub.servlet.model.dao.UserDao;
import com.golub.servlet.model.entity.Exposition;
import com.golub.servlet.model.entity.Ticket;
import com.golub.servlet.model.entity.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.util.List;

import static com.golub.servlet.controller.command.TextConstants.Parameters.EMAIL;

public class TicketService {
    private final DaoFactory daoFactory;
    private static final Logger logger = LogManager.getLogger(TicketService.class);
    private static TicketService instance;
    private final UserService userService;
    private final ExpositionService expositionService;

    private TicketService() {
        daoFactory = DaoFactory.getInstance();
        userService = UserService.getInstance();
        expositionService = ExpositionService.getInstance();
    }

    public static TicketService getInstance() {
        if (instance == null) {
            synchronized (TicketService.class) {
                if (instance == null) {
                    instance = new TicketService();
                }
            }
        }

        return instance;
    }

    /**
     * Obtains List of tickets by id.
     */
    public TicketService.PaginationResult getTicketsByPagination(long user_id, int lowerBound, int upperBound) {
        TicketDao dao = daoFactory.createTicketDao();
        return dao.findByPagination(user_id, lowerBound, upperBound);
    }

    /**
     * obtains ticket by id.
     *
     * @param id long.
     */
    public Ticket getTicketById(long id) {
        TicketDao dao = daoFactory.createTicketDao();
        return dao.findById(id);
    }

    /**
     * ticket buying.
     *
     * @param email String.
     * @param id long.
     */
    public BigDecimal ticketBuy(String email, long id) {
        TicketDao ticketDao = daoFactory.createTicketDao();
        UserDao userDao = daoFactory.createUserDao();
        BigDecimal balance = userService.getUserByEmail(email).getBalance();
        BigDecimal price = expositionService.getExpositionById(id).getPrice();
        long user_id = userService.getUserByEmail(email).getId();

        logger.info(balance.toString());
        logger.info(price.toString());
        if ( balance.compareTo(new BigDecimal(Double.toString(0.0))) > 0
                && balance.compareTo(price) >= 0){
            userDao.alterBalanceById(balance.subtract(price), user_id);
            ticketDao.createByIds(id, user_id);
            return balance.subtract(price);
        }
        return balance;
    }

    /**
     * get all bought tickets by user id in db.
     *
     * @param exp_id long.
     * @param user_id long.
     */
    public boolean isBoughtByUser(long exp_id, long user_id) {
        TicketDao dao = daoFactory.createTicketDao();
        return dao.ticketIsBoughtByUser(exp_id, user_id);

    }

    /**
     * checks if such user bought such ticket in db.
     *
     * @param exp_id long.
     * @param user_id long.
     */
    public long getCountOfTicketsBoughtByUser(long exp_id, long user_id) {
        TicketDao dao = daoFactory.createTicketDao();
        return dao.countOfTicketsBoughtByUser(exp_id, user_id);

    }

    /**
     * It is user-defined class just for returning result from findByPagination() method.
     */
    public static class PaginationResult {
        private int noOfRecords;
        private List<Ticket> resultList;

        public int getNoOfRecords() {
            return noOfRecords;
        }

        public void setNoOfRecords(int noOfRecords) {
            this.noOfRecords = noOfRecords;
        }

        public List<Ticket> getResultList() {
            return resultList;
        }

        public void setResultList(List<Ticket> resultList) {
            this.resultList = resultList;
        }

    }

}
