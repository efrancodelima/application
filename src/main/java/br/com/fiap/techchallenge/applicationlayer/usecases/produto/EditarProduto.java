package br.com.fiap.techchallenge.applicationlayer.usecases.produto;

import br.com.fiap.techchallenge.applicationlayer.exceptions.ResourceNotFoundException;
import br.com.fiap.techchallenge.applicationlayer.exceptions.messages.EnumApplicationExceptions;
import br.com.fiap.techchallenge.applicationlayer.exceptions.messages.EnumNotFoundExceptions;
import br.com.fiap.techchallenge.applicationlayer.interfaces.gateway.InProdutoGateway;
import br.com.fiap.techchallenge.applicationlayer.services.Validar;
import br.com.fiap.techchallenge.businesslayer.entities.produto.Produto;

public final class EditarProduto {

  private EditarProduto() {}

  public static void editar(InProdutoGateway gateway, Produto produto) throws Exception {
    Validar.notNull(produto, EnumApplicationExceptions.PRODUTO_NULO);

    boolean produtoExiste = gateway.produtoExiste(produto.getCodigo());
    if (!produtoExiste) {
      throw new ResourceNotFoundException(EnumNotFoundExceptions.PRODUTO_NAO_ENCONTRADO.getMensagem());
    } else {
      gateway.atualizarProduto(produto);
    }
  }

}
