package com.Meetings.Meetings.controller.advice;

import com.Meetings.Meetings.exceptions.ExceptionMessage;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;


@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionMessage> handleConstraintViolationException(Exception ex, HttpServletRequest request){
        var resp = ExceptionMessage.builder()
                .message(ex.getMessage()).statusCode(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now())
                .path(request.getRequestURI()).build();
        return ResponseEntity.badRequest().body(resp);
    }
}
