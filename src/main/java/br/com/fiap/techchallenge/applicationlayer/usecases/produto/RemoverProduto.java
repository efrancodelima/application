package br.com.fiap.techchallenge.applicationlayer.usecases.produto;

import br.com.fiap.techchallenge.applicationlayer.exceptions.ResourceNotFoundException;
import br.com.fiap.techchallenge.applicationlayer.exceptions.messages.EnumApplicationExceptions;
import br.com.fiap.techchallenge.applicationlayer.exceptions.messages.EnumNotFoundExceptions;
import br.com.fiap.techchallenge.applicationlayer.interfaces.gateway.InProdutoGateway;
import br.com.fiap.techchallenge.applicationlayer.services.Validar;

public final class RemoverProduto {

  // Métodos públicos
  public static void remover(InProdutoGateway gateway, Long codigoProduto) throws Exception {
    Validar.notNull(codigoProduto, EnumApplicationExceptions.PRODUTO_CODIGO_NULO);
    Validar.maiorQueZero(codigoProduto, EnumApplicationExceptions.PRODUTO_CODIGO_MIN);

    boolean produtoExiste = gateway.produtoExiste(codigoProduto);
    if (!produtoExiste) {
      throw new ResourceNotFoundException(EnumNotFoundExceptions.PRODUTO_NAO_ENCONTRADO.getMensagem());
    } else {
      gateway.removerProduto(codigoProduto);
    }

  }

}
