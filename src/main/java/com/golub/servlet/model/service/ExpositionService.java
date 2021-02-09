package com.golub.servlet.model.service;

import com.golub.servlet.model.dao.DaoFactory;
import com.golub.servlet.model.dao.ExpositionDao;
import com.golub.servlet.model.dao.HallDao;
import com.golub.servlet.model.entity.Exposition;
import com.golub.servlet.model.entity.Hall;

import java.util.List;

/**
 * This class realize logic
 * for manipulation with db.
 *
 * @author Vitalii Holub
 */
public class ExpositionService {
    private DaoFactory daoFactory;
    private static ExpositionService instance;

    private ExpositionService() {
        daoFactory = DaoFactory.getInstance();
    }

    public static ExpositionService getInstance() {
        if (instance == null) {
            synchronized (ExpositionService.class) {
                if (instance == null) {
                    instance = new ExpositionService();
                }
            }
        }

        return instance;
    }

    /**
     * obtains List of all expositions.
     */
    public List<Exposition> getAllExpositions() {
        ExpositionDao dao = daoFactory.createExpositionDao();
        return dao.findAll();
    }

    /**
     * obtains exposition by id.
     *
     * @param id long.
     */
    public Exposition getExpositionById(long id) {
        ExpositionDao dao = daoFactory.createExpositionDao();
        return dao.findById(id);
    }
}
