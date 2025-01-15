package br.com.fiap.techchallenge.applicationlayer.usecases.produto;

import br.com.fiap.techchallenge.applicationlayer.exceptions.messages.EnumApplicationExceptions;
import br.com.fiap.techchallenge.applicationlayer.exceptions.messages.EnumNotFoundExceptions;
import br.com.fiap.techchallenge.applicationlayer.interfaces.gateway.InProdutoGateway;
import br.com.fiap.techchallenge.applicationlayer.services.Validar;
import br.com.fiap.techchallenge.businesslayer.entities.produto.Produto;

public final class BuscarProduto {

  // Métodos públicos
  public static Produto buscar(InProdutoGateway gateway, Long codigoProduto) throws Exception {
    Validar.notNull(codigoProduto, EnumApplicationExceptions.PRODUTO_CODIGO_NULO);
    Validar.maiorQueZero(codigoProduto, EnumApplicationExceptions.PRODUTO_CODIGO_MIN);

    Produto produto = gateway.buscarProduto(codigoProduto);
    Validar.notNull(produto, EnumNotFoundExceptions.PRODUTO_NAO_ENCONTRADO);

    return produto;
  }

}
