package com.shimada.luizalabs.digitalmaps.domain.pontointeresse.services.factory;

import com.shimada.luizalabs.digitalmaps.domain.pontointeresse.models.PontoInteresse;
import com.shimada.luizalabs.digitalmaps.web.form.PontoInteresseForm;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PontoInteresseFactoryTest {

    private PontoInteresseForm formSalvar;

    @Test
    public void deveCriarComHorarioInformado() {
        dadoUmFormDeSalvar("09:00", "23:30", "Teste", 30, 40);
        final PontoInteresse pontoInteresse = PontoInteresseFactory.criarPontoInteresseAPartirDoForm(formSalvar);

        assertEquals("Teste", pontoInteresse.getDescricao());
        assertEquals(30, pontoInteresse.getCoordenadaX());
        assertEquals(40, pontoInteresse.getCoordenadaY());
        assertEquals(LocalTime.of(9, 0), pontoInteresse.getHorarioAbertura());
        assertEquals(LocalTime.of(23, 30), pontoInteresse.getHorarioFechamento());
    }

    @Test
    public void deveCriarSemHorario() {
        dadoUmFormDeSalvar(null, null, "Teste", 30, 40);
        final PontoInteresse pontoInteresse = PontoInteresseFactory.criarPontoInteresseAPartirDoForm(formSalvar);

        assertEquals("Teste", pontoInteresse.getDescricao());
        assertEquals(30, pontoInteresse.getCoordenadaX());
        assertEquals(40, pontoInteresse.getCoordenadaY());
        assertNull(pontoInteresse.getHorarioAbertura());
        assertNull(pontoInteresse.getHorarioFechamento());
    }

    private void dadoUmFormDeSalvar(String tempoAbertura, String tempoFechamento, String descricao, Integer coordenadaX, Integer coordenadaY) {
        formSalvar = new PontoInteresseForm(
            descricao,
            coordenadaX,
            coordenadaY,
            tempoAbertura,
            tempoFechamento
        );
    }
}