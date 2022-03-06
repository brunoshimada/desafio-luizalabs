package com.shimada.luizalabs.digitalmaps.config.advice;

import com.shimada.luizalabs.digitalmaps.config.exceptions.BusinessException;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestControllerAdviceImpl extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        final Map<String, String> errosForm = ex.getBindingResult().getAllErrors().stream()
            .collect(Collectors.toMap(
                error -> ((FieldError) error).getField(),
                DefaultMessageSourceResolvable::getDefaultMessage));
        return new ResponseEntity<>(errosForm, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({BusinessException.class})
    protected ResponseEntity<Object> handleBusinessException(BusinessException ex) {
        return new ResponseEntity<>(Collections.singletonMap("error", ex.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
