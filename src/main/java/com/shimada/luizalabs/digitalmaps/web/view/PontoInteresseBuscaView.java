package com.shimada.luizalabs.digitalmaps.web.view;

import io.swagger.v3.oas.annotations.media.Schema;

public record PontoInteresseBuscaView(
    @Schema(example = "Shopping ABD") String descricao,
    @Schema(example = "Aberto", description = "Opção Aberto/Fechado dependendo do horário informado na busca") String status
) {
}
