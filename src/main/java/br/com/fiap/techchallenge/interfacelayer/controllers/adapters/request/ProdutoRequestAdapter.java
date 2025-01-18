package br.com.fiap.techchallenge.interfacelayer.controllers.adapters.request;

import java.math.BigDecimal;

import br.com.fiap.techchallenge.businesslayer.entities.produto.CategoriaProduto;
import br.com.fiap.techchallenge.businesslayer.entities.produto.Produto;
import br.com.fiap.techchallenge.interfacelayer.controllers.dtos.ProdutoDto;

public final class ProdutoRequestAdapter {

    private ProdutoRequestAdapter() {}

    public static Produto adaptar(ProdutoDto produtoDto) throws Exception {

        String nome = produtoDto.getNome().trim();
        String descricao = produtoDto.getDescricao();
        BigDecimal preco = produtoDto.getPreco();
        CategoriaProduto categoria = CategoriaProduto.fromString(produtoDto.getCategoria());
        return new Produto(null, nome, descricao, preco, categoria);
    }

    public static Produto adaptar(long codigo, ProdutoDto produtoDto) throws Exception {

        String nome = produtoDto.getNome().trim();
        String descricao = produtoDto.getDescricao();
        BigDecimal preco = produtoDto.getPreco();
        CategoriaProduto categoria = CategoriaProduto.fromString(produtoDto.getCategoria());
        return new Produto(codigo, nome, descricao, preco, categoria);
    }

}
