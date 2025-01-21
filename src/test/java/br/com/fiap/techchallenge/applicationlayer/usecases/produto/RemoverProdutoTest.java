package br.com.fiap.techchallenge.applicationlayer.usecases.produto;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import br.com.fiap.techchallenge.applicationlayer.exceptions.ResourceNotFoundException;
import br.com.fiap.techchallenge.applicationlayer.exceptions.messages.EnumNotFoundExceptions;
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
 * Classe de testes para o use case RemoverProduto.
 */
class RemoverProdutoTest {

  @Mock
  InProdutoGateway gatewayMock;

  @Mock
  Produto produtoMock;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void deveRemoverProdutoComSucesso() throws BusinessRuleException {

    Mockito.doReturn(true).when(gatewayMock).produtoExiste(Mockito.anyLong());
    Mockito.doNothing().when(gatewayMock).removerProduto(Mockito.anyLong());
    
    assertDoesNotThrow(() -> {
      RemoverProduto.remover(gatewayMock, 1L);
    });
  }

  @Test
  void deveLancarExcecaoQuandoProdutoNaoExistir() throws BusinessRuleException {

    Mockito.doReturn(false).when(gatewayMock).produtoExiste(Mockito.anyLong());
    
    var exception = assertThrows(ResourceNotFoundException.class, () -> {
      RemoverProduto.remover(gatewayMock, 1L);
    });

    assertEquals(
        EnumNotFoundExceptions.PRODUTO_NAO_ENCONTRADO.getMensagem(), 
        exception.getMessage());
  }

}
