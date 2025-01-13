package br.com.fiap.techchallenge.interfacelayer.controllers.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import br.com.fiap.techchallenge.businesslayer.entities.pedido.Pedido;
import br.com.fiap.techchallenge.interfacelayer.controllers.dtos.mercado_pago.PagamentoDto;
import br.com.fiap.techchallenge.interfacelayer.controllers.dtos.pedido.PedidoDto;
import br.com.fiap.techchallenge.interfacelayer.controllers.dtos.pedido.StatusPagamentoDto;
import br.com.fiap.techchallenge.interfacelayer.controllers.dtos.pedido.StatusPedidoDto;

public interface IPedidoController {

    ResponseEntity<StatusPedidoDto> fazerCheckout(PedidoDto pedidoDto) throws Exception;

    ResponseEntity<StatusPedidoDto> atualizarStatusPedido(Long numeroPedido) throws Exception;

    ResponseEntity<StatusPagamentoDto> consultarStatusPagamento(Long numeroPedido) throws Exception;

    ResponseEntity<List<Pedido>> listarPedidos() throws Exception;

    ResponseEntity<Void> webhookMercadoPago(PagamentoDto pagamentoDto) throws Exception;

}
