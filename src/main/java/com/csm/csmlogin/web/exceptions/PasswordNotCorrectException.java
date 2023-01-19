package com.csm.csmlogin.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class PasswordNotCorrectException extends Exception{

    public PasswordNotCorrectException(String message) {
        super(message);
    }
}
