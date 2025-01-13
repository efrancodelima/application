package br.com.fiap.techchallenge.interfacelayer.gateways.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.techchallenge.businesslayer.entities.produto.CategoriaProduto;
import br.com.fiap.techchallenge.interfacelayer.gateways.entities.ProdutoJpa;

@Repository
public interface IProdutoRepository extends JpaRepository<ProdutoJpa, Long> {

    List<ProdutoJpa> findByCategoria(CategoriaProduto categoria);

}
