package com.golub.servlet.model.dao.impl.queries;


/**
 * SQL queries for exposition table.
 */
public enum ExpositionSQL {
    READ_ONE("select * from exposition where id_exp=(?)"),

    READ_ALL("select * from exposition");

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
//    GET_USERS_BY_PAGINATION("SELECT SQL_CALC_FOUND_ROWS * FROM user " +
////                                      " where id=(?) " +
//            " order by first_name DESC " +
//            " limit ?, ?;"),
//
//    CALC_USERS_BY_USER_ID("SELECT count(*) from user");


    String QUERY;

    ExpositionSQL(String QUERY) {
        this.QUERY = QUERY;
    }

    public String getQUERY() {
        return QUERY;
    }
}
