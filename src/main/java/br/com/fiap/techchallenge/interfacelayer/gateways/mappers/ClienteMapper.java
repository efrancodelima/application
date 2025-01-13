package br.com.fiap.techchallenge.interfacelayer.gateways.mappers;

import br.com.fiap.techchallenge.businesslayer.entities.cliente.Cliente;
import br.com.fiap.techchallenge.businesslayer.entities.cliente.Cpf;
import br.com.fiap.techchallenge.interfacelayer.gateways.entities.ClienteJpa;

public final class ClienteMapper {

    public static ClienteJpa getClienteJpa(Cliente cliente) {
        return new ClienteJpa(cliente.getCodigo(), cliente.getCpf().pegarNumeroComDigito(), cliente.getNome(),
                cliente.getEmail());
    }

    public static Cliente getCliente(ClienteJpa clienteJpa) throws Exception {
        Cpf cpf = new Cpf(clienteJpa.getCpf());
        return new Cliente(clienteJpa.getCodigo(), cpf, clienteJpa.getNome(), clienteJpa.getEmail());
    }

}
