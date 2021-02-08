package com.golub.servlet.model.dao;


import com.golub.servlet.model.dao.impl.JdbcDaoFactory;
import com.golub.servlet.model.entity.Exposition;
import com.golub.servlet.model.entity.Hall;
import com.golub.servlet.model.entity.Schedule;

public abstract class DaoFactory {

    private static DaoFactory daoFactory;


    public abstract UserDao createUserDao();
    public abstract ExpositionDao createExpositionDao();
    public abstract ScheduleDao createScheduleDao();
    public abstract HallDao createHallDao();


    public static DaoFactory getInstance() {

        if (daoFactory == null) {
            synchronized (DaoFactory.class) {
                if (daoFactory == null) {
                    daoFactory = new JdbcDaoFactory();
                }
            }
        }

        return daoFactory;
    }

}
