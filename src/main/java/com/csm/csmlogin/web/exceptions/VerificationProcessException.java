package com.csm.csmlogin.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class VerificationProcessException extends RuntimeException{


    public VerificationProcessException(String message) {
        super(message);
    }
}
