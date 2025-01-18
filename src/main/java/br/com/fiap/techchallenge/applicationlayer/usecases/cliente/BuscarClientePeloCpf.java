package br.com.fiap.techchallenge.applicationlayer.usecases.cliente;

import br.com.fiap.techchallenge.applicationlayer.exceptions.messages.EnumApplicationExceptions;
import br.com.fiap.techchallenge.applicationlayer.exceptions.messages.EnumNotFoundExceptions;
import br.com.fiap.techchallenge.applicationlayer.interfaces.gateway.InClienteGateway;
import br.com.fiap.techchallenge.applicationlayer.services.Validar;
import br.com.fiap.techchallenge.businesslayer.entities.cliente.Cliente;
import br.com.fiap.techchallenge.businesslayer.entities.cliente.Cpf;

public final class BuscarClientePeloCpf {

  private BuscarClientePeloCpf() {}

  public static Cliente buscar(InClienteGateway gateway, Cpf cpf) throws Exception {
    Validar.notNull(cpf, EnumApplicationExceptions.CPF_NULO);

    Cliente cliente = gateway.buscarClientePorCpf(cpf);
    Validar.notNull(cliente, EnumNotFoundExceptions.CLIENTE_NAO_ENCONTRADO);

    return cliente;
  }

}
