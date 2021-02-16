package com.golub.servlet.model.dao.impl.queries;


/**
 * SQL queries for exposition table.
 */
public enum ExpositionSQL {
    READ_ONE("select * from exposition where id_exp=(?)"),

    READ_ALL("select * from exposition");

    String QUERY;

    ExpositionSQL(String QUERY) {
        this.QUERY = QUERY;
    }

    public String getQUERY() {
        return QUERY;
    }
}
