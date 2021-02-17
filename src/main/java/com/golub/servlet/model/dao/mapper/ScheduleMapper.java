package com.golub.servlet.model.dao.mapper;

import com.golub.servlet.model.dao.impl.JdbcScheduleDao;
import com.golub.servlet.model.entity.Exposition;
import com.golub.servlet.model.entity.Hall;
import com.golub.servlet.model.entity.Schedule;
import com.golub.servlet.model.service.ExpositionService;
import com.golub.servlet.model.service.HallService;
import com.golub.servlet.model.service.ScheduleService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class ScheduleMapper implements ObjectMapper<Schedule>{
    private static final String ID_SCHEDULE = "id";
    private static final String DATE = "date";
    private static final String START_TIME = "time_start";
    private static final String END_TIME = "time_end";
    private static final String ID_EXPOSITION = "id_e";
    private static final String ID_HALL = "id_h";

    @Override
    public Schedule extractFromResultSet(ResultSet rs) throws SQLException {
        ExpositionService expositionService = ExpositionService.getInstance();
        HallService hallService = HallService.getInstance();

        return Schedule.builder()
                .withId(rs.getLong(ID_SCHEDULE))
                .withDate(rs.getDate(DATE).toLocalDate())
                .withTime_start(rs.getTime(START_TIME).toLocalTime())
                .withTime_end(rs.getTime(END_TIME).toLocalTime())
                .withExposition(expositionService.getExpositionById(rs.getLong(ID_EXPOSITION)))
                .withHall(hallService.getHallById(rs.getLong(ID_HALL)))
                .build();
    }

}
