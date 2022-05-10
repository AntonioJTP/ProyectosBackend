package com.backend.DBA1.content.person.application.errorHandler.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class UnprocesableException extends Exception {

    public UnprocesableException(String message) {
        super(message);
    }
}
