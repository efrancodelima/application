package br.com.fiap.techchallenge.interfacelayer.adapters.requests;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import br.com.fiap.techchallenge.interfacelayer.controllers.adapters.request.PagamentoRequestAdapter;
import br.com.fiap.techchallenge.interfacelayer.controllers.dtos.mercado_pago.PagamentoDto;
import org.junit.jupiter.api.Test;

/**
 * Classe de testes para PagamentoRequestAdapter.
 */
class PagamentoRequestAdapterTest {

  @Test
  void deveAdaptarPagamentoDtoParaStatusPagamento() {

    var pagDto = new PagamentoDto(1L, "APROVADO");

    assertDoesNotThrow(() -> {

      var statusPagamento = PagamentoRequestAdapter.adaptar(pagDto);

      assertEquals(pagDto.getId(), statusPagamento.getCodigo());
      assertEquals(pagDto.getStatus(), statusPagamento.getStatus().toString());

    });

  }

}
