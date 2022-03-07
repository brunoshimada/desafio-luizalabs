package com.shimada.luizalabs.digitalmaps.web.form;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public record PontoInteresseForm(
    @NotNull(message = "Obrigatório informar a descrição") String descricao,
    @NotNull(message = "Obrigatório informar a coordenada X") @Min(value = 0, message = "Coordenadas precisam ser maior que 0") @JsonProperty("x") Integer coordenadaX,
    @NotNull(message = "Obrigatório informar a coordenada Y") @Min(value = 0, message = "Coordenadas precisam ser maior que 0") @JsonProperty("y") Integer coordenadaY,
    @JsonProperty("opened") String horarioAbertura,
    @JsonProperty("closed") String horarioFechamento
) {

}
