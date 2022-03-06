package com.shimada.luizalabs.digitalmaps.domain.pontointeresse.dao;

import com.shimada.luizalabs.digitalmaps.domain.pontointeresse.models.PontoInteresse;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class PontoInteresseDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void salvar(PontoInteresse pontoInteresse) {
        entityManager.persist(pontoInteresse);
    }

}
