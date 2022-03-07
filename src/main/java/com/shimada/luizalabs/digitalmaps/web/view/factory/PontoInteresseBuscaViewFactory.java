package com.shimada.luizalabs.digitalmaps.web.view.factory;

import com.shimada.luizalabs.digitalmaps.domain.pontointeresse.dao.to.PontoInteresseTO;
import com.shimada.luizalabs.digitalmaps.utils.LocalTimeConverter;
import com.shimada.luizalabs.digitalmaps.web.view.PontoInteresseBuscaView;
import com.shimada.luizalabs.digitalmaps.web.view.enumeration.StatusPontoInteresseEnum;

import java.time.LocalTime;

public class PontoInteresseBuscaViewFactory {

    private PontoInteresseTO pontoInteresseTO;
    private String horario;

    public PontoInteresseBuscaViewFactory(final PontoInteresseTO pontoInteresseTO, final String horario) {
        this.pontoInteresseTO = pontoInteresseTO;
        this.horario = horario;
    }

    public static PontoInteresseBuscaViewFactory init(final PontoInteresseTO pontoInteresseTO, final String horario) {
        return new PontoInteresseBuscaViewFactory(pontoInteresseTO, horario);
    }

    public PontoInteresseBuscaView gerar() {
        return new PontoInteresseBuscaView(pontoInteresseTO.getDescricao(),
                                           isAbertoNoHorarioInformado());
    }

    private String isAbertoNoHorarioInformado() {
        final LocalTime horarioInformado = LocalTimeConverter.converter(horario);
        final boolean isPeriodoValidavel = pontoInteresseTO.getHorarioAbertura() != null && pontoInteresseTO.getHorarioFechamento() != null;

        final boolean isNoPeriodo = isPeriodoValidavel && pontoInteresseTO.getHorarioAbertura().isBefore(horarioInformado) &&
                                    pontoInteresseTO.getHorarioFechamento().isAfter(horarioInformado);

        return isNoPeriodo || !isPeriodoValidavel ? StatusPontoInteresseEnum.ABERTO.getDescricao() : StatusPontoInteresseEnum.FECHADO.getDescricao();
    }
}
