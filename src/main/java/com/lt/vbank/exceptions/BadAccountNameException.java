package com.lt.vbank.exceptions;

public class BadAccountNameException extends NullPointerException {
    @Override
    public String getMessage() {
        return "ERROR: Invalid Client name";
    }
}
