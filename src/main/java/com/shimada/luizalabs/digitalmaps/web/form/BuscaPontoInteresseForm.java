package com.shimada.luizalabs.digitalmaps.web.form;

import com.fasterxml.jackson.annotation.JsonProperty;

public record BuscaPontoInteresseForm(
    @JsonProperty("x") Integer coordenadaX,
    @JsonProperty("y") Integer coordenadaY,
    @JsonProperty("mts") Integer raio,
    @JsonProperty("hr") String horario
) {

}
