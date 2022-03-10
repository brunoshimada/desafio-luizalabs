package com.shimada.luizalabs.digitalmaps.web.form;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;

public record PontoInteresseForm(
    @NotNull(message = "Obrigatório informar a descrição") @Schema(example = "Lanchonete CDC", description = "Descrição do ponto de interesse") String descricao,
    @NotNull(message = "Obrigatório informar a coordenada X") @Schema(example = "27") @Min(value = 0, message = "Coordenadas precisam ser maior que 0") @JsonProperty("x") Integer coordenadaX,
    @NotNull(message = "Obrigatório informar a coordenada Y") @Schema(example = "32") @Min(value = 0, message = "Coordenadas precisam ser maior que 0") @JsonProperty("y") Integer coordenadaY,
    @JsonProperty("opened") @Schema(example = "09:30", description = "Aceita horários no formato HH:mm") String horarioAbertura,
    @JsonProperty("closed") @Schema(example = "19:59", description = "Aceita horários no formato HH:mm")String horarioFechamento
) {

}
