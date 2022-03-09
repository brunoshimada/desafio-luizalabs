package com.shimada.luizalabs.digitalmaps.domain.pontointeresse.validator;

import com.shimada.luizalabs.digitalmaps.config.exceptions.BusinessException;
import com.shimada.luizalabs.digitalmaps.web.form.PontoInteresseForm;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PontoInteresseValidatorTest {

    private PontoInteresseForm form;

    @Test
    public void deveValidarSemErros() {
        dadoUmForm("12:00", "18:00");
        aoValidar();
    }

    @Test
    public void deveTerErroSePeriodoInvalido() {
        dadoUmForm("22:00", "09:15");
        final BusinessException businessException = assertThrows(BusinessException.class,
                                                                 this::aoValidar);
        assertEquals("Horário de abertura não pode ser depois do horário de fechamento", businessException.getMessage());
    }

    @Test
    public void deveTerErroSeInformadoSomenteHorarioAbertura() {
        dadoUmForm(null, "09:15");
        final BusinessException businessException = assertThrows(BusinessException.class,
                                                                 this::aoValidar);
        assertEquals("Informe os dois horários, ou nenhum se não possuir", businessException.getMessage());
    }

    @Test
    public void deveTerErroSeInformadoSomenteHorarioFechamento() {
        dadoUmForm("13:30", null);
        final BusinessException businessException = assertThrows(BusinessException.class,
                                                                 this::aoValidar);
        assertEquals("Informe os dois horários, ou nenhum se não possuir", businessException.getMessage());
    }

    private void dadoUmForm(String horarioAbertura, String horarioFechamento) {
        form = new PontoInteresseForm(
            "",
            0,
            0,
            horarioAbertura,
            horarioFechamento
        );
    }

    private void aoValidar() {
        PontoInteresseValidator.criar(form).validarForm();
    }

}