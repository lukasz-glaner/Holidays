package com.project.holidays.exception;

public class HolidayIsAlreadyApprovedException extends RuntimeException{
    public HolidayIsAlreadyApprovedException(String message) {
        super(message);
    }
}
