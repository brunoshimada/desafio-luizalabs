package com.shimada.luizalabs.digitalmaps.web.controller;

import com.shimada.luizalabs.digitalmaps.api.PontoInteresseServiceAPI;
import com.shimada.luizalabs.digitalmaps.web.form.BuscaPontoInteresseForm;
import com.shimada.luizalabs.digitalmaps.web.form.PontoInteresseForm;
import com.shimada.luizalabs.digitalmaps.web.view.ListViewWrapper;
import com.shimada.luizalabs.digitalmaps.web.view.PontoInteresseView;
import com.shimada.luizalabs.digitalmaps.web.view.factory.PontoInteresseBuscaViewFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

import javax.validation.Valid;

@RestController
@RequestMapping("/digitalmaps/pontointeresse/api")
public class DigitalMapsApiController {

    private final PontoInteresseServiceAPI pontoInteresseServiceAPI;

    public DigitalMapsApiController(PontoInteresseServiceAPI pontoInteresseServiceAPI) {
        this.pontoInteresseServiceAPI = pontoInteresseServiceAPI;
    }

    @PostMapping
    public ResponseEntity<?> novoPontoDeInteresse(@Valid @RequestBody final PontoInteresseForm pontoInteresseForm) {
        pontoInteresseServiceAPI.novoPontoInteresse(pontoInteresseForm);
        return new ResponseEntity<>(Collections.singletonMap("message","Novo ponto de interesse salvo com sucesso!"), HttpStatus.CREATED);
    }

    @GetMapping
    public ListViewWrapper listarPontosDeInteresse() {
        return ListViewWrapper.criar(pontoInteresseServiceAPI.listar().stream().map(PontoInteresseView::criar).toList());
    }

    @GetMapping(value = "/buscar")
    public ListViewWrapper buscarPontosDeInteresse(@RequestBody BuscaPontoInteresseForm buscaPontoInteresseForm) {
        return ListViewWrapper.criar(
            pontoInteresseServiceAPI.buscarPontosDeInteresse(buscaPontoInteresseForm).stream()
                .map(pontoInteresseTO -> PontoInteresseBuscaViewFactory.init(pontoInteresseTO, buscaPontoInteresseForm.horario()).gerar())
                .toList());
    }

}
