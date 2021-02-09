package com.golub.servlet.model.dao.impl;

import com.golub.servlet.model.dao.ScheduleDao;
import com.golub.servlet.model.dao.impl.queries.ScheduleSQL;
import com.golub.servlet.model.dao.impl.queries.TicketSQL;
import com.golub.servlet.model.dao.impl.queries.UserSQL;
import com.golub.servlet.model.dao.mapper.ScheduleMapper;
import com.golub.servlet.model.entity.Schedule;
import com.golub.servlet.model.service.ScheduleService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcScheduleDao implements ScheduleDao {

    private Connection connection;
    private static final Logger logger = LogManager.getLogger(JdbcScheduleDao.class);

    private static JdbcScheduleDao instance;

    private JdbcScheduleDao() {
    }

    public static JdbcScheduleDao getInstance() {
        if (instance == null) {
            synchronized (JdbcScheduleDao.class) {
                if (instance == null) {
                    instance = new JdbcScheduleDao();
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
    public void create(Schedule schedule) {
        try (PreparedStatement ps = connection.prepareStatement(ScheduleSQL.INSERT.getQUERY())) {

            ps.setTime(1, java.sql.Time.valueOf(schedule.getTime_start()));
            ps.setTime(2, java.sql.Time.valueOf(schedule.getTime_end()));
            ps.setDate(3, java.sql.Date.valueOf(schedule.getDate()));
            ps.setLong(4, schedule.getHall().getId_hall());
            ps.setLong(5, schedule.getExposition().getId_exp());

            ps.execute();

        } catch (SQLException e) {
            logger.fatal("Caught SQLException exception", e);
            e.printStackTrace();
        }
    }

    @Override
    public Schedule findById(long id) {
        throw new UnsupportedOperationException("This action has not yet been developed.");
    }

    @Override
    public List<Schedule> findAll() {
        Map<Long, Schedule> schedules = new HashMap<>();

        final String query = ScheduleSQL.READ_ALL.getQUERY();

        try (Statement st = connection.createStatement()) {
            final ResultSet rs = st.executeQuery(query);
            return mapFindManyResultSet(rs, schedules);
        } catch (SQLException e) {
            logger.fatal("Caught SQLException exception", e);
            e.printStackTrace();
            return null;
        }
    }

    //utility method. created in order not to duplicate code below
    private List<Schedule> mapFindManyResultSet(ResultSet rs, Map<Long, Schedule> schedules) throws SQLException {
        final ScheduleMapper scheduleMapper = new ScheduleMapper();
        while (rs.next()) {
            Schedule schedule = scheduleMapper.extractFromResultSet(rs);
            schedule = scheduleMapper.makeUnique(schedules, schedule);
        }
        return new ArrayList<>(schedules.values());
    }

    @Override
    public void update(Schedule schedule) {
        throw new UnsupportedOperationException("This action has not yet been developed.");
    }

    @Override
    public void delete(long id) {
        try (PreparedStatement ps = connection.prepareStatement(ScheduleSQL.DELETE.getQUERY())) {

            ps.setLong(1, id);

            ps.execute();

        } catch (SQLException e) {
            logger.fatal("Caught SQLException exception", e);
            e.printStackTrace();
        }
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
     * SQL query obtains all Schedules limited by lower and upper bounds ordered by descent according to time
     * and quantity of all records got from database.
     *
     * @param field String value.
     * @param sortType String value.
     * @param lowerBound integer value.
     * @param upperBound integer value.
     * @return object of schedule-defined class PaginationResult. Which contains of two fields:
     * 1)the List of obtained Schedules.
     * 2)number of records was read.
     */
    @Override
    public ScheduleService.PaginationResult findByPagination(String field, String sortType, LocalDate start_date,
                                                             LocalDate end_date, int lowerBound, int upperBound) {

        ScheduleService.PaginationResult paginationResult = new ScheduleService.PaginationResult();

        List<Schedule> schedules = new ArrayList<>();
        ScheduleMapper scheduleMapper = new ScheduleMapper();

        try (PreparedStatement schedulesPS = connection.prepareStatement(
                String.format(ScheduleSQL.GET_SCHEDULES_BY_PAGINATION.getQUERY(), field, sortType));
             PreparedStatement countRowsPS = connection.prepareStatement(ScheduleSQL.CALC_SCHEDULES_BY_SCHEDULE_ID.getQUERY())) {
            schedulesPS.setDate(1, java.sql.Date.valueOf(start_date));
            schedulesPS.setDate(2, java.sql.Date.valueOf(end_date));
            schedulesPS.setInt(3, lowerBound);
            schedulesPS.setInt(4, upperBound);
            countRowsPS.setDate(1, java.sql.Date.valueOf(start_date));
            countRowsPS.setDate(2, java.sql.Date.valueOf(end_date));

            ResultSet rs = schedulesPS.executeQuery();
            while (rs.next()) {
                Schedule schedule = scheduleMapper.extractFromResultSet(rs);
                schedules.add(schedule);
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

        paginationResult.setResultList(schedules);
        return paginationResult;
    }

}
