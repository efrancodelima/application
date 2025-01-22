package br.com.fiap.techchallenge.externallayer.apis;

import static org.junit.jupiter.api.Assertions.assertEquals;

import br.com.fiap.techchallenge.interfacelayer.gateways.repositories.InPedidoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

/**
 * Testes de integração a partir dos endpoints da API pedidos.
 */
@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
class PedidoApiTest {
    
  @Autowired
  InPedidoRepository repo;

  @Autowired
  PedidoApi api;

  @Test
  void devePermitirCriarTabela() {
    var numeroEntidades = repo.count();
    assertEquals(true, numeroEntidades < 1);
  }

}
