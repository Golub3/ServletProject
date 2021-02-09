package com.golub.servlet.model.dao.impl;


import com.golub.servlet.model.dao.*;
import com.golub.servlet.model.entity.Exposition;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JdbcDaoFactory extends DaoFactory {

    private DataSource dataSource = ConnectionPoolHolder.getDataSource();

    private Connection connection = getConnection();

    @Override
    public UserDao createUserDao() {
        JdbcUserDao jdbcUserDao = JdbcUserDao.getInstance();
        jdbcUserDao.initConnection(connection);
        return jdbcUserDao;
    }

    @Override
    public ExpositionDao createExpositionDao() {
        JdbcExpositionDao jdbcExpositionDao = JdbcExpositionDao.getInstance();
        jdbcExpositionDao.initConnection(connection);
        return jdbcExpositionDao;
    }

    @Override
    public ScheduleDao createScheduleDao() {
        JdbcScheduleDao jdbcScheduleDao = JdbcScheduleDao.getInstance();
        jdbcScheduleDao.initConnection(connection);
        return jdbcScheduleDao;
    }

    @Override
    public HallDao createHallDao() {
        JdbcHallDao jdbcHallDao = JdbcHallDao.getInstance();
        jdbcHallDao.initConnection(connection);
        return jdbcHallDao;
    }

    @Override
    public TicketDao createTicketDao() {
        JdbcTicketDao jdbcTicketDao = JdbcTicketDao.getInstance();
        jdbcTicketDao.initConnection(connection);
        return jdbcTicketDao;
    }

    private Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
