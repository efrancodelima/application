package br.com.fiap.techchallenge.applicationlayer.usecases.cliente;

import br.com.fiap.techchallenge.applicationlayer.exceptions.ApplicationException;
import br.com.fiap.techchallenge.applicationlayer.exceptions.messages.EnumApplicationExceptions;
import br.com.fiap.techchallenge.applicationlayer.interfaces.gateway.InClienteGateway;
import br.com.fiap.techchallenge.applicationlayer.services.Validar;
import br.com.fiap.techchallenge.businesslayer.entities.cliente.Cliente;

public final class CadastrarCliente {

  public static Cliente cadastrar(InClienteGateway gateway, Cliente cliente) throws Exception {
    Validar.notNull(cliente, EnumApplicationExceptions.CLIENTE_NULO);

    boolean clienteJaExiste = gateway.clienteJaExiste(cliente.getCpf());
    if (clienteJaExiste) {
      throw new ApplicationException(EnumApplicationExceptions.CLIENTE_JA_EXISTE.getMensagem());
    }

    return gateway.gravarCliente(cliente);
  }

}
