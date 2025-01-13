package br.com.fiap.techchallenge.businesslayer.entities.pedido;

import br.com.fiap.techchallenge.businesslayer.entities.produto.Produto;
import br.com.fiap.techchallenge.businesslayer.exceptions.BusinessRuleException;
import br.com.fiap.techchallenge.businesslayer.exceptions.messages.ItemPedidoExceptions;
import java.math.BigDecimal;

public class ItemPedido {

  private final Produto produto;
  private final int quantidade;

  // Construtor
  public ItemPedido(Produto produto, Integer quantidade) throws BusinessRuleException {
    validarItemPedido(produto, quantidade);
    this.produto = produto;
    this.quantidade = quantidade;
  }

  // Getters
  public Produto getProduto() {
    return produto;
  }

  public int getQuantidade() {
    return quantidade;
  }

  public BigDecimal getValorItem() {
    return produto.getPreco().multiply(BigDecimal.valueOf(quantidade));
  }

  // Métodos de validação
  private void validarItemPedido(Produto produto, Integer quantidade) throws BusinessRuleException {
    validarProduto(produto);
    validarQuantidade(quantidade);
  }

  private void validarProduto(Produto produto) throws BusinessRuleException {
    if (produto == null) {
      throw new BusinessRuleException(ItemPedidoExceptions.PRODUTO_NULO.getMensagem());
    }
  }

  private void validarQuantidade(Integer quantidade) throws BusinessRuleException {
    if (quantidade == null) {
      throw new BusinessRuleException(ItemPedidoExceptions.QTDE_NULA.getMensagem());
    }
    if (quantidade < 0) {
      throw new BusinessRuleException(ItemPedidoExceptions.QTDE_MIN.getMensagem());
    }
    if (quantidade > 50) {
      throw new BusinessRuleException(ItemPedidoExceptions.QTDE_MAX.getMensagem());
    }
  }
}
