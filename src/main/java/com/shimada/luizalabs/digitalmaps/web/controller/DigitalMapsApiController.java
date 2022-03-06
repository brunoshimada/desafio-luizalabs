package com.shimada.luizalabs.digitalmaps.web.controller;

import com.shimada.luizalabs.digitalmaps.api.PontoInteresseServiceAPI;
import com.shimada.luizalabs.digitalmaps.web.form.PontoInteresseForm;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/digitalmaps/pontointeresse/api")
public class DigitalMapsApiController {

    private final PontoInteresseServiceAPI pontoInteresseServiceAPI;

    public DigitalMapsApiController(PontoInteresseServiceAPI pontoInteresseServiceAPI) {
        this.pontoInteresseServiceAPI = pontoInteresseServiceAPI;
    }

    @PostMapping
    public void novoPontoDeInteresse(@Valid @RequestBody final PontoInteresseForm pontoInteresseForm) {
        pontoInteresseServiceAPI.novoPontoInteresse(pontoInteresseForm);
    }

}
