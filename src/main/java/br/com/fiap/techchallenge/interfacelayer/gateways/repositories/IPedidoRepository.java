package br.com.fiap.techchallenge.interfacelayer.gateways.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.techchallenge.interfacelayer.gateways.entities.PedidoJpa;

public interface IPedidoRepository extends JpaRepository<PedidoJpa, Long> {

    PedidoJpa findByStatusPagamentoCodigo(Long codigoPagamento);

}
