package br.com.fiap.techchallenge.applicationlayer.usecases.produto;

import br.com.fiap.techchallenge.applicationlayer.exceptions.messages.EnumApplicationExceptions;
import br.com.fiap.techchallenge.applicationlayer.interfaces.gateway.InProdutoGateway;
import br.com.fiap.techchallenge.applicationlayer.services.Validar;
import br.com.fiap.techchallenge.businesslayer.entities.produto.Produto;

public final class CadastrarProduto {

  public static Produto cadastrar(InProdutoGateway gateway, Produto produto) throws Exception {
    Validar.notNull(produto, EnumApplicationExceptions.PRODUTO_NULO);
    return gateway.gravarProduto(produto);
  }

}
