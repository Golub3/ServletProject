package com.golub.servlet.model.dao.impl.queries;

/**
 * SQL queries for users table.
 */
public enum UserSQL {
    READ_ONE("select * from user where id=(?)"),

    READ_ALL("select * from user"),

    INSERT("INSERT INTO user " +
            "(first_name, last_name, email, password, role) " +
            "VALUES ((?),(?),(?),(?),(?))"),

    ALTER_BALANCE_BY_ID("UPDATE user SET balance=? WHERE id=(?)"),

    DELETE(""),
    UPDATE(""),

    READ_BY_EMAIL("SELECT * FROM user where email=(?);"),

    READ_BY_EMAIL_PASSWORD("SELECT * FROM user where email=(?) and password=(?);"),

    GET_USERS_BY_PAGINATION("SELECT SQL_CALC_FOUND_ROWS * FROM user " +
//                                      " where id=(?) " +
                                      " order by first_name DESC " +
                                      " limit ?, ?;"),

    CALC_USERS("SELECT count(*) from user");


    String QUERY;

    UserSQL(String QUERY) {
        this.QUERY = QUERY;
    }

    public String getQUERY() {
        return QUERY;
    }
}