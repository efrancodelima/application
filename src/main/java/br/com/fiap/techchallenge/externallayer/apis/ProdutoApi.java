package br.com.fiap.techchallenge.externallayer.apis;

import br.com.fiap.techchallenge.businesslayer.entities.produto.Produto;
import br.com.fiap.techchallenge.externallayer.apis.interfaces.IntProdutoApi;
import br.com.fiap.techchallenge.interfacelayer.controllers.ProdutoController;
import br.com.fiap.techchallenge.interfacelayer.controllers.dtos.ProdutoDto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * A API de Produtos.
 */
@RestController
@RequestMapping("/produtos")
public class ProdutoApi implements IntProdutoApi {

  // Atributos
  private final ProdutoController controller;

  /**
   * O construtor da classe.
   *
   * @param controller O controller de Produtos.
   */
  @Autowired
  public ProdutoApi(ProdutoController controller) {
    this.controller = controller;
  }

  @Override
  public ResponseEntity<Produto> cadastrarProduto(ProdutoDto produtoDto) throws Exception {
    return controller.cadastrarProduto(produtoDto);
  }

  @Override
  public ResponseEntity<Produto> editarProduto(long codigo,
      ProdutoDto produtoDto) throws Exception {
    return controller.editarProduto(codigo, produtoDto);
  }

  @Override
  public ResponseEntity<Produto> removerProduto(long codigo) throws Exception {
    return controller.removerProduto(codigo);
  }

  @Override
  public ResponseEntity<List<Produto>> buscarProdutosPorCategoria(
      String categoria) throws Exception {
    return controller.buscarProdutosPorCategoria(categoria);
  }
}
