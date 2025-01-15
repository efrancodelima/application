package br.com.fiap.techchallenge.interfacelayer.gateways.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.techchallenge.interfacelayer.gateways.entities.ClienteJpa;

public interface IClienteRepository extends JpaRepository<ClienteJpa, Long> {

    ClienteJpa findByCpf(long cpf);

    boolean existsByCpf(long cpf);

}
