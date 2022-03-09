package com.shimada.luizalabs.digitalmaps.domain.pontointeresse.services;

import com.shimada.luizalabs.digitalmaps.config.exceptions.BusinessException;
import com.shimada.luizalabs.digitalmaps.domain.pontointeresse.dao.PontoInteresseDao;
import com.shimada.luizalabs.digitalmaps.domain.pontointeresse.models.PontoInteresse;
import com.shimada.luizalabs.digitalmaps.web.form.BuscaPontoInteresseForm;
import com.shimada.luizalabs.digitalmaps.web.form.PontoInteresseForm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PontoInteresseServiceImplTest {

    private PontoInteresseServiceImpl pontoInteresseService;

    @Mock
    private PontoInteresseDao pontoInteresseDao;

    private BuscaPontoInteresseForm form;
    private PontoInteresseForm formSalvar;

    @BeforeEach
    public void init() {
        pontoInteresseService = new PontoInteresseServiceImpl(
            pontoInteresseDao
        );
    }

    @Test
    public void deveListar() {
        pontoInteresseService.listar();
        verify(pontoInteresseDao).listar();
    }

    @Test
    public void deveBuscarOsPontosDeInteresse() {
        dadoUmFormDeBusca(30, 40, 10);
        pontoInteresseService.buscarPontosDeInteresse(form);
        verify(pontoInteresseDao).buscarPontosDeInteresse(30, 40, 10);
    }

    @Test
    public void deveTerErroSeFormInvalidoAoCriarNovoPontoDeInterese() {
        dadoUmFormDeSalvar("19:00", "14:30", "Teste", 30, 40);
        final BusinessException businessException = assertThrows(BusinessException.class,
                                                                            () -> pontoInteresseService.novoPontoInteresse(formSalvar));
        assertEquals("Horário de abertura não pode ser depois do horário de fechamento", businessException.getMessage());
    }

    @Test
    public void deveSalvarNormalmenteNovoPontoDeInteresse() {
        ArgumentCaptor<PontoInteresse> argumentCaptor = ArgumentCaptor.forClass(PontoInteresse.class);

        dadoUmFormDeSalvar("12:00", "22:30", "Teste", 30, 40);

        pontoInteresseService.novoPontoInteresse(formSalvar);

        verify(pontoInteresseDao).salvar(argumentCaptor.capture());
        final PontoInteresse pontoInteresseSendoSalvo = argumentCaptor.getValue();
        assertEquals("Teste", pontoInteresseSendoSalvo.getDescricao());
        assertEquals(30, pontoInteresseSendoSalvo.getCoordenadaX());
        assertEquals(40, pontoInteresseSendoSalvo.getCoordenadaY());
        assertEquals(LocalTime.of(12, 0), pontoInteresseSendoSalvo.getHorarioAbertura());
        assertEquals(LocalTime.of(22, 30), pontoInteresseSendoSalvo.getHorarioFechamento());
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

    private void dadoUmFormDeBusca(final Integer coordenadaX, final Integer coordenadaY, final Integer raio) {
        form = new BuscaPontoInteresseForm(
            coordenadaX,
            coordenadaY,
            raio,
            ""
        );
    }

}