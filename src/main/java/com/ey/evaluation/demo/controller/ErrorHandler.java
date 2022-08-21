package com.ey.evaluation.demo.controller;

import com.ey.evaluation.demo.dto.ErrorMessage;
import com.ey.evaluation.demo.exception.NotValidPasswordException;
import com.ey.evaluation.demo.exception.UsedEmailException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorMessage> handleRequestException(MethodArgumentNotValidException e, WebRequest req) {
        List<String> errors = e.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> fieldError.getDefaultMessage()).collect(Collectors.toList());
        return ResponseEntity.badRequest().body(new ErrorMessage(String.join(". ", errors)));
    }

    @ExceptionHandler({UsedEmailException.class, NotValidPasswordException.class})
    public ResponseEntity<ErrorMessage> handleRequestException(Exception e, WebRequest req) {
        return ResponseEntity.badRequest().body(new ErrorMessage(e.getMessage()));
    }

}
