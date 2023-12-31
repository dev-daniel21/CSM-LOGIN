package com.csm.csmlogin.web;

import com.csm.csmlogin.web.exceptions.CustomExceptionBody;
import com.csm.csmlogin.web.exceptions.PasswordNotCorrectException;
import com.csm.csmlogin.web.exceptions.ServiceNotAvailableException;
import com.csm.csmlogin.web.exceptions.UserAlreadyExistsException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@RestControllerAdvice(assignableTypes = LoginController.class)
@AllArgsConstructor
@Slf4j
public class LoginControllerExceptionHandler {

    @ExceptionHandler(PasswordNotCorrectException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<CustomExceptionBody> passwordNotCorrectException(PasswordNotCorrectException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.FORBIDDEN;
        Timestamp time = Timestamp.valueOf(LocalDateTime.now());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        log.error(e.getLocalizedMessage(), e);

        return new ResponseEntity<>(
                new CustomExceptionBody(time, status, e.getLocalizedMessage(), request),
                headers,
                status
        );
    }

    @ExceptionHandler(ServiceNotAvailableException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public ResponseEntity<CustomExceptionBody> serviceNotAvailableException(ServiceNotAvailableException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.SERVICE_UNAVAILABLE;
        Timestamp time = Timestamp.valueOf(LocalDateTime.now());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        log.error(e.getLocalizedMessage(), e);

        return new ResponseEntity<>(
                new CustomExceptionBody(time, status, e.getLocalizedMessage(), request),
                headers,
                status
        );
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<CustomExceptionBody> userAlreadyExistsException(UserAlreadyExistsException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        Timestamp time = Timestamp.valueOf(LocalDateTime.now());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        log.error(e.getLocalizedMessage(), e);

        return new ResponseEntity<>(
                new CustomExceptionBody(time, status, e.getLocalizedMessage(), request),
                headers,
                status
        );
    }
}
