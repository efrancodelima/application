package br.com.fiap.techchallenge.externallayer.apis;

import static org.junit.Assert.assertEquals;

import br.com.fiap.techchallenge.interfacelayer.controllers.dtos.ClienteDto;
import br.com.fiap.techchallenge.interfacelayer.gateways.repositories.InClienteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;

/**
 * Testes de integração a partir dos endpoints da API clientes.
 */
@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
class ClienteApiTest {
  
  @Autowired
  InClienteRepository repo;

  @Autowired
  ClienteApi api;

  @Test
  void devePermitirCriarTabela() {
    var numeroEntidades = repo.count();
    assertEquals(true, numeroEntidades < 1);
  }

  @Test
  void deveCadastrarClienteComSucesso() throws Exception {

    ClienteDto clienteDto = instanciarClienteDto();

    var response = api.cadastrarCliente(clienteDto);
    assertEquals(HttpStatus.CREATED, response.getStatusCode());
  }

  @Test
  void deveBuscarClientePorCpf() throws Exception {

    ClienteDto clienteDto = instanciarClienteDto();
    api.cadastrarCliente(clienteDto);
    
    var response = api.buscarClientePorCpf(clienteDto.getCpf());
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  // Métodos auxiliares dos testes
  private ClienteDto instanciarClienteDto() {
    return new ClienteDto(11122233396L, "Nome do cliente", "email@email.com");
  }
  
}
