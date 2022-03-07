package com.shimada.luizalabs.digitalmaps.domain.pontointeresse.dao;

import com.shimada.luizalabs.digitalmaps.domain.pontointeresse.dao.to.PontoInteresseTO;
import com.shimada.luizalabs.digitalmaps.domain.pontointeresse.dao.to.PontoInteresseTOResultTransformer;
import com.shimada.luizalabs.digitalmaps.domain.pontointeresse.models.PontoInteresse;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @SuppressWarnings({"unchecked"})
    public List<PontoInteresse> listar() {
        final String hql = "from PontoInteresse p";

        final Query query = entityManager.createQuery(hql);

        return query.getResultList();
    }

    @SuppressWarnings({"unchecked"})
    public List<PontoInteresseTO> buscarPontosDeInteresse(Integer coordenadaX, Integer coordenadaY, Integer raio) {
        final String sql = """
            select
                p.descricao as descricao,
                p.horario_abertura as horarioAbertura,
                p.horario_fechamento as horarioFechamento
            from
                ponto_interesse p
            where
                p.coordenadax BETWEEN :coordenadaX - :raio and :coordenadaX + :raio
                and p.coordenaday BETWEEN :coordenadaY - :raio and :coordenadaY + :raio
            """;

        final Map<String, Object> params = new HashMap<>();
        params.put("coordenadaX", coordenadaX);
        params.put("coordenadaY", coordenadaY);
        params.put("raio", raio);

        return entityManager.createNativeQuery(sql)
            .unwrap(org.hibernate.query.Query.class)
            .setResultTransformer(new PontoInteresseTOResultTransformer())
            .setProperties(params)
            .list();
    }
}
