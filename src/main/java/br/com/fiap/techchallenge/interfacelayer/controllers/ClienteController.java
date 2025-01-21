package br.com.fiap.techchallenge.interfacelayer.controllers;

import br.com.fiap.techchallenge.applicationlayer.usecases.cliente.BuscarClientePeloCpf;
import br.com.fiap.techchallenge.applicationlayer.usecases.cliente.CadastrarCliente;
import br.com.fiap.techchallenge.businesslayer.entities.cliente.Cliente;
import br.com.fiap.techchallenge.businesslayer.entities.cliente.Cpf;
import br.com.fiap.techchallenge.interfacelayer.controllers.adapters.request.ClienteRequestAdapter;
import br.com.fiap.techchallenge.interfacelayer.controllers.adapters.response.ClienteResponseAdapter;
import br.com.fiap.techchallenge.interfacelayer.controllers.dtos.ClienteDto;
import br.com.fiap.techchallenge.interfacelayer.controllers.interfaces.InClienteController;
import br.com.fiap.techchallenge.interfacelayer.gateways.ClienteGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * Classe ClienteController.
 */
@Component
public class ClienteController implements InClienteController {

  // Atributos
  private final ClienteGateway gateway;

  /**
   * Construtor público de ClienteController.
   *
   * @param gateway O gateway do repositório de clientes.
   */
  @Autowired
  public ClienteController(ClienteGateway gateway) {
    this.gateway = gateway;
  }

  @Override
  public ResponseEntity<Cliente> cadastrarCliente(ClienteDto clienteDto) throws Exception {

    Cliente cliente = ClienteRequestAdapter.adaptar(clienteDto);
    cliente = CadastrarCliente.cadastrar(gateway, cliente);
    return ClienteResponseAdapter.adaptar(cliente, HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<Cliente> buscarClientePeloCpf(Long cpfLong) throws Exception {

    Cpf cpf = new Cpf(cpfLong);
    Cliente cliente = BuscarClientePeloCpf.buscar(gateway, cpf);
    return ClienteResponseAdapter.adaptar(cliente, HttpStatus.OK);
  }
}