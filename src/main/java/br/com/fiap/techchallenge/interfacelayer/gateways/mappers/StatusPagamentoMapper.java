package br.com.fiap.techchallenge.interfacelayer.gateways.mappers;

import br.com.fiap.techchallenge.businesslayer.entities.pedido.StatusPagamento;
import br.com.fiap.techchallenge.businesslayer.exceptions.BusinessRuleException;
import br.com.fiap.techchallenge.interfacelayer.gateways.entities.StatusPagamentoJpa;

public final class StatusPagamentoMapper {

  private StatusPagamentoMapper() {}

  public static StatusPagamentoJpa getStatusPagamentoJpa(StatusPagamento status) {
    return new StatusPagamentoJpa(status.getCodigo(), status.getStatus(), status.getDataHora());
  }

  public static StatusPagamento getStatusPagamento(StatusPagamentoJpa status)
    throws BusinessRuleException {
    
    return new StatusPagamento(status.getCodigo(), status.getStatus(), status.getDataHora());
  }

}
