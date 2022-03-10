package com.shimada.luizalabs.digitalmaps.web.controller;

import com.shimada.luizalabs.digitalmaps.api.PontoInteresseServiceAPI;
import com.shimada.luizalabs.digitalmaps.web.form.BuscaPontoInteresseForm;
import com.shimada.luizalabs.digitalmaps.web.form.PontoInteresseForm;
import com.shimada.luizalabs.digitalmaps.web.view.ListViewWrapper;
import com.shimada.luizalabs.digitalmaps.web.view.PontoInteresseBuscaView;
import com.shimada.luizalabs.digitalmaps.web.view.PontoInteresseView;
import com.shimada.luizalabs.digitalmaps.web.view.factory.PontoInteresseBuscaViewFactory;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

import javax.validation.Valid;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/digitalmaps/pontointeresse/api")
@Tag(name = "digitalMaps", description = "API's implementadas para o desafio")
public class DigitalMapsApiController {

    private final PontoInteresseServiceAPI pontoInteresseServiceAPI;

    public DigitalMapsApiController(PontoInteresseServiceAPI pontoInteresseServiceAPI) {
        this.pontoInteresseServiceAPI = pontoInteresseServiceAPI;
    }

    @Operation(summary = "Cria um novo ponto de interesse", security = @SecurityRequirement(name = "basicAuth"))
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Novo ponto de interesse salvo com sucesso", content = @Content(examples = @ExampleObject(value = "{\"message\": \"Novo ponto de interesse salvo com sucesso!\"}"))),
        @ApiResponse(responseCode = "400",
            description = "Erro na validação dos parâmetros enviados",
            content = @Content(examples = {
                @ExampleObject(name = "Perído inválido", description = "Se forem informados ambos os horários e o de abertura for depois do de fechamento", value = "{\"error\": \"Horário de abertura não pode ser depois do horário de fechamento\"}"),
                @ExampleObject(name = "Período inconsistente", description = "Se for informado somente um dos horários, o ponto de interesse precisa ter um horário de abertura e fechamento ou nenhum deles", value = "{\"error\": \"Informe os dois horários, ou nenhum se não possuir\"}")}
            )
        ),
    })
    @PostMapping(consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> novoPontoDeInteresse(@Valid @RequestBody final PontoInteresseForm pontoInteresseForm) {
        pontoInteresseServiceAPI.novoPontoInteresse(pontoInteresseForm);
        return new ResponseEntity<>(Collections.singletonMap("message", "Novo ponto de interesse salvo com sucesso!"), HttpStatus.CREATED);
    }

    @Operation(summary = "Lista todos os pontos de interesse",
        description = "Lista todos os pontos de intersse sem filtro\n\nA api devolve a lista em um atributo \"resultado\"\n\nCaso o ponto de interesse não tenha um horário não será enviado os atributos \"opened\" e \"closed\"",
        security = @SecurityRequirement(name = "basicAuth")
    )
    @ApiResponse(responseCode = "200", description = "Busca com sucesso", content = @Content(array = @ArraySchema(schema = @Schema(implementation = PontoInteresseView.class))))
    @GetMapping(produces = {"application/json"})
    public ListViewWrapper listarPontosDeInteresse() {
        return ListViewWrapper.criar(pontoInteresseServiceAPI.listar().stream().map(PontoInteresseView::criar).toList());
    }

    @Operation(summary = "Busca pontos de interesse",
        description = "Busca os pontos de interesse filtrando com os parâmetros enviados \n\nA api devolve a lista em um atributo \"resultado\"",
        security = @SecurityRequirement(name = "basicAuth")
    )
    @ApiResponse(responseCode = "200", description = "Busca com sucesso", content = @Content(array = @ArraySchema(schema = @Schema(implementation = PontoInteresseBuscaView.class))))
    @GetMapping(value = "/buscar", produces = {"application/json"})
    public ListViewWrapper buscarPontosDeInteresse(@ParameterObject BuscaPontoInteresseForm buscaPontoInteresseForm) {
        return ListViewWrapper.criar(
            pontoInteresseServiceAPI.buscarPontosDeInteresse(buscaPontoInteresseForm).stream()
                .map(pontoInteresseTO -> PontoInteresseBuscaViewFactory.init(pontoInteresseTO, buscaPontoInteresseForm.horario()).gerar())
                .toList());
    }

}
