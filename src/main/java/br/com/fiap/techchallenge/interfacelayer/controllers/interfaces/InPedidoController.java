package br.com.fiap.techchallenge.interfacelayer.controllers.interfaces;

import br.com.fiap.techchallenge.businesslayer.entities.pedido.Pedido;
import br.com.fiap.techchallenge.interfacelayer.controllers.dtos.mercadopago.PagamentoDto;
import br.com.fiap.techchallenge.interfacelayer.controllers.dtos.pedido.PedidoDto;
import br.com.fiap.techchallenge.interfacelayer.controllers.dtos.pedido.StatusPagamentoDto;
import br.com.fiap.techchallenge.interfacelayer.controllers.dtos.pedido.StatusPedidoDto;
import java.util.List;
import org.springframework.http.ResponseEntity;

/**
 * Interface InPedidoController.
 */
public interface InPedidoController {

  /**
   * Faz o checkout do pedido.
   *
   * @param pedidoDto O pedido para o qual será feito o checkout.
   * @return O status do pedido.
   * @throws Exception Exceção lançada pelo método.
   */
  ResponseEntity<StatusPedidoDto> fazerCheckout(PedidoDto pedidoDto) throws Exception;

  /**
   * Atualiza o status do pedido.
   *
   * @param numeroPedido O número do pedido a ser atualizado.
   * @return O status do pedido atualizado.
   * @throws Exception Exceção lançada pelo método.
   */
  ResponseEntity<StatusPedidoDto> atualizarStatusPedido(Long numeroPedido) throws Exception;

  /**
   * Consulta o status do pagamento do pedido.
   *
   * @param numeroPedido O número do pedido cujo status do pagamento será consultado.
   * @return O status do pagamento do pedido.
   * @throws Exception Exceção lançada pelo método.
   */
  ResponseEntity<StatusPagamentoDto> consultarStatusPagamento(Long numeroPedido) throws Exception;

  /**
   * Lista todos os pedidos não finalizados.
   *
   * @return Lista com todos os pedidos não finalizados.
   * @throws Exception Exceção lançada pelo método.
   */
  ResponseEntity<List<Pedido>> listarPedidos() throws Exception;

  /**
   * Recebe as notificações de pagamento do Mercado livre.
   *
   * @param pagamentoDto A notificação do Mercado Livre.
   * @throws Exception Exceção lançada pelo método.
   */
  ResponseEntity<Void> webhookMercadoPago(PagamentoDto pagamentoDto) throws Exception;

}
