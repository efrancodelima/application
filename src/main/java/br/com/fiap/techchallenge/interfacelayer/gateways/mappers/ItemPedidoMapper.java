package br.com.fiap.techchallenge.interfacelayer.gateways.mappers;

import java.util.ArrayList;
import java.util.List;

import br.com.fiap.techchallenge.businesslayer.entities.pedido.ItemPedido;
import br.com.fiap.techchallenge.businesslayer.entities.produto.Produto;
import br.com.fiap.techchallenge.interfacelayer.gateways.entities.ItemPedidoJpa;
import br.com.fiap.techchallenge.interfacelayer.gateways.entities.ProdutoJpa;

public final class ItemPedidoMapper {

    private ItemPedidoMapper() {}

    // Métodos públicos
    public static ItemPedidoJpa getItemPedidoJpa(ItemPedido item) {
        ProdutoJpa produtoJpa = ProdutoMapper.getProdutoJpa(item.getProduto());
        return new ItemPedidoJpa(produtoJpa, item.getQuantidade());
    }

    public static ItemPedido getItemPedido(ItemPedidoJpa itemJpa) throws Exception {
        Produto produto = ProdutoMapper.getProduto(itemJpa.getProdutoJpa());
        return new ItemPedido(produto, itemJpa.getQuantidade());
    }

    public static List<ItemPedidoJpa> getListItemPedidoJpa(List<ItemPedido> itens) {
        List<ItemPedidoJpa> result = new ArrayList<ItemPedidoJpa>();
        for (ItemPedido item : itens) {
            ItemPedidoJpa itemJpa = getItemPedidoJpa(item);
            result.add(itemJpa);
        }
        return result;
    }

    public static List<ItemPedido> getListItemPedido(List<ItemPedidoJpa> itensJpa)
            throws Exception {
        List<ItemPedido> result = new ArrayList<ItemPedido>();
        for (ItemPedidoJpa itemJpa : itensJpa) {
            ItemPedido item = getItemPedido(itemJpa);
            result.add(item);
        }
        return result;
    }

}
