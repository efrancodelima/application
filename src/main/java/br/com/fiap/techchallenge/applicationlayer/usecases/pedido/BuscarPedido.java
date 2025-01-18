package br.com.fiap.techchallenge.applicationlayer.usecases.pedido;

import br.com.fiap.techchallenge.applicationlayer.exceptions.messages.EnumApplicationExceptions;
import br.com.fiap.techchallenge.applicationlayer.exceptions.messages.EnumNotFoundExceptions;
import br.com.fiap.techchallenge.applicationlayer.interfaces.gateway.InPedidoGateway;
import br.com.fiap.techchallenge.applicationlayer.services.Validar;
import br.com.fiap.techchallenge.businesslayer.entities.pedido.Pedido;

public final class BuscarPedido {

  private BuscarPedido() {}

  public static Pedido buscar(InPedidoGateway gateway, Long numeroPedido) throws Exception {

    Validar.notNull(numeroPedido, EnumApplicationExceptions.PEDIDO_NUMERO_NULO);

    Pedido pedido = gateway.buscarPedido(numeroPedido);
    Validar.notNull(pedido, EnumNotFoundExceptions.PEDIDO_NAO_ENCONTRADO);

    return pedido;
  }

}
