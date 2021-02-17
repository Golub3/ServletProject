package com.golub.servlet.model.dao.mapper;

import com.golub.servlet.model.entity.Exposition;
import com.golub.servlet.model.entity.Hall;
import com.golub.servlet.model.entity.Schedule;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class HallMapper implements ObjectMapper<Hall>{
    private static final String ID_HALL = "id_hall";
    private static final String NAME = "name";

    @Override
    public Hall extractFromResultSet(ResultSet rs) throws SQLException {
        return Hall.builder()
                .withId_hall(rs.getLong(ID_HALL))
                .withName(rs.getString(NAME))
                .build();
    }

}
