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


    String QUERY;

    TicketSQL(String QUERY) {
        this.QUERY = QUERY;
    }

    public String getQUERY() {
        return QUERY;
    }
}
