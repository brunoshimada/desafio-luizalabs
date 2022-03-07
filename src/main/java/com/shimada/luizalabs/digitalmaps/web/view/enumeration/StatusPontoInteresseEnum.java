package com.shimada.luizalabs.digitalmaps.web.view.enumeration;

public enum StatusPontoInteresseEnum {

    ABERTO("Aberto"),
    FECHADO("Fechado");

    private String descricao;

    private StatusPontoInteresseEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
