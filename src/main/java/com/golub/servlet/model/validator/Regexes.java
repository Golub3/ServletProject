package com.golub.servlet.model.validator;


public interface Regexes {

    String EMAIL_REGEXP = "(^[\\w\\.-_]+)@([\\w\\.-]+)\\.([\\w\\.-]+)$";
    String PASSWORD_REGEXP = "^([a-zA-Z0-9_.]{1,30})$";

}