package br.com.fiap.techchallenge.applicationlayer.usecases.produto;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import br.com.fiap.techchallenge.applicationlayer.interfaces.gateway.InProdutoGateway;
import br.com.fiap.techchallenge.businesslayer.entities.produto.CategoriaProduto;
import br.com.fiap.techchallenge.businesslayer.entities.produto.Produto;
import br.com.fiap.techchallenge.businesslayer.exceptions.BusinessRuleException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * Classe de testes para o use case BuscarProdutosPorCategoria.
 */
class BuscarProdutosPorCategoriaTest {

  @Mock
  InProdutoGateway gatewayMock;

  @Mock
  Produto produtoMock;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void deveBuscarProdutosPelaCategoriaComSucesso() throws BusinessRuleException {

    List<Produto> lista = new ArrayList<>();
    lista.add(produtoMock);

    Mockito.doReturn(lista).when(gatewayMock).buscarProdutosPorCategoria(Mockito.any());
    
    assertDoesNotThrow(() -> {
      BuscarProdutosPorCategoria.buscar(gatewayMock, CategoriaProduto.LANCHE);
    });
  }

}
