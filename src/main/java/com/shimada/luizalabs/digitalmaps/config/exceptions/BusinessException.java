package com.shimada.luizalabs.digitalmaps.config.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.text.MessageFormat;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BusinessException extends RuntimeException {

    public BusinessException(final String message, String... args) {
        super(MessageFormat.format(message, args));
    }
}
