package com.golub.servlet.model.dao.impl;

import com.golub.servlet.model.dao.HallDao;
import com.golub.servlet.model.dao.impl.queries.HallSQL;
import com.golub.servlet.model.dao.impl.queries.ScheduleSQL;
import com.golub.servlet.model.dao.impl.queries.UserSQL;
import com.golub.servlet.model.dao.mapper.HallMapper;
import com.golub.servlet.model.dao.mapper.ScheduleMapper;
import com.golub.servlet.model.dao.mapper.UserMapper;
import com.golub.servlet.model.entity.Hall;
import com.golub.servlet.model.entity.Schedule;
import com.golub.servlet.model.entity.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcHallDao implements HallDao {

    private Connection connection;
    private static final Logger logger = LogManager.getLogger(JdbcHallDao.class);

    private static JdbcHallDao instance;

    private JdbcHallDao() {
    }

    public static JdbcHallDao getInstance() {
        if (instance == null) {
            synchronized (JdbcHallDao.class) {
                if (instance == null) {
                    instance = new JdbcHallDao();
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
    public void create(Hall hall) {
        throw new UnsupportedOperationException("This action has not yet been developed.");
    }

    /**
     * finds Hall in database.
     *
     * @param id hall id.
     */
    @Override
    public Hall findById(long id) {
        HallMapper hallMapper = new HallMapper();

        Hall result = new Hall();
        result.setId_hall(-1);

        try (PreparedStatement ps = connection.prepareStatement(HallSQL.READ_ONE.getQUERY())) {

            ps.setLong(1, id);
            final ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result = hallMapper.extractFromResultSet(rs);
            }
        } catch (SQLException e) {
            logger.fatal("Caught SQLException exception", e);
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Hall> findAll() {
        final String query = HallSQL.READ_ALL.getQUERY();

        try (Statement st = connection.createStatement()) {
            final ResultSet rs = st.executeQuery(query);
            return mapData(rs);
        } catch (SQLException e) {
            logger.fatal("Caught SQLException exception", e);
            e.printStackTrace();
            return null;
        }
    }

    private List<Hall> mapData(ResultSet rs) throws SQLException {
        final HallMapper hallMapper = new HallMapper();
        List<Hall> list = new ArrayList<>();
        while (rs.next()) {
            Hall hall = hallMapper.extractFromResultSet(rs);
            list.add(hall);
        }
        return list;
    }

    @Override
    public void update(Hall hall) {
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
}
