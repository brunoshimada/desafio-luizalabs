package com.shimada.luizalabs.digitalmaps.domain.pontointeresse.services;

import com.shimada.luizalabs.digitalmaps.api.PontoInteresseServiceAPI;
import com.shimada.luizalabs.digitalmaps.config.exceptions.BusinessException;
import com.shimada.luizalabs.digitalmaps.domain.pontointeresse.dao.PontoInteresseDao;
import com.shimada.luizalabs.digitalmaps.domain.pontointeresse.dao.to.PontoInteresseTO;
import com.shimada.luizalabs.digitalmaps.domain.pontointeresse.models.PontoInteresse;
import com.shimada.luizalabs.digitalmaps.domain.pontointeresse.services.factory.PontoInteresseFactory;
import com.shimada.luizalabs.digitalmaps.domain.pontointeresse.validator.PontoInteresseValidator;
import com.shimada.luizalabs.digitalmaps.web.form.BuscaPontoInteresseForm;
import com.shimada.luizalabs.digitalmaps.web.form.PontoInteresseForm;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PontoInteresseServiceImpl implements PontoInteresseServiceAPI {

    private static final String ERROR_UNIQUE_VIOLATION = "Já existe um ponto de interesse com essas mesmas coordenadas ({0}, {1})";

    private final PontoInteresseDao pontoInteresseDao;

    public PontoInteresseServiceImpl(PontoInteresseDao pontoInteresseDao) {
        this.pontoInteresseDao = pontoInteresseDao;
    }

    @Transactional(rollbackFor = Exception.class)
    public void novoPontoInteresse(final PontoInteresseForm pontoInteresseForm) {
        PontoInteresseValidator.criar(pontoInteresseForm).validarForm();

        try {
            pontoInteresseDao.salvar(PontoInteresseFactory.criarPontoInteresseAPartirDoForm(pontoInteresseForm));
        } catch (DataIntegrityViolationException exception) {
            if (exception.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
                throw new BusinessException(ERROR_UNIQUE_VIOLATION, String.valueOf(pontoInteresseForm.coordenadaX()), String.valueOf(pontoInteresseForm.coordenadaY()));
            }
        }
    }

    @Override
    public List<PontoInteresse> listar() {
        return pontoInteresseDao.listar();
    }

    @Override
    public List<PontoInteresseTO> buscarPontosDeInteresse(BuscaPontoInteresseForm buscaPontoInteresseForm) {
        return pontoInteresseDao.buscarPontosDeInteresse(buscaPontoInteresseForm.coordenadaX(), buscaPontoInteresseForm.coordenadaY(), buscaPontoInteresseForm.raio());
    }

}
