package br.com.fiap.techchallenge.applicationlayer.usecases.produto;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import br.com.fiap.techchallenge.applicationlayer.interfaces.gateway.InProdutoGateway;
import br.com.fiap.techchallenge.businesslayer.entities.produto.Produto;
import br.com.fiap.techchallenge.businesslayer.exceptions.BusinessRuleException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * Classe de testes para o use case CadastrarProduto.
 */
class CadastrarProdutoTest {

  @Mock
  InProdutoGateway gatewayMock;

  @Mock
  Produto produtoMock;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void deveCadastrarProdutoComSucesso() throws BusinessRuleException {

    Mockito.doReturn(produtoMock).when(gatewayMock).gravarProduto(Mockito.any());
    
    assertDoesNotThrow(() -> {
      CadastrarProduto.cadastrar(gatewayMock, produtoMock);
    });
  }

}
