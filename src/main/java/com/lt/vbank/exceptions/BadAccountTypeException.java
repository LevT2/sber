package com.lt.vbank.exceptions;

public class BadAccountTypeException extends NullPointerException {
    @Override
    public String getMessage() {
        return "ERROR: Invalid Account type";
    }
}
