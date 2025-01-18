package br.com.fiap.techchallenge.interfacelayer.controllers.adapters.request;

import br.com.fiap.techchallenge.businesslayer.entities.pedido.ItemPedido;
import br.com.fiap.techchallenge.businesslayer.entities.produto.Produto;
import br.com.fiap.techchallenge.interfacelayer.controllers.dtos.pedido.ItemPedidoDto;
import java.util.ArrayList;
import java.util.List;

public final class ItemPedidoRequestAdapter {

    private ItemPedidoRequestAdapter() {}

    public static List<ItemPedido> adaptar(List<ItemPedidoDto> itens, List<Produto> produtos)
            throws Exception {
        List<ItemPedido> result = new ArrayList<>();

        for (int i = 0; i < itens.size(); i++) {
            ItemPedido item = new ItemPedido(produtos.get(i), itens.get(i).getQuantidade());
            result.add(item);
        }
        return result;
    }
}
