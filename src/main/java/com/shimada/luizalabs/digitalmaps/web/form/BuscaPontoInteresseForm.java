package com.shimada.luizalabs.digitalmaps.web.form;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;

public record BuscaPontoInteresseForm(
    @Schema(example = "10") @Parameter(description = "Coordenada do ponto X") Integer coordenadaX,
    @Schema(example = "15") @Parameter(description = "Coordenada do ponto Y") Integer coordenadaY,
    @Schema(example = "50") @Parameter(description = "Raio de distância das coordenadas para buscar os pontos de interesse") Integer raio,
    @Schema(example = "13:30") @Parameter(description = "Horário para consultar o funcionamento dos pontos de interesse") String horario
) {

    /**
     * Esses métodos foram criados somente para poder exibir na doc da API
     */
    @Deprecated
    public Integer getCoordenadaX() {
        return coordenadaX();
    }
    @Deprecated
    public Integer getCoordenadaY() {
        return coordenadaY();
    }
    @Deprecated
    public Integer getRaio() {
        return raio();
    }
    @Deprecated
    public String getHorario() {
        return horario();
    }
}
