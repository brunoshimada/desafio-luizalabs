package com.shimada.luizalabs.digitalmaps.domain.pontointeresse.dao.to;

import org.hibernate.transform.ResultTransformer;

import java.sql.Time;
import java.util.List;

public class PontoInteresseTOResultTransformer implements ResultTransformer {

    @Override
    public Object transformTuple(Object[] tuple, String[] aliases) {
        var pontoInteresseTo = new PontoInteresseTO();
        pontoInteresseTo.setDescricao((String) tuple[0]);
        pontoInteresseTo.setHorarioAbertura(tuple[1] != null ? ((Time) tuple[1]).toLocalTime() : null);
        pontoInteresseTo.setHorarioFechamento(tuple[2] != null ? ((Time) tuple[2]).toLocalTime() : null);
        return pontoInteresseTo;
    }

    @Override
    public List transformList(List collection) {
        return collection;
    }
}
