package br.com.fiap.techchallenge.externallayer.apis;

import br.com.fiap.techchallenge.businesslayer.entities.pedido.Pedido;
import br.com.fiap.techchallenge.externallayer.apis.interfaces.IntPedidoApi;
import br.com.fiap.techchallenge.interfacelayer.controllers.PedidoController;
import br.com.fiap.techchallenge.interfacelayer.controllers.dtos.mercadopago.PagamentoDto;
import br.com.fiap.techchallenge.interfacelayer.controllers.dtos.pedido.PedidoDto;
import br.com.fiap.techchallenge.interfacelayer.controllers.dtos.pedido.StatusPagamentoDto;
import br.com.fiap.techchallenge.interfacelayer.controllers.dtos.pedido.StatusPedidoDto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedidos")
public class PedidoApi implements IntPedidoApi {

  // Atributos
  private final PedidoController controller;

  // Construtor
  @Autowired
  public PedidoApi(PedidoController controller) {
    this.controller = controller;
  }

  @Override
  public ResponseEntity<StatusPedidoDto> fazerCheckout(PedidoDto pedidoDto) throws Exception {
    return controller.fazerCheckout(pedidoDto);
  }

  @Override
  public ResponseEntity<StatusPedidoDto> atualizarStatusPedido(long numeroPedido) throws Exception {
    return controller.atualizarStatusPedido(numeroPedido);
  }

  @Override
  public ResponseEntity<StatusPagamentoDto> consultarStatusPagamento(
      long numeroPedido) throws Exception {
    return controller.consultarStatusPagamento(numeroPedido);
  }

  @Override
  public ResponseEntity<List<Pedido>> listarPedidos() throws Exception {
    return controller.listarPedidos();
  }

  @Override
  public ResponseEntity<Void> webhookMercadoPago(PagamentoDto pagamentoDto) throws Exception {
    return controller.webhookMercadoPago(pagamentoDto);
  }

}