package br.com.fiap.techchallenge.interfacelayer.controllers.interfaces;

import org.springframework.http.ResponseEntity;

import br.com.fiap.techchallenge.businesslayer.entities.cliente.Cliente;
import br.com.fiap.techchallenge.interfacelayer.controllers.dtos.ClienteDto;

public interface IClienteController {

    ResponseEntity<Cliente> cadastrarCliente(ClienteDto clienteDto) throws Exception;

    ResponseEntity<Cliente> buscarClientePorCpf(Long cpfLong) throws Exception;
}
