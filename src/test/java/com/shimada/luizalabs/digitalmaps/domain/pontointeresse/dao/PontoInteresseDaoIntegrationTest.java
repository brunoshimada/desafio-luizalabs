package com.shimada.luizalabs.digitalmaps.domain.pontointeresse.dao;

import com.shimada.luizalabs.digitalmaps.domain.pontointeresse.dao.to.PontoInteresseTO;
import com.shimada.luizalabs.digitalmaps.domain.pontointeresse.models.PontoInteresse;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Repository.class))
public class PontoInteresseDaoIntegrationTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private PontoInteresseDao pontoInteresseDao;

    @Test
    public void deveCompilarlistar() {
        pontoInteresseDao.listar();
    }

    @Test
    public void deveCompilarBuscar() {
        pontoInteresseDao.buscarPontosDeInteresse(1,1,1);
    }

    @Test
    public void deveInserirEListar() {
        var pontoInteresse = new PontoInteresse();
        pontoInteresse.setCoordenadaX(1000);
        pontoInteresse.setCoordenadaY(1000);
        pontoInteresse.setDescricao("teste");
        pontoInteresseDao.salvar(pontoInteresse);

        testEntityManager.flush();

        final List<PontoInteresseTO> pontoInteresseTOS = pontoInteresseDao.buscarPontosDeInteresse(1000, 1000, 1);
        Assert.assertFalse(pontoInteresseTOS.isEmpty());
        Assert.assertEquals("teste", pontoInteresseTOS.get(0).getDescricao());
    }
}