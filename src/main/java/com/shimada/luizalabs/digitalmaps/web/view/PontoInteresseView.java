package com.shimada.luizalabs.digitalmaps.web.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.shimada.luizalabs.digitalmaps.domain.pontointeresse.models.PontoInteresse;
import com.shimada.luizalabs.digitalmaps.utils.LocalTimeConverter;

import io.swagger.v3.oas.annotations.media.Schema;

public class PontoInteresseView {

    @Schema(example = "10")
    private Integer x;
    @Schema(example = "20")
    private Integer y;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Schema(required = false, example = "10:00")
    private String opened;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Schema(required = false, example = "19:00")
    private String closed;
    @Schema(example = "Restaurante XYZ")
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
