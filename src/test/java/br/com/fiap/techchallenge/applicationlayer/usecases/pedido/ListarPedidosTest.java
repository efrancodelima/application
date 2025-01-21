package br.com.fiap.techchallenge.applicationlayer.usecases.pedido;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import br.com.fiap.techchallenge.applicationlayer.interfaces.gateway.InPedidoGateway;
import br.com.fiap.techchallenge.businesslayer.entities.cliente.Cliente;
import br.com.fiap.techchallenge.businesslayer.entities.pedido.ItemPedido;
import br.com.fiap.techchallenge.businesslayer.entities.pedido.Pedido;
import br.com.fiap.techchallenge.businesslayer.entities.pedido.StatusPagamento;
import br.com.fiap.techchallenge.businesslayer.entities.pedido.StatusPedido;
import br.com.fiap.techchallenge.businesslayer.entities.pedido.StatusPedidoEnum;
import br.com.fiap.techchallenge.businesslayer.exceptions.BusinessRuleException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * Classe de testes para o use case ListarPedidos.
 */
class ListarPedidosTest {

  @Mock
  InPedidoGateway gatewayMock;

  @Mock
  Cliente clienteMock;

  @Mock
  ItemPedido itemPedidoMock;

  @Mock
  StatusPagamento statusPagamentoMock;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void deveFazerCheckoutComSucesso() throws BusinessRuleException {

    Mockito.doReturn(getListaPedido()).when(gatewayMock).buscarTodosOsPedidos();
    
    assertDoesNotThrow(() -> {
      ListarPedidos.listar(gatewayMock);
    });
  }

  // Métodos auxiliares dos testes
  List<Pedido> getListaPedido() throws BusinessRuleException {
    List<ItemPedido> listaItens = new ArrayList<>();
    listaItens.add(itemPedidoMock);

    var pedido1 = new Pedido(1L, clienteMock, listaItens,
        LocalDateTime.now(), statusPagamentoMock,
        new StatusPedido(StatusPedidoEnum.RECEBIDO, LocalDateTime.now()));
    
    var pedido2 = new Pedido(1L, clienteMock, listaItens,
        LocalDateTime.now(), statusPagamentoMock,
        new StatusPedido(StatusPedidoEnum.EM_PREPARACAO, LocalDateTime.now()));
    
    var pedido3 = new Pedido(1L, clienteMock, listaItens,
        LocalDateTime.now(), statusPagamentoMock,
        new StatusPedido(StatusPedidoEnum.PRONTO, LocalDateTime.now()));
    
    var pedido4 = new Pedido(1L, clienteMock, listaItens,
        LocalDateTime.now(), statusPagamentoMock,
        new StatusPedido(StatusPedidoEnum.FINALIZADO, LocalDateTime.now()));

    List<Pedido> listaPedidos = new ArrayList<>();
    listaPedidos.add(pedido1);
    listaPedidos.add(pedido2);
    listaPedidos.add(pedido3);
    listaPedidos.add(pedido4);

    return listaPedidos;
  }

}
