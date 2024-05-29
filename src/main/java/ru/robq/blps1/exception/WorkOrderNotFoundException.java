package ru.robq.blps1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Payment not found")
public class WorkOrderNotFoundException extends Exception{
    public WorkOrderNotFoundException(String message) {
        super(message);
    }
}
