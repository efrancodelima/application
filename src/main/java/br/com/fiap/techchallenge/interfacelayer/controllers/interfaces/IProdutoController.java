package br.com.fiap.techchallenge.interfacelayer.controllers.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import br.com.fiap.techchallenge.businesslayer.entities.produto.Produto;
import br.com.fiap.techchallenge.interfacelayer.controllers.dtos.ProdutoDto;

public interface IProdutoController {

    ResponseEntity<Produto> cadastrarProduto(ProdutoDto produtoDto) throws Exception;

    ResponseEntity<Produto> editarProduto(Long codigo, ProdutoDto produtoDto) throws Exception;

    ResponseEntity<Produto> removerProduto(Long codigo) throws Exception;

    ResponseEntity<List<Produto>> buscarProdutosPorCategoria(String categoriaStr) throws Exception;

}
