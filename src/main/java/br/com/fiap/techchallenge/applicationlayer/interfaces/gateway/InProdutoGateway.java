package br.com.fiap.techchallenge.applicationlayer.interfaces.gateway;

import br.com.fiap.techchallenge.businesslayer.entities.produto.CategoriaProduto;
import br.com.fiap.techchallenge.businesslayer.entities.produto.Produto;
import java.util.List;

/**
 * Interface para o gateway do produto.
 */
public interface InProdutoGateway {

  Produto gravarProduto(Produto produto) throws Exception;

  void atualizarProduto(Produto produto) throws Exception;

  void removerProduto(long codigoProduto) throws Exception;

  Produto buscarProduto(long codigoProduto) throws Exception;

  List<Produto> buscarProdutosPorCategoria(CategoriaProduto categoria) throws Exception;

  boolean produtoExiste(long codigoProduto) throws Exception;

}
