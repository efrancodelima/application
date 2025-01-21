package br.com.fiap.techchallenge.interfacelayer.gateways.mappers;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

import br.com.fiap.techchallenge.businesslayer.entities.cliente.Cliente;
import br.com.fiap.techchallenge.businesslayer.entities.cliente.Cpf;
import br.com.fiap.techchallenge.businesslayer.entities.pedido.ItemPedido;
import br.com.fiap.techchallenge.businesslayer.entities.pedido.Pedido;
import br.com.fiap.techchallenge.businesslayer.entities.pedido.StatusPagamento;
import br.com.fiap.techchallenge.businesslayer.entities.pedido.StatusPagamentoEnum;
import br.com.fiap.techchallenge.businesslayer.entities.pedido.StatusPedido;
import br.com.fiap.techchallenge.businesslayer.entities.pedido.StatusPedidoEnum;
import br.com.fiap.techchallenge.businesslayer.entities.produto.CategoriaProduto;
import br.com.fiap.techchallenge.businesslayer.entities.produto.Produto;
import br.com.fiap.techchallenge.businesslayer.exceptions.BusinessRuleException;

/**
 * Classe de testes para PedidoMapper.
 */
class PedidoMapperTest {

  
  // Métodos auxiliares dos testes
  private Pedido instanciarPedidoCompleto() throws BusinessRuleException {

    var cpf = new Cpf(11122233396L);
    
    var cliente = new Cliente(1L, cpf, "Nome do cliente", "email@email.com");

    var produto = new Produto(1L,
        "Nome do produto", null, BigDecimal.valueOf(5), CategoriaProduto.BEBIDA);
    
    var itemPedido = new ItemPedido(produto, 2);

    var listaItens = new ArrayList<ItemPedido>();
    listaItens.add(itemPedido);
    
    var statusPagamento = new StatusPagamento(1L,
        StatusPagamentoEnum.AGUARDANDO_PAGAMENTO, LocalDateTime.now());

    var statusPedido = new StatusPedido(StatusPedidoEnum.RECEBIDO, LocalDateTime.now());

    return new Pedido(1L, cliente, listaItens,
        LocalDateTime.now(), statusPagamento, statusPedido);
  }
}
