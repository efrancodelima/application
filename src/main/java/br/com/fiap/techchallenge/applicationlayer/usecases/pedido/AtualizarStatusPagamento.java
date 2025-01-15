package br.com.fiap.techchallenge.applicationlayer.usecases.pedido;

import br.com.fiap.techchallenge.applicationlayer.exceptions.messages.EnumApplicationExceptions;
import br.com.fiap.techchallenge.applicationlayer.exceptions.messages.EnumNotFoundExceptions;
import br.com.fiap.techchallenge.applicationlayer.interfaces.gateway.InPedidoGateway;
import br.com.fiap.techchallenge.applicationlayer.services.Validar;
import br.com.fiap.techchallenge.businesslayer.entities.pedido.Pedido;
import br.com.fiap.techchallenge.businesslayer.entities.pedido.StatusPagamento;

public final class AtualizarStatusPagamento {

  public static void atualizar(InPedidoGateway gateway, StatusPagamento statusPagamento)
      throws Exception {

    Validar.notNull(statusPagamento, EnumApplicationExceptions.PAGAMENTO_STATUS_NULO);

    Pedido pedido = gateway.buscarPedidoPeloCodigoPagamento(statusPagamento.getCodigo());
    Validar.notNull(pedido, EnumNotFoundExceptions.PEDIDO_NAO_ENCONTRADO);

    pedido.setStatusPagamento(statusPagamento);
    gateway.atualizarPedido(pedido);
  }

}
