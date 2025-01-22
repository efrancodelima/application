package br.com.fiap.techchallenge.interfacelayer.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import br.com.fiap.techchallenge.applicationlayer.usecases.cliente.BuscarClientePeloCpf;
import br.com.fiap.techchallenge.applicationlayer.usecases.pedido.AtualizarStatusPedido;
import br.com.fiap.techchallenge.applicationlayer.usecases.pedido.FazerCheckoutPedido;
import br.com.fiap.techchallenge.applicationlayer.usecases.produto.BuscarProduto;
import br.com.fiap.techchallenge.businesslayer.entities.cliente.Cliente;
import br.com.fiap.techchallenge.businesslayer.entities.pedido.ItemPedido;
import br.com.fiap.techchallenge.businesslayer.entities.pedido.Pedido;
import br.com.fiap.techchallenge.businesslayer.entities.pedido.StatusPagamento;
import br.com.fiap.techchallenge.businesslayer.entities.pedido.StatusPagamentoEnum;
import br.com.fiap.techchallenge.businesslayer.entities.pedido.StatusPedido;
import br.com.fiap.techchallenge.businesslayer.entities.pedido.StatusPedidoEnum;
import br.com.fiap.techchallenge.businesslayer.entities.produto.Produto;
import br.com.fiap.techchallenge.businesslayer.exceptions.BusinessRuleException;
import br.com.fiap.techchallenge.interfacelayer.controllers.dtos.pedido.ItemPedidoDto;
import br.com.fiap.techchallenge.interfacelayer.controllers.dtos.pedido.PedidoDto;
import br.com.fiap.techchallenge.interfacelayer.gateways.ClienteGateway;
import br.com.fiap.techchallenge.interfacelayer.gateways.PedidoGateway;
import br.com.fiap.techchallenge.interfacelayer.gateways.ProdutoGateway;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * Classe de testes para o controller de Pedido.
 */
class PedidoControllerTest {

  AutoCloseable closeable;
  PedidoController controller;
  
  @Mock
  PedidoGateway pedidoGatewayMock;

  @Mock
  ClienteGateway clienteGatewayMock;

  @Mock
  ProdutoGateway produtoGatewayMock;

  @Mock
  Cliente clienteMock;

  @Mock
  ItemPedido itemPedido;

  @Mock
  Produto produtoMock;

  @BeforeEach
  void setup() {
    closeable = MockitoAnnotations.openMocks(this);
    controller = new PedidoController(pedidoGatewayMock, clienteGatewayMock, produtoGatewayMock);
  }

  @AfterEach
  void tearDown() throws Exception {
    closeable.close();
  }

  @Test
  void deveFazerCheckoutComSucesso() throws BusinessRuleException {

    // Arrange
    // Mock do use case buscar cliente
    var buscarClienteMock = Mockito.mockStatic(BuscarClientePeloCpf.class);
    
    buscarClienteMock.when(() -> BuscarClientePeloCpf
        .buscar(Mockito.any(), Mockito.any())).thenReturn(clienteMock);

    // Mock do use case buscar produto
    var buscarProdutoMock = Mockito.mockStatic(BuscarProduto.class);

    buscarProdutoMock.when(() -> BuscarProduto
        .buscar(Mockito.any(), Mockito.any())).thenReturn(produtoMock);

    // Mock do use case fazer checkout
    var useCaseCheckoutMock = Mockito.mockStatic(FazerCheckoutPedido.class);

    var respostaUseCaseCheckout = instanciarPedido(false);

    useCaseCheckoutMock.when(() -> FazerCheckoutPedido
        .fazerCheckout(Mockito.any(), Mockito.any())).thenReturn(respostaUseCaseCheckout);

    // Act and assert
    assertDoesNotThrow(() -> {

      var paramRequisicao = instanciarPedidoDto();

      var respostaController = controller.fazerCheckout(paramRequisicao);

      assertEquals(201, respostaController.getStatusCode().value());
    });

    buscarClienteMock.close();
    buscarProdutoMock.close();
    useCaseCheckoutMock.close();

  }

  @Test
  void deveAtualizarStatusPedidoComSucesso() throws BusinessRuleException {

    // Arrange
    var usecaseMock = Mockito.mockStatic(AtualizarStatusPedido.class);

    var respostaUseCase = instanciarPedido(true);
    
    usecaseMock.when(() -> AtualizarStatusPedido
        .atualizar(Mockito.any(), Mockito.anyLong())).thenReturn(respostaUseCase);

    // Act and assert
    assertDoesNotThrow(() -> {
      var respostaController = controller.atualizarStatusPedido(1L);

      assertEquals(200, respostaController.getStatusCode().value());
    });
    
  }

  @Test
  void deveConsultarStatusPagamentoComSucesso() {}

  @Test
  void deveListarPedidosComSucesso() {}

  @Test
  void deveExecutarWebhookMercadoPagoComSucesso() {}

  // Métodos auxiliares dos testes
  private PedidoDto instanciarPedidoDto() {

    var listaItens = new ArrayList<ItemPedidoDto>();
    listaItens.add(new ItemPedidoDto(1L, 1));

    return new PedidoDto(11122233396L, listaItens);
  }

  private Pedido instanciarPedido(boolean pago)
      throws BusinessRuleException {

    var listaItens = new ArrayList<ItemPedido>();
    listaItens.add(itemPedido);

    var enumPag = pago ? StatusPagamentoEnum.APROVADO : StatusPagamentoEnum.AGUARDANDO_PAGAMENTO;
    var statusPagamento = new StatusPagamento(1L, enumPag, LocalDateTime.now());

    var enumPed = pago ? StatusPedidoEnum.EM_PREPARACAO : StatusPedidoEnum.RECEBIDO;
    var statusPedido = new StatusPedido(enumPed, LocalDateTime.now());

    return new Pedido(1L, clienteMock, listaItens,
        LocalDateTime.now(), statusPagamento, statusPedido);
  }

}

