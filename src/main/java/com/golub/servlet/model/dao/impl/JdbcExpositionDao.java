package com.golub.servlet.model.dao.impl;

import com.golub.servlet.model.dao.ExpositionDao;
import com.golub.servlet.model.dao.impl.queries.ExpositionSQL;
import com.golub.servlet.model.dao.impl.queries.HallSQL;
import com.golub.servlet.model.dao.mapper.ExpositionMapper;
import com.golub.servlet.model.dao.mapper.HallMapper;
import com.golub.servlet.model.entity.Exposition;
import com.golub.servlet.model.entity.Hall;
import com.golub.servlet.model.entity.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JdbcExpositionDao implements ExpositionDao {

    private Connection connection;
    private static final Logger logger = LogManager.getLogger(JdbcExpositionDao.class);

    private static JdbcExpositionDao instance;

    private JdbcExpositionDao() {
    }

    public static JdbcExpositionDao getInstance() {
        if (instance == null) {
            synchronized (JdbcExpositionDao.class) {
                if (instance == null) {
                    instance = new JdbcExpositionDao();
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
    public void create(Exposition exposition) {
        throw new UnsupportedOperationException("This action has not yet been developed.");
    }

    /**
     * finds Exposition in database.
     *
     * @param id student id.
     */
    @Override
    public Exposition findById(long id) {
        ExpositionMapper expositionMapper = new ExpositionMapper();

        Exposition result = new Exposition();
        result.setId_exp(-1);

        try (PreparedStatement ps = connection.prepareStatement(ExpositionSQL.READ_ONE.getQUERY())) {

            ps.setLong(1, id);
            final ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result = expositionMapper.extractFromResultSet(rs);
            }
        } catch (SQLException e) {
            logger.fatal("Caught SQLException exception", e);
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Exposition> findAll() {
        throw new UnsupportedOperationException("This action has not yet been developed.");
    }

    @Override
    public void update(Exposition exposition) {
        throw new UnsupportedOperationException("This action has not yet been developed.");
    }

    @Override
    public void delete(long id) {
        throw new UnsupportedOperationException("This action has not yet been developed.");
    }

    @Override
    public void close() {

    }

}
