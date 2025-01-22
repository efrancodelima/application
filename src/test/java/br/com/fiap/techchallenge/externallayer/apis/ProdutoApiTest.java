package br.com.fiap.techchallenge.externallayer.apis;

import static org.junit.jupiter.api.Assertions.assertEquals;

import br.com.fiap.techchallenge.interfacelayer.controllers.dtos.ProdutoDto;
import br.com.fiap.techchallenge.interfacelayer.gateways.repositories.InProdutoRepository;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
class ProdutoApiTest {
   
  @Autowired
  InProdutoRepository repo;

  @Autowired
  ProdutoApi api;

  @Test
  void devePermitirCriarTabela() {
    var numeroEntidades = repo.count();
    assertEquals(true, numeroEntidades < 1);
  }

  @Test
  void deveCadastrarProduto() throws Exception {
    
    ProdutoDto produtoDto = instanciarProdutoDto();
    var response = api.cadastrarProduto(produtoDto);

    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals(produtoDto.getNome(), response.getBody().getNome());
    assertEquals(produtoDto.getPreco(), response.getBody().getPreco());
  }

  @Test
  void deveEditarProduto() throws Exception {
    
    // Arrange
    ProdutoDto produtoDto = instanciarProdutoDto();
    var produtoCadastrado = api.cadastrarProduto(produtoDto);
    var codigoProduto = produtoCadastrado.getBody().getCodigo();
    produtoDto.setNome("X-Bacon");
    
    // Act
    var response = api.editarProduto(codigoProduto, produtoDto);

    // Assert
    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
  }

  @Test
  void deveRemoverProduto() throws Exception {
    
    // Arrange
    var produtoCadastrado = api.cadastrarProduto(instanciarProdutoDto());
    var codigoProduto = produtoCadastrado.getBody().getCodigo();
    
    // Act
    var response = api.removerProduto(codigoProduto);

    // Assert
    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
  }

  @Test
  void deveBuscarProdutosPorCategoria() throws Exception {
    
    // Arrange and act
    var categoria = "LANCHE";
    api.cadastrarProduto(instanciarProdutoDto(categoria));
    var response = api.buscarProdutosPorCategoria(categoria);

    // Assert
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(false, response.getBody().isEmpty());
  }

  // Métodos auxiliares dos testes
  private ProdutoDto instanciarProdutoDto() {
    return instanciarProdutoDto("BEBIDA");
  }

  private ProdutoDto instanciarProdutoDto(String categoria) {
    return new ProdutoDto("Nome do produto", null, BigDecimal.valueOf(5), categoria);
  }

}
