package com.golub.servlet.model.dao;

import com.golub.servlet.model.entity.Schedule;
import com.golub.servlet.model.service.ScheduleService;

import java.time.LocalDate;

public interface ScheduleDao extends GenericDao<Schedule>{
    ScheduleService.PaginationResult findByPagination(String field, String dir, LocalDate start_date, LocalDate end_date,
                                                      int offset, int noOfRecords);
}
