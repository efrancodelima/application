package br.com.fiap.techchallenge.interfacelayer.gateways.mappers;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import br.com.fiap.techchallenge.businesslayer.entities.pedido.ItemPedido;
import br.com.fiap.techchallenge.businesslayer.entities.produto.CategoriaProduto;
import br.com.fiap.techchallenge.businesslayer.entities.produto.Produto;
import br.com.fiap.techchallenge.businesslayer.exceptions.BusinessRuleException;
import br.com.fiap.techchallenge.interfacelayer.gateways.entities.ItemPedidoJpa;
import br.com.fiap.techchallenge.interfacelayer.gateways.entities.ProdutoJpa;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

/**
 * Classe de testes para ItemPedidoMapper.
 */
class ItemPedidoMapperTest {
  
  @Test
  void deveMapearListItemPedidoParaListItemPedidoJpa() throws BusinessRuleException {
    
    // Arrange
    var produto = new Produto(1L,
        "Nome do produto", null, BigDecimal.valueOf(5), CategoriaProduto.BEBIDA);
    
    var itemPedido = new ItemPedido(produto, 2);

    // Act and assert
    assertDoesNotThrow(() -> {
      var itemPedidoJpa = ItemPedidoMapper.getItemPedidoJpa(itemPedido);

      assertEquals(itemPedido.getProduto().getNome(), itemPedidoJpa.getProdutoJpa().getNome());
      assertEquals(itemPedido.getQuantidade(), (int) itemPedidoJpa.getQuantidade());
      assertEquals(itemPedido.getValorItem(),
          itemPedidoJpa.getProdutoJpa().getPreco()
          .multiply(BigDecimal.valueOf(itemPedidoJpa.getQuantidade())));
    });
  }

  @Test
  void deveMapearListItemPedidoJpaParaListItemPedido() {

    // Arrange
    var produtoJpa = new ProdutoJpa(1L, "Nome do produto",
        null, BigDecimal.valueOf(22), CategoriaProduto.BEBIDA);

    var itemPedidoJpa = new ItemPedidoJpa(produtoJpa, 2);

    // Act and assert
    assertDoesNotThrow(() -> {
      var itemPedido = ItemPedidoMapper.getItemPedido(itemPedidoJpa);
  
      assertEquals(itemPedido.getProduto().getNome(), itemPedidoJpa.getProdutoJpa().getNome());
      assertEquals(itemPedido.getQuantidade(), (int) itemPedidoJpa.getQuantidade());
      assertEquals(itemPedido.getValorItem(),
          itemPedidoJpa.getProdutoJpa().getPreco()
          .multiply(BigDecimal.valueOf(itemPedidoJpa.getQuantidade())));
    });
    
  }
}