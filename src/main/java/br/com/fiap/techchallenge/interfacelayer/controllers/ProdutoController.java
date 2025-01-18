package br.com.fiap.techchallenge.interfacelayer.controllers;

import br.com.fiap.techchallenge.applicationlayer.usecases.produto.BuscarProdutosPorCategoria;
import br.com.fiap.techchallenge.applicationlayer.usecases.produto.CadastrarProduto;
import br.com.fiap.techchallenge.applicationlayer.usecases.produto.EditarProduto;
import br.com.fiap.techchallenge.applicationlayer.usecases.produto.RemoverProduto;
import br.com.fiap.techchallenge.businesslayer.entities.produto.CategoriaProduto;
import br.com.fiap.techchallenge.businesslayer.entities.produto.Produto;
import br.com.fiap.techchallenge.interfacelayer.controllers.adapters.request.ProdutoRequestAdapter;
import br.com.fiap.techchallenge.interfacelayer.controllers.adapters.response.ProdutoResponseAdapter;
import br.com.fiap.techchallenge.interfacelayer.controllers.dtos.ProdutoDto;
import br.com.fiap.techchallenge.interfacelayer.controllers.interfaces.IProdutoController;
import br.com.fiap.techchallenge.interfacelayer.gateways.ProdutoGateway;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ProdutoController implements IProdutoController {

  // Atributos
  private final ProdutoGateway gateway;

  // Construtor
  @Autowired
  public ProdutoController(ProdutoGateway gateway) {
    this.gateway = gateway;
  }

  // Métodos públicos
  @Override
  public ResponseEntity<Produto> cadastrarProduto(ProdutoDto produtoDto) throws Exception {

    Produto produto = ProdutoRequestAdapter.adaptar(produtoDto);
    produto = CadastrarProduto.cadastrar(gateway, produto);
    return ProdutoResponseAdapter.adaptar(produto, HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<Produto> editarProduto(Long codigo, ProdutoDto produtoDto) throws Exception {

    Produto produto = ProdutoRequestAdapter.adaptar(codigo, produtoDto);
    EditarProduto.editar(gateway, produto);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @Override
  public ResponseEntity<Produto> removerProduto(Long codigo) throws Exception {

    RemoverProduto.remover(gateway, codigo);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @Override
  public ResponseEntity<List<Produto>> buscarProdutosPorCategoria(String categoriaStr) throws Exception {

    CategoriaProduto categoria = CategoriaProduto.fromString(categoriaStr);
    List<Produto> produtos = BuscarProdutosPorCategoria.buscar(gateway, categoria);

    if (produtos.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } else {
      return ProdutoResponseAdapter.adaptar(produtos, HttpStatus.OK);
    }
  }

}
