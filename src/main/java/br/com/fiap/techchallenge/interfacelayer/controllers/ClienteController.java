package br.com.fiap.techchallenge.interfacelayer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import br.com.fiap.techchallenge.applicationlayer.usecases.cliente.BuscarClientePeloCpf;
import br.com.fiap.techchallenge.applicationlayer.usecases.cliente.CadastrarCliente;
import br.com.fiap.techchallenge.businesslayer.entities.cliente.Cliente;
import br.com.fiap.techchallenge.businesslayer.entities.cliente.Cpf;
import br.com.fiap.techchallenge.interfacelayer.controllers.adapters.request_adapters.ClienteRequestAdapter;
import br.com.fiap.techchallenge.interfacelayer.controllers.adapters.response_adapters.ClienteResponseAdapter;
import br.com.fiap.techchallenge.interfacelayer.controllers.dtos.ClienteDto;
import br.com.fiap.techchallenge.interfacelayer.controllers.interfaces.IClienteController;
import br.com.fiap.techchallenge.interfacelayer.gateways.ClienteGateway;

@Component
public class ClienteController implements IClienteController {

    @Autowired
    ClienteGateway gateway;

    @Override
    public ResponseEntity<Cliente> cadastrarCliente(ClienteDto clienteDto) throws Exception {

        Cliente cliente = ClienteRequestAdapter.adaptar(clienteDto);
        cliente = CadastrarCliente.cadastrar(gateway, cliente);
        return ClienteResponseAdapter.adaptar(cliente, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Cliente> buscarClientePorCpf(Long cpfLong) throws Exception {

        Cpf cpf = new Cpf(cpfLong);
        Cliente cliente = BuscarClientePeloCpf.buscar(gateway, cpf);
        return ClienteResponseAdapter.adaptar(cliente, HttpStatus.OK);
    }
}