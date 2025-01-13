package br.com.fiap.techchallenge.applicationlayer.usecases.pedido;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import br.com.fiap.techchallenge.applicationlayer.exceptions.messages.EnumNotFoundExceptions;
import br.com.fiap.techchallenge.applicationlayer.interfaces.gateway.InPedidoGateway;
import br.com.fiap.techchallenge.applicationlayer.services.Validar;
import br.com.fiap.techchallenge.businesslayer.entities.pedido.Pedido;
import br.com.fiap.techchallenge.businesslayer.entities.pedido.StatusPedidoEnum;

public final class ListarPedidos {

  public static List<Pedido> listar(InPedidoGateway gateway) throws Exception {
    List<Pedido> pedidos = gateway.buscarTodosOsPedidos();
    Validar.listNotEmpty(pedidos, EnumNotFoundExceptions.PEDIDO_LISTA_VAZIA);
    return filtrarEOrdenarPedidos(pedidos);
  }

  // Métodos privados
  private static List<Pedido> filtrarEOrdenarPedidos(List<Pedido> pedidos) {
    return pedidos.stream()
        .filter(p -> p.getStatusPedido().getStatus() != StatusPedidoEnum.AGUARDANDO_CHECKOUT &&
            p.getStatusPedido().getStatus() != StatusPedidoEnum.FINALIZADO)
        .sorted(Comparator.comparing((Pedido p) -> p.getStatusPedido().getStatus(),
            Comparator.comparingInt(ListarPedidos::getStatusOrder))
            .thenComparing(p -> p.getStatusPedido().getDataHora()))
        .collect(Collectors.toList());
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
