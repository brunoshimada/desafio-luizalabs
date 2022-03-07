package com.shimada.luizalabs.digitalmaps.web.view;

import java.util.List;

public class ListViewWrapper {

    private List<?> resultados;

    private ListViewWrapper(final List<?> resultados) {
        this.resultados = resultados;
    }

    public static ListViewWrapper criar(final List<?> resultados) {
        return new ListViewWrapper(resultados);
    }

    public List<?> getResultados() {
        return resultados;
    }
}
