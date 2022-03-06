package com.shimada.luizalabs.digitalmaps.domain.pontointeresse.services;

import com.shimada.luizalabs.digitalmaps.api.PontoInteresseServiceAPI;
import com.shimada.luizalabs.digitalmaps.domain.pontointeresse.dao.PontoInteresseDao;
import com.shimada.luizalabs.digitalmaps.domain.pontointeresse.services.factory.PontoInteresseFactory;
import com.shimada.luizalabs.digitalmaps.domain.pontointeresse.validator.PontoInteresseValidator;
import com.shimada.luizalabs.digitalmaps.web.form.PontoInteresseForm;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PontoInteresseServiceImpl implements PontoInteresseServiceAPI {

    private final PontoInteresseDao pontoInteresseDao;

    public PontoInteresseServiceImpl(PontoInteresseDao pontoInteresseDao) {
        this.pontoInteresseDao = pontoInteresseDao;
    }

    @Transactional(rollbackFor = Exception.class)
    public void novoPontoInteresse(final PontoInteresseForm pontoInteresseForm) {
        PontoInteresseValidator.criar(pontoInteresseForm).validarForm();
        pontoInteresseDao.salvar(PontoInteresseFactory.criarPontoInteresseAPartirDoForm(pontoInteresseForm));
    }

}
