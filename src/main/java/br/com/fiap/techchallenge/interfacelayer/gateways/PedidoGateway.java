package br.com.fiap.techchallenge.interfacelayer.gateways;

import br.com.fiap.techchallenge.applicationlayer.interfaces.gateway.InPedidoGateway;
import br.com.fiap.techchallenge.businesslayer.entities.pedido.Pedido;
import br.com.fiap.techchallenge.interfacelayer.gateways.entities.PedidoJpa;
import br.com.fiap.techchallenge.interfacelayer.gateways.mappers.PedidoMapper;
import br.com.fiap.techchallenge.interfacelayer.gateways.repositories.IPedidoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PedidoGateway implements InPedidoGateway {

  // Atributos
  private final IPedidoRepository pedidoJpaRepository;

  // Construtor
  @Autowired
  public PedidoGateway(IPedidoRepository pedidoJpaRepository) {
    this.pedidoJpaRepository = pedidoJpaRepository;
  }

  // Métodos públicos
  @Override
  public Pedido gravarPedido(Pedido pedido) throws Exception {

    PedidoJpa pedidoJpa = PedidoMapper.getPedidoJpa(pedido);
    pedidoJpa = pedidoJpaRepository.save(pedidoJpa);
    return PedidoMapper.getPedido(pedidoJpa);
  }

  @Override
  public void atualizarPedido(Pedido pedido) throws Exception {

    PedidoJpa pedidoJpa = PedidoMapper.getPedidoJpa(pedido);
    pedidoJpaRepository.save(pedidoJpa);
  }

  @Override
  public Pedido buscarPedido(long numeroPedido) throws Exception {

    Optional<PedidoJpa> optionalPedido = pedidoJpaRepository.findById(numeroPedido);
    return optionalPedido.isPresent() ? PedidoMapper.getPedido(optionalPedido.get()) : null;
  }

  @Override
  public List<Pedido> buscarTodosOsPedidos() throws Exception, Exception {
    List<PedidoJpa> pedidosJpa = pedidoJpaRepository.findAll();
    return PedidoMapper.getListPedido(pedidosJpa);
  }

  @Override
  public Pedido buscarPedidoPeloCodigoPagamento(long codigoPagamento) throws Exception {
    PedidoJpa pedidoJpa = pedidoJpaRepository.findByStatusPagamentoCodigo(codigoPagamento);
    return pedidoJpa == null ? null : PedidoMapper.getPedido(pedidoJpa);
  }

}
