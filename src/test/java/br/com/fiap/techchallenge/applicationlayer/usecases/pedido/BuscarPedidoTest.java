package br.com.fiap.techchallenge.applicationlayer.usecases.pedido;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import br.com.fiap.techchallenge.applicationlayer.interfaces.gateway.InPedidoGateway;
import br.com.fiap.techchallenge.businesslayer.entities.pedido.Pedido;
import br.com.fiap.techchallenge.businesslayer.exceptions.BusinessRuleException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * Classe de testes para o use case BuscarPedido.
 */
class BuscarPedidoTest {

  @Mock
  InPedidoGateway gatewayMock;

  @Mock
  Pedido pedidoMock;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void deveBuscarPedidoComSucesso() throws BusinessRuleException {

    Mockito.doReturn(pedidoMock).when(gatewayMock).buscarPedido(Mockito.anyLong());
    
    assertDoesNotThrow(() -> {
      BuscarPedido.buscar(gatewayMock, 1L);
    });
  }

}
