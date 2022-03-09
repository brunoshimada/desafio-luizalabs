package com.shimada.luizalabs.digitalmaps.utils;

import com.shimada.luizalabs.digitalmaps.config.exceptions.BusinessException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalTime;

class LocalTimeConverterTest {

    @ParameterizedTest
    @ValueSource(strings = {"00:00", "12:34", "16:38", "23:59"})
    public void deveConverterDeString(String value) {
        final LocalTime localTime = LocalTimeConverter.converter(value);
        final String[] timeMinutes = value.split(":");

        Assertions.assertEquals(2, timeMinutes.length);
        Assertions.assertEquals(localTime.getHour(), Integer.valueOf(timeMinutes[0]));
        Assertions.assertEquals(localTime.getMinute(), Integer.valueOf(timeMinutes[1]));
    }

    @ParameterizedTest
    @ValueSource(strings = {"24:01", "12:98", "13:60", "aa:aa"})
    public void erroSeStringForaDoPadrao(String value) {
        final BusinessException exception = Assertions.assertThrows(BusinessException.class,
                                                                            () -> LocalTimeConverter.converter(value));
        Assertions.assertEquals(exception.getMessage(), String.format("Horário informado %s não é válido", value));
    }

    @Test
    public void stringNulaSeParametroNulo() {
        LocalTime localTime = null;
        final String converter = LocalTimeConverter.converter(localTime);
        Assertions.assertNull(converter);
    }

    @Test
    public void deveConverterEmString() {
        var localTime = LocalTime.of(22,30);
        final String converter = LocalTimeConverter.converter(localTime);
        Assertions.assertTrue(converter.equals("22:30"));
    }

}