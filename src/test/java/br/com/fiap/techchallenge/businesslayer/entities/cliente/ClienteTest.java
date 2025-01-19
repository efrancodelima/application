package br.com.fiap.techchallenge.businesslayer.entities.cliente;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import br.com.fiap.techchallenge.businesslayer.exceptions.BusinessRuleException;
import br.com.fiap.techchallenge.businesslayer.exceptions.messages.ClienteExceptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Classe de teste da entidade de negócio Cliente.
 */
class ClienteTest {
  
  private Cpf cpfValido;
  private final Long codigoValido = 1L;
  private final String nomeValido = "Nome do cliente";
  private final String emailValido = "email@email.com";

  @BeforeEach
  void setup() throws BusinessRuleException{
    cpfValido = new Cpf(111222333, (byte) 96);
  }

  @Test
  void codigoClienteNaoDeveSerMenorQueUm() {
    var exception = assertThrows(BusinessRuleException.class, () -> {
      new Cliente(0L, cpfValido, nomeValido, emailValido);
    });
    assertEquals(ClienteExceptions.CODIGO_MIN.getMensagem(), exception.getMessage());

    exception = assertThrows(BusinessRuleException.class, () -> {
      new Cliente(-1L, cpfValido, nomeValido, emailValido);
    });
    assertEquals(ClienteExceptions.CODIGO_MIN.getMensagem(), exception.getMessage());
  }

  @Test
  void codigoClienteDeveAceitarNulo() {
    assertDoesNotThrow(() -> {
      new Cliente(null, cpfValido, nomeValido, emailValido);
    });
  }

  @Test
  void cpfClienteNaoPodeSerNulo() {
    var exception = assertThrows(BusinessRuleException.class, () -> {
      new Cliente(codigoValido, null, nomeValido, emailValido);
    });
    assertEquals(ClienteExceptions.CPF_NULO.getMensagem(), exception.getMessage());
  }

  @Test
  void nomeClienteDeveAceitarNulo() {
    assertDoesNotThrow(() -> {
      new Cliente(codigoValido, cpfValido, null, emailValido);
    });
  }

  @Test
  void nomeClienteNaoPodeSerStringVazia() {
    var exception = assertThrows(BusinessRuleException.class, () -> {
      new Cliente(codigoValido, cpfValido, "", emailValido);
    });
    assertEquals(ClienteExceptions.NOME_INVALIDO.getMensagem(), exception.getMessage());
  }

  @Test
  void nomeClienteNaoDeveTerMaisDe50Caracteres() {
    var exception = assertThrows(BusinessRuleException.class, () -> {
      new Cliente(codigoValido, cpfValido, "A".repeat(51), emailValido);
    });
    assertEquals(ClienteExceptions.NOME_MAX_CHAR.getMensagem(), exception.getMessage());
  }

  @Test
  void nomeClientePrecisaConterPeloMenosUmaPalavraComPeloMenos3Letras() {
    var exception = assertThrows(BusinessRuleException.class, () -> {
      new Cliente(codigoValido, cpfValido, "A B C D E", emailValido);
    });
    assertEquals(ClienteExceptions.NOME_INVALIDO.getMensagem(), exception.getMessage());
  }

}
