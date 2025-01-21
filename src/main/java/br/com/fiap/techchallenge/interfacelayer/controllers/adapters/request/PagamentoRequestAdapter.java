package br.com.fiap.techchallenge.interfacelayer.controllers.adapters.request;

import br.com.fiap.techchallenge.businesslayer.entities.pedido.StatusPagamento;
import br.com.fiap.techchallenge.businesslayer.entities.pedido.StatusPagamentoEnum;
import br.com.fiap.techchallenge.businesslayer.exceptions.BusinessRuleException;
import br.com.fiap.techchallenge.interfacelayer.controllers.dtos.mercado_pago.PagamentoDto;
import java.time.LocalDateTime;

/**
 * Classe PagamentoRequestAdapter.
 */
public final class PagamentoRequestAdapter {

  private PagamentoRequestAdapter() {}

  /**
   * Adapta um objeto do tipo PagamentoDto para o tipo StatusPagamento.
   *
   * @param pagamentoDto O objeto a ser adaptado.
   * @return O objeto adaptado.
   * @throws BusinessRuleException Exceção de regra de negócio lançada pelo método.
   */
  public static StatusPagamento adaptar(PagamentoDto pagamentoDto)
      throws BusinessRuleException {

    Long codigo = pagamentoDto.getId();
    StatusPagamentoEnum status = adaptarStatus(pagamentoDto.getStatus());
    LocalDateTime dataHora = LocalDateTime.now();

    return new StatusPagamento(codigo, status, dataHora);
  }

  /**
   * Adapta uma string para o tipo StatusPagamentoEnum.
   *
   * @param statusRequisicao A string a ser adaptada.
   * @return O enum correspondente.
   * @throws BusinessRuleException Exceção de regra de negócio lançada pelo método.
   */
  private static StatusPagamentoEnum adaptarStatus(String statusRequisicao)
      throws BusinessRuleException {

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
