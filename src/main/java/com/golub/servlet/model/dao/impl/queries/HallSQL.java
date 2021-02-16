package com.golub.servlet.model.dao.impl.queries;

/**
 * SQL queries for exposition table.
 */
public enum HallSQL {
    READ_ONE("select * from hall where id_hall=(?)"),

    READ_ALL("select * from hall");

    String QUERY;

    HallSQL(String QUERY) {
        this.QUERY = QUERY;
    }

    public String getQUERY() {
        return QUERY;
    }
}
