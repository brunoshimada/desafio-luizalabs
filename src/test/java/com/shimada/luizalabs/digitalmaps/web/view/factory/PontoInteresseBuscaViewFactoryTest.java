package com.shimada.luizalabs.digitalmaps.web.view.factory;

import com.shimada.luizalabs.digitalmaps.domain.pontointeresse.dao.to.PontoInteresseTO;
import com.shimada.luizalabs.digitalmaps.web.view.PontoInteresseBuscaView;
import com.shimada.luizalabs.digitalmaps.web.view.enumeration.StatusPontoInteresseEnum;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PontoInteresseBuscaViewFactoryTest {

    private PontoInteresseTO pontoInteresseTO;
    private String horarioInformado;
    private PontoInteresseBuscaView pontoInteresseBuscaView;

    @Test
    @DisplayName("Deve ter status aberto se não tiver nenhum horário informado")
    public void caseA() {
        dadoUmPontoInteresseTo(null, null);
        dadoOHorarioBuscado("12:00");
        aoCriarParaView();
        deveTerStatusAberto();
    }

    @Test
    @DisplayName("Deve ter status aberto se horário informado dentro do buscado")
    public void caseB() {
        dadoUmPontoInteresseTo(LocalTime.of(11, 59), LocalTime.of(12, 1));
        dadoOHorarioBuscado("12:00");
        aoCriarParaView();
        deveTerStatusAberto();
    }

    @Test
    @DisplayName("Deve ter status fechado se horário informado fora do buscado")
    public void caseC() {
        dadoUmPontoInteresseTo(LocalTime.of(9, 0), LocalTime.of(16, 30));
        dadoOHorarioBuscado("16:31");
        aoCriarParaView();
        deveTerStatusFechado();
    }

    private void dadoUmPontoInteresseTo(LocalTime abertura, LocalTime fechamento) {
        pontoInteresseTO = new PontoInteresseTO();
        pontoInteresseTO.setHorarioAbertura(abertura);
        pontoInteresseTO.setHorarioFechamento(fechamento);
    }

    private void dadoOHorarioBuscado(String horarioBuscado) {
        horarioInformado = horarioBuscado;
    }

    private void aoCriarParaView() {
        pontoInteresseBuscaView = PontoInteresseBuscaViewFactory.init(pontoInteresseTO, horarioInformado).gerar();
    }

    private void deveTerStatusAberto() {
        assertEquals(StatusPontoInteresseEnum.ABERTO.getDescricao(), pontoInteresseBuscaView.status());
    }

    private void deveTerStatusFechado() {
        assertEquals(StatusPontoInteresseEnum.FECHADO.getDescricao(), pontoInteresseBuscaView.status());
    }
}