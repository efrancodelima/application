package br.com.fiap.techchallenge.interfacelayer.controllers.interfaces;

import br.com.fiap.techchallenge.businesslayer.entities.cliente.Cliente;
import br.com.fiap.techchallenge.interfacelayer.controllers.dtos.ClienteDto;
import org.springframework.http.ResponseEntity;

/**
 * Interface InClienteController.
 */
public interface InClienteController {

  /**
   * Cadasstra o cliente.
   *
   * @param clienteDto O cliente a ser cadastrado.
   * @return O cliente cadastrado.
   * @throws Exception Exceção lançada pelo método.
   */
  ResponseEntity<Cliente> cadastrarCliente(ClienteDto clienteDto) throws Exception;

  /**
   * Busca o cliente pelo CPF.
   *
   * @param cpfLong O CPF do cliente a ser buscado.
   * @return O cliente encontrado.
   * @throws Exception Exceção lançada pelo método.
   */
  ResponseEntity<Cliente> buscarClientePeloCpf(Long cpfLong) throws Exception;
}
