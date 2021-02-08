package com.golub.servlet.model.exception;

public class AlreadyExistingDBRecordException extends Exception{
    public AlreadyExistingDBRecordException() {}
    public AlreadyExistingDBRecordException(String m) {
        super(m);
    }
}
