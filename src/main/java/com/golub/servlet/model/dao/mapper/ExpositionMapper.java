package com.golub.servlet.model.dao.mapper;

import com.golub.servlet.model.entity.Exposition;
import com.golub.servlet.model.entity.Hall;
import com.golub.servlet.model.entity.Schedule;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class ExpositionMapper implements ObjectMapper<Exposition>{
    private static final String ID_EXP = "id_exp";
    private static final String PRICE = "price";
    private static final String THEME = "theme";

    @Override
    public Exposition extractFromResultSet(ResultSet rs) throws SQLException {
        return Exposition.builder()
                .withId_exp(rs.getLong(ID_EXP))
                .withPrice(rs.getBigDecimal(PRICE))
                .withTheme(rs.getString(THEME))
                .build();
    }


    public Exposition makeUnique(Map<Long, Exposition> existing, Exposition entity) {
        existing.putIfAbsent(entity.getId_exp(), entity);
        return existing.get(entity.getId_exp());
    }
}
