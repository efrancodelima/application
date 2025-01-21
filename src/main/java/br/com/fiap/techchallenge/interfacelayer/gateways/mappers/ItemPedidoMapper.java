package br.com.fiap.techchallenge.interfacelayer.gateways.mappers;

import br.com.fiap.techchallenge.businesslayer.entities.pedido.ItemPedido;
import br.com.fiap.techchallenge.businesslayer.entities.produto.Produto;
import br.com.fiap.techchallenge.businesslayer.exceptions.BusinessRuleException;
import br.com.fiap.techchallenge.interfacelayer.gateways.entities.ItemPedidoJpa;
import br.com.fiap.techchallenge.interfacelayer.gateways.entities.ProdutoJpa;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe ItemPedidoMapper.
 */
public final class ItemPedidoMapper {

  private ItemPedidoMapper() {}

  /**
   * Converte uma entidade de negócio para uma entidade JPA.
   *
   * @param item A entidade de negócio que será convertida.
   * @return A entidade JPA.
   */
  public static ItemPedidoJpa getItemPedidoJpa(ItemPedido item) {
    ProdutoJpa produtoJpa = ProdutoMapper.getProdutoJpa(item.getProduto());
    return new ItemPedidoJpa(produtoJpa, item.getQuantidade());
  }

  /**
   * Converte uma entidade JPA para uma entidade de negócio.
   *
   * @param itemJpa A entidade JPA que será convertida.
   * @return A entidade de negócio.
   * @throws BusinessRuleException Exceção de regra de negócio lançada pelo método.
   */
  public static ItemPedido getItemPedido(ItemPedidoJpa itemJpa)
      throws BusinessRuleException {
    
    Produto produto = ProdutoMapper.getProduto(itemJpa.getProdutoJpa());
    return new ItemPedido(produto, itemJpa.getQuantidade());
  }

  /**
   * Converte uma lista de entidades de negócio para uma lista de entidades JPA.
   *
   * @param itens A lista de entidades de negócio.
   * @return A lista de entidades JPA.
   */
  public static List<ItemPedidoJpa> getListItemPedidoJpa(List<ItemPedido> itens) {
    
    List<ItemPedidoJpa> result = new ArrayList<>();
    
    for (ItemPedido item : itens) {
      ItemPedidoJpa itemJpa = getItemPedidoJpa(item);
      result.add(itemJpa);
    }

    return result;
  }

  /**
   * Converte uma lista de entidades JPA para uma lista de entidades de negócio.
   *
   * @param itensJpa A lista de entidades JPA.
   * @return A lista de entidades de negócio.
   * @throws BusinessRuleException Exceção de regra de negócio lançada pelo método.
   */
  public static List<ItemPedido> getListItemPedido(List<ItemPedidoJpa> itensJpa)
      throws BusinessRuleException {
    
    List<ItemPedido> result = new ArrayList<>();
    
    for (ItemPedidoJpa itemJpa : itensJpa) {
      ItemPedido item = getItemPedido(itemJpa);
      result.add(item);
    }
    
    return result;
  }

}
