package br.com.fiap.techchallenge.interfacelayer.controllers.interfaces;

import br.com.fiap.techchallenge.businesslayer.entities.produto.Produto;
import br.com.fiap.techchallenge.interfacelayer.controllers.dtos.ProdutoDto;
import java.util.List;
import org.springframework.http.ResponseEntity;

/**
 * Interface InProdutoController.
 */
public interface InProdutoController {

  /**
   * Cadastra o produto.
   *
   * @param produtoDto O produto a ser cadastrado.
   * @return O produto cadastrado.
   * @throws Exception Exceção lançada pelo método.
   */
  ResponseEntity<Produto> cadastrarProduto(ProdutoDto produtoDto) throws Exception;

  /**
   * Edita o produto.
   *
   * @param codigo O código do produto a ser editado.
   * @param produtoDto O produto editado.
   * @return O produto editado.
   * @throws Exception Exceção lançada pelo método.
   */
  ResponseEntity<Produto> editarProduto(Long codigo, ProdutoDto produtoDto) throws Exception;

  /**
   * Remove o produto.
   *
   * @param codigo O código do produto a ser removido.
   * @return O produto removido.
   * @throws Exception Exceção lançada pelo método.
   */
  ResponseEntity<Produto> removerProduto(Long codigo) throws Exception;

  /**
   * Busca os produtos por categoria.
   *
   * @param categoriaStr A categoria dos produtos a serem buscados.
   * @return A lista com os produtos encontrados.
   * @throws Exception Exceção lançada pelo método.
   */
  ResponseEntity<List<Produto>> buscarProdutosPorCategoria(String categoriaStr) throws Exception;

}
