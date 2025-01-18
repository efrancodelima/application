package br.com.fiap.techchallenge.applicationlayer.usecases.pedido;

import br.com.fiap.techchallenge.applicationlayer.exceptions.messages.EnumNotFoundExceptions;
import br.com.fiap.techchallenge.applicationlayer.interfaces.gateway.InPedidoGateway;
import br.com.fiap.techchallenge.applicationlayer.services.Validar;
import br.com.fiap.techchallenge.businesslayer.entities.pedido.Pedido;
import br.com.fiap.techchallenge.businesslayer.entities.pedido.StatusPedidoEnum;
import java.util.Comparator;
import java.util.List;

/**
 * Listar pedidos.
 */
public final class ListarPedidos {

  private ListarPedidos() {}

  /**
   * Listar pedidos.
   *
   * @param gateway O gateway para conexão com o repositório.
   * @return A lista de pedidos.
   */
  public static List<Pedido> listar(InPedidoGateway gateway) throws Exception {
    List<Pedido> pedidos = gateway.buscarTodosOsPedidos();
    Validar.listNotEmpty(pedidos, EnumNotFoundExceptions.PEDIDO_LISTA_VAZIA);
    return filtrarOrdenarPedidos(pedidos);
  }

  // Métodos privados
  private static List<Pedido> filtrarOrdenarPedidos(List<Pedido> pedidos) {
    return pedidos.stream()
        .filter(p -> p.getStatusPedido().getStatus() != StatusPedidoEnum.AGUARDANDO_CHECKOUT &&
            p.getStatusPedido().getStatus() != StatusPedidoEnum.FINALIZADO)
        .sorted(Comparator.comparing((Pedido p) -> p.getStatusPedido().getStatus(),
            Comparator.comparingInt(ListarPedidos::getStatusOrder))
            .thenComparing(p -> p.getStatusPedido().getDataHora()))
        .toList();
  }

  private static int getStatusOrder(StatusPedidoEnum status) {
    switch (status) {
      case PRONTO:
        return 1;
      case EM_PREPARACAO:
        return 2;
      case RECEBIDO:
        return 3;
      default:
        throw new IllegalArgumentException("Status inválido: " + status);
    }
  }

}
