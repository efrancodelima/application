package br.com.fiap.techchallenge.interfacelayer.controllers.adapters.request_adapters;

import br.com.fiap.techchallenge.businesslayer.entities.cliente.Cliente;
import br.com.fiap.techchallenge.businesslayer.entities.cliente.Cpf;
import br.com.fiap.techchallenge.interfacelayer.controllers.dtos.ClienteDto;

public final class ClienteRequestAdapter {

    public static Cliente adaptar(ClienteDto clienteDto) throws Exception {
        Cpf cpf = new Cpf(clienteDto.getCpf());
        String nome = clienteDto.getNome();
        String email = clienteDto.getEmail();
        return new Cliente(null, cpf, nome, email);
    }

}
