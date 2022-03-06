package com.shimada.luizalabs.digitalmaps.domain.pontointeresse.validator;

import com.shimada.luizalabs.digitalmaps.config.exceptions.BusinessException;
import com.shimada.luizalabs.digitalmaps.domain.pontointeresse.utils.LocalTimeConverter;
import com.shimada.luizalabs.digitalmaps.web.form.PontoInteresseForm;

import java.time.LocalTime;

public class PontoInteresseValidator {

    private static final String ERROR_PERIODO_INVALIDO = "Horário de abertura não pode ser depois do horário de fechamento";

    private LocalTime horarioAbertura;
    private LocalTime horarioFechamento;

    private PontoInteresseValidator(final PontoInteresseForm pontoInteresseForm) {
        horarioAbertura = LocalTimeConverter.converter(pontoInteresseForm.horarioAbertura());
        horarioFechamento = LocalTimeConverter.converter(pontoInteresseForm.horarioFechamento());
    }

    public static PontoInteresseValidator criar(final PontoInteresseForm pontoInteresseForm) {
        return new PontoInteresseValidator(pontoInteresseForm);
    }

    public void validarForm() {
        if (horarioAbertura.isAfter(horarioFechamento)) {
            throw new BusinessException(ERROR_PERIODO_INVALIDO);
        }
    }

}
