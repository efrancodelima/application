package br.com.fiap.techchallenge.externallayer.apis;

import br.com.fiap.techchallenge.businesslayer.entities.cliente.Cliente;
import br.com.fiap.techchallenge.externallayer.apis.interfaces.IClienteApi;
import br.com.fiap.techchallenge.interfacelayer.controllers.ClienteController;
import br.com.fiap.techchallenge.interfacelayer.controllers.dtos.ClienteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST para a APi clientes.
 */
@RestController
@RequestMapping("/clientes")
public class ClienteApi implements IClienteApi {

  private final ClienteController controller;

  /**
   * Injeta o controller via construtor.
   */
  @Autowired
  public ClienteApi(ClienteController controller) {
    this.controller = controller;
  }

  @Override
  public ResponseEntity<Cliente> cadastrarCliente(ClienteDto clienteDto) throws Exception {
    return controller.cadastrarCliente(clienteDto);
  }

  @Override
  public ResponseEntity<Cliente> buscarClientePorCpf(long cpf) throws Exception {
    return controller.buscarClientePorCpf(cpf);
  }

}
