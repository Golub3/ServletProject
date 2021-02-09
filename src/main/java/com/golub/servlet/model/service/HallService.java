package com.golub.servlet.model.service;

import com.golub.servlet.model.dao.DaoFactory;
import com.golub.servlet.model.dao.HallDao;
import com.golub.servlet.model.dao.ScheduleDao;
import com.golub.servlet.model.entity.Hall;
import com.golub.servlet.model.entity.Schedule;

import java.util.List;

/**
 * This class realize logic
 * for manipulation with db.
 *
 * @author Vitalii Holub
 */
public class HallService {
    private DaoFactory daoFactory;
    private static HallService instance;

    private HallService() {
        daoFactory = DaoFactory.getInstance();
    }

    public static HallService getInstance() {
        if (instance == null) {
            synchronized (HallService.class) {
                if (instance == null) {
                    instance = new HallService();
                }
            }
        }

        return instance;
    }

    /**
     * obtains hall by id.
     *
     * @param id long.
     */
    public Hall getHallById(long id) {
        HallDao dao = daoFactory.createHallDao();
        return dao.findById(id);
    }

    /**
     * obtains List of all halls.
     */
    public List<Hall> getAllHalls() {
        HallDao dao = daoFactory.createHallDao();
        return dao.findAll();
    }
}
