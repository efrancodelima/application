package br.com.fiap.techchallenge.interfacelayer.gateways.mappers;

import java.util.ArrayList;
import java.util.List;

import br.com.fiap.techchallenge.businesslayer.entities.produto.Produto;
import br.com.fiap.techchallenge.interfacelayer.gateways.entities.ProdutoJpa;

public final class ProdutoMapper {

    // Métodos públicos
    public static ProdutoJpa getProdutoJpa(Produto produto) {
        return new ProdutoJpa(produto.getCodigo(), produto.getNome(), produto.getDescricao(), produto.getPreco(),
                produto.getCategoria());
    }

    public static Produto getProduto(ProdutoJpa produtoJpa) throws Exception {
        return new Produto(produtoJpa.getCodigo(), produtoJpa.getNome(), produtoJpa.getDescricao(),
                produtoJpa.getPreco(),
                produtoJpa.getCategoria());
    }

    public static List<Produto> getListProduto(List<ProdutoJpa> produtosJpa)
            throws Exception {
        List<Produto> produtos = new ArrayList<>();
        for (ProdutoJpa produtoJpa : produtosJpa) {
            Produto produto = getProduto(produtoJpa);
            produtos.add(produto);
        }
        return produtos;
    }

}
