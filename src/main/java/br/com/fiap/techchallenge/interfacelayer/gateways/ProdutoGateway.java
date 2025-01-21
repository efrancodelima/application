package br.com.fiap.techchallenge.interfacelayer.gateways;

import br.com.fiap.techchallenge.applicationlayer.interfaces.gateway.InProdutoGateway;
import br.com.fiap.techchallenge.businesslayer.entities.produto.CategoriaProduto;
import br.com.fiap.techchallenge.businesslayer.entities.produto.Produto;
import br.com.fiap.techchallenge.businesslayer.exceptions.BusinessRuleException;
import br.com.fiap.techchallenge.interfacelayer.gateways.entities.ProdutoJpa;
import br.com.fiap.techchallenge.interfacelayer.gateways.mappers.ProdutoMapper;
import br.com.fiap.techchallenge.interfacelayer.gateways.repositories.IProdutoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProdutoGateway implements InProdutoGateway {

  // Atributos
  private final IProdutoRepository produtoJpaRepository;

  // Construtor
  @Autowired
  public ProdutoGateway(IProdutoRepository produtoJpaRepository) {
    this.produtoJpaRepository = produtoJpaRepository;
  }

  // Métodos públicos
  @Override
  public Produto gravarProduto(Produto produto) throws BusinessRuleException {
    
    ProdutoJpa produtoJpa = ProdutoMapper.getProdutoJpa(produto);
    produtoJpa = produtoJpaRepository.save(produtoJpa);
    return ProdutoMapper.getProduto(produtoJpa);
  }

  @Override
  public void atualizarProduto(Produto produto) throws BusinessRuleException {
    
    ProdutoJpa produtoJpa = ProdutoMapper.getProdutoJpa(produto);
    produtoJpaRepository.save(produtoJpa);
  }

  @Override
  public void removerProduto(long codigoProduto) throws BusinessRuleException {
    produtoJpaRepository.deleteById(codigoProduto);
  }

  @Override
  public Produto buscarProduto(long codigoProduto) throws BusinessRuleException {

    Optional<ProdutoJpa> produtoJpa = produtoJpaRepository.findById(codigoProduto);
    return produtoJpa.isPresent() ? ProdutoMapper.getProduto(produtoJpa.get()) : null;
  }

  @Override
  public List<Produto> buscarProdutosPorCategoria(CategoriaProduto categoria)
      throws BusinessRuleException {

    List<ProdutoJpa> produtosJpa = produtoJpaRepository.findByCategoria(categoria);
    return ProdutoMapper.getListProduto(produtosJpa);
  }

  @Override
  public boolean produtoExiste(long codigoProduto) {
    return produtoJpaRepository.existsById(codigoProduto);
  }

}
