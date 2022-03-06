package com.shimada.luizalabs.digitalmaps.domain.pontointeresse.utils;

import com.shimada.luizalabs.digitalmaps.config.exceptions.BusinessException;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public final class LocalTimeConverter {

    private static final String ERROR_HORARIO_INVALIDO = "Horário informado {0} não é válido";

    public static LocalTime converter(final String localTimeCandidate) {
        try {
            return LocalTime.parse(localTimeCandidate, DateTimeFormatter.ofPattern("HH:mm"));
        } catch (DateTimeParseException e) {
            throw new BusinessException(ERROR_HORARIO_INVALIDO, localTimeCandidate);
        }
    }

}
