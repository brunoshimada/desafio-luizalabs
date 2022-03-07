package com.shimada.luizalabs.digitalmaps.domain.pontointeresse.validator;

import com.shimada.luizalabs.digitalmaps.config.exceptions.BusinessException;
import com.shimada.luizalabs.digitalmaps.utils.LocalTimeConverter;
import com.shimada.luizalabs.digitalmaps.web.form.PontoInteresseForm;

import java.time.LocalTime;

public class PontoInteresseValidator {

    private static final String ERROR_PERIODO_INVALIDO = "Horário de abertura não pode ser depois do horário de fechamento";
    private static final String ERROR_HORARIO_INCONSISTENTE = "Informe os dois horários, ou nenhum se não possuir";

    private LocalTime horarioAbertura;
    private LocalTime horarioFechamento;

    private PontoInteresseValidator(final PontoInteresseForm pontoInteresseForm) {
        horarioAbertura = converter(pontoInteresseForm.horarioAbertura());
        horarioFechamento = converter(pontoInteresseForm.horarioFechamento());
    }

    private LocalTime converter(final String candidateTime) {
        return candidateTime == null ? null : LocalTimeConverter.converter(candidateTime);
    }

    public static PontoInteresseValidator criar(final PontoInteresseForm pontoInteresseForm) {
        return new PontoInteresseValidator(pontoInteresseForm);
    }

    public void validarForm() {
        isHorariosConsistentes();
        isPeriodoValido();
    }

    private void isHorariosConsistentes() {
        if (horarioAbertura == null && horarioFechamento != null || horarioAbertura != null && horarioFechamento == null) {
            throw new BusinessException(ERROR_HORARIO_INCONSISTENTE);
        }
    }

    private void isPeriodoValido() {
        final boolean isHorariosPresentes = horarioAbertura != null && horarioFechamento != null;

        if (isHorariosPresentes && horarioAbertura.isAfter(horarioFechamento)) {
            throw new BusinessException(ERROR_PERIODO_INVALIDO);
        }
    }

}
