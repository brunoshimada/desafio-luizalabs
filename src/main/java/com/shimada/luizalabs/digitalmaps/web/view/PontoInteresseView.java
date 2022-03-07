package com.shimada.luizalabs.digitalmaps.web.view;

import com.shimada.luizalabs.digitalmaps.domain.pontointeresse.models.PontoInteresse;
import com.shimada.luizalabs.digitalmaps.domain.pontointeresse.utils.LocalTimeConverter;

public class PontoInteresseView {

    private Integer x;
    private Integer y;
    private String opened;
    private String closed;
    private String descricao;

    private PontoInteresseView(Integer x, Integer y, String opened, String closed, String descricao) {
        this.x = x;
        this.y = y;
        this.opened = opened;
        this.closed = closed;
        this.descricao = descricao;
    }

    public static PontoInteresseView criar(final PontoInteresse pontoInteresse) {
        return new PontoInteresseView(
            pontoInteresse.getCoordenadaX(),
            pontoInteresse.getCoordenadaY(),
            LocalTimeConverter.converter(pontoInteresse.getHorarioAbertura()),
            LocalTimeConverter.converter(pontoInteresse.getHorarioFechamento()),
            pontoInteresse.getDescricao()
        );
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    public String getOpened() {
        return opened;
    }

    public String getClosed() {
        return closed;
    }

    public String getDescricao() {
        return descricao;
    }
}
