package com.golub.servlet.model.service;

import com.golub.servlet.model.dao.DaoFactory;
import com.golub.servlet.model.dao.ScheduleDao;
import com.golub.servlet.model.entity.Schedule;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * This class realize logic
 * for manipulation with db.
 *
 * @author Vitalii Holub
 */
public class ScheduleService {

    private DaoFactory daoFactory;
    private static ScheduleService instance;

    private ScheduleService() {
        daoFactory = DaoFactory.getInstance();
    }

    public static ScheduleService getInstance() {
        if (instance == null) {
            synchronized (ScheduleService.class) {
                if (instance == null) {
                    instance = new ScheduleService();
                }
            }
        }

        return instance;
    }

    /**
     * obtains List of all schedules.
     */
    public List<Schedule> getAllSchedules() {
        ScheduleDao dao = daoFactory.createScheduleDao();
        return dao.findAll();
    }

    public void deleteScheduleById(long id){
        ScheduleDao dao = daoFactory.createScheduleDao();
        dao.delete(id);
    }

    /**
     * Obtains List of schedules.
     */
    public PaginationResult getSchedulesByPagination(String field, String dir, LocalDate start_date, LocalDate end_date,
                                                     int lowerBound, int upperBound) {
        ScheduleDao dao = daoFactory.createScheduleDao();
        return dao.findByPagination(field, dir, start_date, end_date, lowerBound, upperBound);
    }

    /**
     * Create schedule.
     */
    public void createSchedule(Schedule schedule) {
        ScheduleDao dao = daoFactory.createScheduleDao();
        dao.create(schedule);
    }

    /**
     * It is schedule-defined class just for returning result from findByPagination() method.
     */
    public static class PaginationResult {
        private int noOfRecords;
        private List<Schedule> resultList;

        public int getNoOfRecords() {
            return noOfRecords;
        }

        public void setNoOfRecords(int noOfRecords) {
            this.noOfRecords = noOfRecords;
        }

        public List<Schedule> getResultList() {
            return resultList;
        }

        public void setResultList(List<Schedule> resultList) {
            this.resultList = resultList;
        }

    }

}