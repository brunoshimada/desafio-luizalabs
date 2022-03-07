package com.shimada.luizalabs.digitalmaps.domain.pontointeresse.services.factory;

import com.shimada.luizalabs.digitalmaps.domain.pontointeresse.models.PontoInteresse;
import com.shimada.luizalabs.digitalmaps.domain.pontointeresse.utils.LocalTimeConverter;
import com.shimada.luizalabs.digitalmaps.web.form.PontoInteresseForm;

public class PontoInteresseFactory {

    public static PontoInteresse criarPontoInteresseAPartirDoForm(PontoInteresseForm pontoInteresseForm) {
        var novoPontoInteresse = new PontoInteresse();
        novoPontoInteresse.setCoordenadaX(pontoInteresseForm.coordenadaX());
        novoPontoInteresse.setCoordenadaY(pontoInteresseForm.coordenadaY());
        novoPontoInteresse.setDescricao(pontoInteresseForm.descricao());

        if (pontoInteresseForm.horarioAbertura() != null && pontoInteresseForm.horarioFechamento() != null) {
            novoPontoInteresse.setHorarioAbertura(LocalTimeConverter.converter(pontoInteresseForm.horarioAbertura()));
            novoPontoInteresse.setHorarioFechamento(LocalTimeConverter.converter(pontoInteresseForm.horarioFechamento()));
        }

        return novoPontoInteresse;
    }

}
