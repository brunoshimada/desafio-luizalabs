package com.shimada.luizalabs.digitalmaps.web.form;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public record PontoInteresseForm(
    @NotNull(message = "Obrigatório informar a descrição") String descricao,
    @NotNull(message = "Obrigatório informar a coordenada X") @Min(value = 0, message = "Coordenadas precisam ser maior que 0") Integer coordenadaX,
    @NotNull(message = "Obrigatório informar a coordenada Y") @Min(value = 0, message = "Coordenadas precisam ser maior que 0") Integer coordenadaY,
    @NotNull(message = "Obrigatório informar o horário de abertura") String horarioAbertura,
    @NotNull(message = "Obrigatório informar o horário de fechamento") String horarioFechamento
) {

}
