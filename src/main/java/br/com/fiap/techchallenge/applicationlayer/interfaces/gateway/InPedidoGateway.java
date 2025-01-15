package br.com.fiap.techchallenge.applicationlayer.interfaces.gateway;

import br.com.fiap.techchallenge.businesslayer.entities.pedido.Pedido;
import java.util.List;

/**
 * Interface para o gateway do pedido.
 */
public interface InPedidoGateway {

  /**
   * Grava o pedido.
   *
   * @param pedido O pedido que será gravado.
   * @return O pedido que foi gravado.
   * @throws Exception Exceção lançado durante a operação.
   */
  Pedido gravarPedido(Pedido pedido) throws Exception;

  void atualizarPedido(Pedido pedido) throws Exception;

  Pedido buscarPedido(long numeroPedido) throws Exception;

  Pedido buscarPedidoPeloCodigoPagamento(long codigoPagamento) throws Exception;

  List<Pedido> buscarTodosOsPedidos() throws Exception;

}
