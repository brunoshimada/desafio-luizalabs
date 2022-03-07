package com.shimada.luizalabs.digitalmaps.api;

import com.shimada.luizalabs.digitalmaps.domain.pontointeresse.dao.to.PontoInteresseTO;
import com.shimada.luizalabs.digitalmaps.domain.pontointeresse.models.PontoInteresse;
import com.shimada.luizalabs.digitalmaps.web.form.BuscaPontoInteresseForm;
import com.shimada.luizalabs.digitalmaps.web.form.PontoInteresseForm;

import java.util.List;

public interface PontoInteresseServiceAPI {

    void novoPontoInteresse(final PontoInteresseForm pontoInteresseForm);

    List<PontoInteresse> listar();

    List<PontoInteresseTO> buscarPontosDeInteresse(BuscaPontoInteresseForm buscaPontoInteresseForm);
}
