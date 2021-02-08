package com.golub.servlet.model.dao.impl.queries;

/**
 * SQL queries for schedule table.
 */
public enum ScheduleSQL {
    READ_ONE("select * from schedule where id=(?)"),

    READ_ALL("select * from schedule"),

//    INSERT("INSERT INTO user " +
//            "(first_name, last_name, email, password, role) " +
//            "VALUES ((?),(?),(?),(?),(?))"),
//
//    DELETE(""),
//    UPDATE(""),
//
//    READ_BY_EMAIL("SELECT * FROM user where email=(?);"),
//
//    READ_BY_EMAIL_PASSWORD("SELECT * FROM user where email=(?) and password=(?);"),
//

    GET_SCHEDULES_BY_PAGINATION("SELECT * FROM schedule, exposition " +
            " where schedule.id_e = exposition.id_exp and (date >= ? and date <= ?)" +
            " order by %s %s" +
            " limit ?, ? "),

    CALC_SCHEDULES_BY_SCHEDULE_ID("SELECT count(*) from schedule" +
            " WHERE date >= ? and date <= ? ");


    String QUERY;

    ScheduleSQL(String QUERY) {
        this.QUERY = QUERY;
    }

    public String getQUERY() {
        return QUERY;
    }
}
