package br.com.fiap.techchallenge.applicationlayer.usecases.pedido;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import br.com.fiap.techchallenge.applicationlayer.interfaces.gateway.InPedidoGateway;
import br.com.fiap.techchallenge.businesslayer.entities.pedido.Pedido;
import br.com.fiap.techchallenge.businesslayer.entities.pedido.StatusPagamento;
import br.com.fiap.techchallenge.businesslayer.exceptions.BusinessRuleException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * Classe de testes para o use case AtualizarStatusPedido.
 */
class AtualizarStatusPedidoTest {

  @Mock
  InPedidoGateway gatewayMock;

  @Mock
  StatusPagamento statusPagamentoMock;

  @Mock
  Pedido pedidoMock;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
  }
  
  @Test
  void deveAtualizarStatusPedidoComSucesso() throws BusinessRuleException {

    Mockito.doReturn(pedidoMock).when(gatewayMock).buscarPedido(Mockito.anyLong());
    Mockito.doNothing().when(gatewayMock).atualizarPedido(Mockito.any());
    Mockito.doNothing().when(pedidoMock).atualizarStatusPedido();
    
    assertDoesNotThrow(() -> {
      AtualizarStatusPedido.atualizar(gatewayMock, 1L);
    });
  }

}
