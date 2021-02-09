package com.golub.servlet.model.dao.impl.queries;

public enum TicketSQL {
    READ_ONE("select * from ticket where ticket_id=(?)"),

    READ_ALL("select * from ticket"),

    INSERT_BY_IDS("INSERT INTO ticket " +
            "(id_exp, id) " +
            " VALUES ((?), (?))"),

    READ_BY_EXP_ID_AND_USER_ID("SELECT * FROM ticket where id_exp=(?) and id=(?);"),

    CALC_TICKETS_BY_USER_ID("SELECT count(*) from ticket where id=(?)"),

    CALC_TICKETS_BY_USER_ID_AND_EXPOSITION_ID("SELECT count(*) from ticket where id_exp=(?) and id=(?)"),

    GET_TICKETS_BY_PAGINATION("SELECT * FROM ticket " +
                                      " where id=(?) " +
                                    " order by id DESC " +
                                    " limit ?, ?;");

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
//    CALC_USERS("SELECT count(*) from user");


    String QUERY;

    TicketSQL(String QUERY) {
        this.QUERY = QUERY;
    }

    public String getQUERY() {
        return QUERY;
    }
}
