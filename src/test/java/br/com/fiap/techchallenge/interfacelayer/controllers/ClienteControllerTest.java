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
    
    var cpf = 11122233396L;
    var paramRequisicao = instanciarClienteDto(cpf);
    var respostaUseCase = instanciarCliente(cpf);

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

    var useCaseMock = Mockito.mockStatic(BuscarClientePeloCpf.class);

    var cpf = 11122233396L;
    var respostaUseCase = instanciarCliente(cpf);

    useCaseMock.when(() -> BuscarClientePeloCpf
        .buscar(Mockito.any(), Mockito.any())).thenReturn(respostaUseCase);

    assertDoesNotThrow(() -> {
      var respostaController = controller.buscarClientePeloCpf(cpf);
      assertEquals(respostaUseCase.getCpf(), respostaController.getBody().getCpf());
    });

    useCaseMock.close();

  }

  // Métodos auxiliares dos testes
  private Cliente instanciarCliente(Long cpf) throws BusinessRuleException {
    return new Cliente(1L,
        new Cpf(cpf), "Arthur Conan Doyle", "conanad@gmail.com");
  }

  private ClienteDto instanciarClienteDto(Long cpf) throws BusinessRuleException {
    return new ClienteDto(cpf, "Arthur Conan Doyle", "conanad@gmail.com");
  }

}
