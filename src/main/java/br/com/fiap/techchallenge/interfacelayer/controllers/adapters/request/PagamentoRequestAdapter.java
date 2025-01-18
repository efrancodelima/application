package br.com.fiap.techchallenge.interfacelayer.controllers.adapters.request;

import br.com.fiap.techchallenge.businesslayer.entities.pedido.StatusPagamento;
import br.com.fiap.techchallenge.businesslayer.entities.pedido.StatusPagamentoEnum;
import br.com.fiap.techchallenge.interfacelayer.controllers.dtos.mercado_pago.PagamentoDto;
import java.time.LocalDateTime;

public final class PagamentoRequestAdapter {

  private PagamentoRequestAdapter() {}

  public static StatusPagamento adaptar(PagamentoDto pagamentoDto) throws Exception {

    Long codigo = pagamentoDto.getId();
    StatusPagamentoEnum status = adaptarStatus(pagamentoDto.getStatus());
    LocalDateTime dataHora = LocalDateTime.now();

    return new StatusPagamento(codigo, status, dataHora);
  }

  private static StatusPagamentoEnum adaptarStatus(String statusRequisicao) throws Exception {

    statusRequisicao = statusRequisicao == null ? "" : statusRequisicao.trim().toUpperCase();

    switch (statusRequisicao) {
      case "PENDING", "IN_PROCESS":
        return StatusPagamentoEnum.AGUARDANDO_PAGAMENTO;
      case "APPROVED":
        return StatusPagamentoEnum.APROVADO;
      case "REJECTED":
        return StatusPagamentoEnum.REPROVADO;
      default:
        // Retorna erro
        return StatusPagamentoEnum.fromString(statusRequisicao);
    }
  }
}
