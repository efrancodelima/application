package br.com.fiap.techchallenge.applicationlayer.usecases.pedido;

import java.time.LocalDateTime;

import br.com.fiap.techchallenge.applicationlayer.exceptions.messages.EnumApplicationExceptions;
import br.com.fiap.techchallenge.applicationlayer.interfaces.gateway.InPedidoGateway;
import br.com.fiap.techchallenge.applicationlayer.services.Validar;
import br.com.fiap.techchallenge.businesslayer.entities.pedido.Pedido;
import br.com.fiap.techchallenge.businesslayer.entities.pedido.StatusPagamento;
import br.com.fiap.techchallenge.businesslayer.entities.pedido.StatusPagamentoEnum;

public final class FazerCheckoutPedido {

  private FazerCheckoutPedido() {}

  public static Pedido fazerCheckout(InPedidoGateway gateway, Pedido pedido) throws Exception {

    Validar.notNull(pedido, EnumApplicationExceptions.PEDIDO_NULO);

    pedido = gateway.gravarPedido(pedido);

    // Por ora, o código do pagamento é igual ao do pedido
    // Após a integração com o Mercado Pago isso vai mudar
    long codigo = pedido.getNumero();
    var status = StatusPagamentoEnum.AGUARDANDO_PAGAMENTO;
    var dataHora = LocalDateTime.now();
    var pagamento = new StatusPagamento(codigo, status, dataHora);
    pedido.setStatusPagamento(pagamento);
    gateway.gravarPedido(pedido);
    return pedido;
  }

}
