package br.com.fiap.techchallenge.interfacelayer.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import br.com.fiap.techchallenge.applicationlayer.usecases.cliente.BuscarClientePeloCpf;
import br.com.fiap.techchallenge.applicationlayer.usecases.cliente.CadastrarCliente;
import br.com.fiap.techchallenge.businesslayer.entities.cliente.Cliente;
import br.com.fiap.techchallenge.businesslayer.entities.cliente.Cpf;
import br.com.fiap.techchallenge.businesslayer.exceptions.BusinessRuleException;
import br.com.fiap.techchallenge.interfacelayer.controllers.dtos.ClienteDto;
import br.com.fiap.techchallenge.interfacelayer.gateways.ClienteGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * Classe de testes para o controller de Cliente.
 */
class ClienteControllerTest {

  ClienteController controller;
    
  @Mock
  ClienteGateway clienteGatewayMock;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
    controller = new ClienteController(clienteGatewayMock);
  }

  @Test
  void deveCadastrarClienteComSucesso() throws Exception {

    MockedStatic<CadastrarCliente> useCaseMock = Mockito.mockStatic(CadastrarCliente.class);
    
    var paramRequisicao = new ClienteDto(11122233396L, 
        "Arthur Conan Doyle", "conanad@gmail.com");

    var respostaUseCase = new Cliente(1L,
        new Cpf(11122233396L), "Arthur Conan Doyle", "conanad@gmail.com");

    useCaseMock.when(() -> CadastrarCliente
        .cadastrar(Mockito.any(), Mockito.any())).thenReturn(respostaUseCase);

    assertDoesNotThrow(() -> {
      var respostaController = controller.cadastrarCliente(paramRequisicao);
      assertEquals(respostaUseCase.getCpf(), respostaController.getBody().getCpf());
    });

    useCaseMock.close();

  }

  @Test
  void deveBuscarClientePeloCpfComSucesso() throws BusinessRuleException {

    MockedStatic<BuscarClientePeloCpf> useCaseMock = Mockito.mockStatic(BuscarClientePeloCpf.class);

    var cpf = 11122233396L;

    var respostaUseCase = new Cliente(1L, new Cpf(cpf), "Arthur Conan Doyle", "conanad@gmail.com");

    useCaseMock.when(() -> BuscarClientePeloCpf
        .buscar(Mockito.any(), Mockito.any())).thenReturn(respostaUseCase);

    assertDoesNotThrow(() -> {
      var respostaController = controller.buscarClientePeloCpf(cpf);
      assertEquals(respostaUseCase.getCpf(), respostaController.getBody().getCpf());
    });

    useCaseMock.close();

  }

}
