package br.com.fiap.techchallenge.interfacelayer.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.fiap.techchallenge.interfacelayer.gateways.ClienteGateway;
import br.com.fiap.techchallenge.interfacelayer.gateways.PedidoGateway;
import br.com.fiap.techchallenge.interfacelayer.gateways.ProdutoGateway;

/**
 * Classe de testes para o controller de Pedido.
 */
class PedidoControllerTest {

  PedidoController controller;
  
  @Mock
  PedidoGateway pedidoGatewayMock;

  @Mock
  ClienteGateway clienteGatewayMock;

  @Mock
  ProdutoGateway produtoGatewayMock;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
    controller = new PedidoController(pedidoGatewayMock, clienteGatewayMock, produtoGatewayMock);
  }

  @Test
  void deveFazerCheckoutComSucesso() {}

  @Test
  void deveAtualizarStatusPedidoComSucesso() {}

  @Test
  void deveConsultarStatusPagamentoComSucesso() {}

  @Test
  void deveListarPedidosComSucesso() {}

  @Test
  void deveExecutarWebhookMercadoPagoComSucesso() {}

}

