package com.front.end.pk.encrypt.demo.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Date;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MyPasswordException.class)
    protected ResponseEntity<CustomErrorResponse> handlePasswordException(MyPasswordException ex) {
        CustomErrorResponse customErrorResponse = new CustomErrorResponse();
        customErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        LocalDateTime localDateTime = LocalDateTime.now();
        customErrorResponse.setTimestamp(localDateTime);
        customErrorResponse.setError(ex.getMessage());
        customErrorResponse.setDebugMessage(ex.getStackTrace()[0]);
        return new ResponseEntity<>(customErrorResponse,HttpStatus.NOT_FOUND);

    }
}
