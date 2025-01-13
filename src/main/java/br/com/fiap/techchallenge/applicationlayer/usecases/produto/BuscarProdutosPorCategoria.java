package br.com.fiap.techchallenge.applicationlayer.usecases.produto;

import br.com.fiap.techchallenge.applicationlayer.exceptions.messages.EnumApplicationExceptions;
import br.com.fiap.techchallenge.applicationlayer.exceptions.messages.EnumNotFoundExceptions;
import br.com.fiap.techchallenge.applicationlayer.interfaces.gateway.InProdutoGateway;
import br.com.fiap.techchallenge.applicationlayer.services.Validar;
import br.com.fiap.techchallenge.businesslayer.entities.produto.CategoriaProduto;
import br.com.fiap.techchallenge.businesslayer.entities.produto.Produto;
import java.util.List;

public final class BuscarProdutosPorCategoria {

  public static List<Produto> buscar(InProdutoGateway gateway, CategoriaProduto categoria)
      throws Exception {

    Validar.notNull(categoria, EnumApplicationExceptions.CATEGORIA_NULA);

    List<Produto> produtos = gateway.buscarProdutosPorCategoria(categoria);
    Validar.listNotEmpty(produtos, EnumNotFoundExceptions.PRODUTO_LISTA_VAZIA);

    return produtos;
  }

}
