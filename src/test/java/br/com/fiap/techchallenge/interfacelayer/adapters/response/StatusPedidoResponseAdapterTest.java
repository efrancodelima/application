package br.com.fiap.techchallenge.interfacelayer.adapters.response;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import br.com.fiap.techchallenge.businesslayer.entities.pedido.Pedido;
import br.com.fiap.techchallenge.businesslayer.entities.pedido.StatusPagamento;
import br.com.fiap.techchallenge.businesslayer.entities.pedido.StatusPagamentoEnum;
import br.com.fiap.techchallenge.businesslayer.entities.pedido.StatusPedido;
import br.com.fiap.techchallenge.businesslayer.entities.pedido.StatusPedidoEnum;
import br.com.fiap.techchallenge.businesslayer.exceptions.BusinessRuleException;
import br.com.fiap.techchallenge.interfacelayer.controllers.adapters.response.StatusPagamentoResponseAdapter;
import br.com.fiap.techchallenge.interfacelayer.controllers.adapters.response.StatusPedidoResponseAdapter;
import br.com.fiap.techchallenge.interfacelayer.controllers.dtos.pedido.StatusPedidoDto;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

/**
 * Classe de testes para ResponseAdapter.
 */
class StatusPedidoResponseAdapterTest {

  private AutoCloseable closeable;

  @Mock
  Pedido pedidoMock;

  @BeforeEach
  void setup() {
    closeable = MockitoAnnotations.openMocks(this);
  }

  @AfterEach
  void tearDown() throws Exception {
    closeable.close();
  }

  @Test
  void deveAdaptarPedidoParaResponseEntityStatusPedidoDto() throws BusinessRuleException {

    // Arrange
    var statusPedido = new StatusPedido(StatusPedidoEnum.EM_PREPARACAO, LocalDateTime.now());
    Mockito.doReturn(statusPedido).when(pedidoMock).getStatusPedido();

    var httpStatus = HttpStatus.OK;

    // Act and assert
    assertDoesNotThrow(() -> {
      var resposta = StatusPedidoResponseAdapter.adaptar(pedidoMock, httpStatus);

      assertEquals(httpStatus, resposta.getStatusCode());
      assertEquals(pedidoMock.getStatusPedido().getStatus(), resposta.getBody().getStatus());
    });
  }

  @Test
  void deveAdaptarListPedidoParaResponseEntityListStatusPedidoDto() throws BusinessRuleException {
    
    // Arrange
    var listaPedidos = new ArrayList<Pedido>();
    listaPedidos.add(pedidoMock);

    var statusPedido = new StatusPedido(StatusPedidoEnum.EM_PREPARACAO, LocalDateTime.now());    
    Mockito.doReturn(statusPedido).when(pedidoMock).getStatusPedido();

    Long numeroPedido = Long.valueOf(123);
    Mockito.doReturn(numeroPedido).when(pedidoMock).getNumero();

    var httpStatus = HttpStatus.OK;

    // Act and assert
    assertDoesNotThrow(() -> {
      var resposta = StatusPedidoResponseAdapter.adaptar(listaPedidos, httpStatus);

      assertEquals(httpStatus, resposta.getStatusCode());
      assertEquals(1, resposta.getBody().size());
      assertEquals(pedidoMock.getNumero(), resposta.getBody().get(0).getNumeroPedido());
    });
  }
    
}
