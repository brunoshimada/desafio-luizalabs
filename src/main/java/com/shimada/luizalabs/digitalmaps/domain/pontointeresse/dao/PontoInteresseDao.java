package com.shimada.luizalabs.digitalmaps.domain.pontointeresse.dao;

import com.shimada.luizalabs.digitalmaps.domain.pontointeresse.models.PontoInteresse;

import org.springframework.stereotype.Repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
public class PontoInteresseDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void salvar(PontoInteresse pontoInteresse) {
        entityManager.persist(pontoInteresse);
    }

    public List<PontoInteresse> listar() {
        final String hql = "from PontoInteresse p";

        final Query query = entityManager.createQuery(hql);

        return query.getResultList();
    }
}
