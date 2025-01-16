package br.com.fiap.techchallenge;

import br.com.fiap.techchallenge.externallayer.apis.ClienteApi;
import br.com.fiap.techchallenge.externallayer.apis.PedidoApi;
import br.com.fiap.techchallenge.externallayer.apis.ProdutoApi;
import br.com.fiap.techchallenge.interfacelayer.controllers.dtos.ClienteDto;
import br.com.fiap.techchallenge.interfacelayer.controllers.dtos.ProdutoDto;
import br.com.fiap.techchallenge.interfacelayer.controllers.dtos.pedido.ItemPedidoDto;
import br.com.fiap.techchallenge.interfacelayer.controllers.dtos.pedido.PedidoDto;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Classe responsável por popular o banco de dados com dados de exemplo no início da aplicação.
 */
public final class PopularBd {

  private static final Logger logger = LoggerFactory.getLogger(PopularBd.class);

  private static ClienteApi clienteApi;
  private static ProdutoApi produtoApi;
  private static PedidoApi pedidoApi;

  private static final Random random = new Random();

  /** 
   * Construtor da classe.
   */
  private PopularBd() {
    // Construtor vazio criado para ocultar o construtor público.
  }

  /** 
   * Popula o banco de dados no início da aplicação.
   */
  public static void popular(ConfigurableApplicationContext context) {

    clienteApi = context.getBean(ClienteApi.class);
    produtoApi = context.getBean(ProdutoApi.class);
    pedidoApi = context.getBean(PedidoApi.class);

    try {
      popularClientes();
      popularProdutos();
      popularPedidos();
    } catch (Exception e) {
      logger.info("Não foi possível popular o banco de dados.");
      logger.info(Arrays.toString(e.getStackTrace()));
    }
  }
  
  // Métodos privados
  private static void popularClientes() throws Exception {
    ClienteDto[] clientes = gerarClientes();
    for (ClienteDto cliente : clientes) {
      clienteApi.cadastrarCliente(cliente);
    }
  }

  private static void popularProdutos() throws Exception {
    ProdutoDto[] produtos = gerarProdutos();
    for (ProdutoDto produto : produtos) {
      produtoApi.cadastrarProduto(produto);
    }
  }

  private static void popularPedidos() throws Exception {
    PedidoDto[] pedidos = gerarPedidos();
    for (PedidoDto pedido : pedidos) {
      pedidoApi.fazerCheckout(pedido);
    }
  }

  private static ClienteDto[] gerarClientes() {
    return new ClienteDto[] {
        new ClienteDto(23456789092L, "Maria Clara de Oliveira", "maria_oliveira@gmail.com"),
        new ClienteDto(34567890175L, "Carlos Eduardo de Souza", "carlos_souza@gmail.com"),
        new ClienteDto(45678901249L, "Ana Paula Pereira", "ana_pereira@gmail.com"),
        new ClienteDto(56789012303L, "Lucas Henrique Fernandes", "lucas_fernandes@gmail.com"),
        new ClienteDto(12345678909L, "João Pedro da Silva", "joao_silva@gmail.com")
    };
  }

  private static ProdutoDto[] gerarProdutos() {
    return new ProdutoDto[] {
        // Acompanhamentos
        new ProdutoDto("Batata Frita", "Batata frita crocante",
        BigDecimal.valueOf(5.50), "ACOMPANHAMENTO"),

        new ProdutoDto("Anéis de Cebola", "Anéis de cebola empanados", 
        BigDecimal.valueOf(6.00), "ACOMPANHAMENTO"),

        new ProdutoDto("Nuggets de Frango", "Porção de nuggets de frango", 
        BigDecimal.valueOf(7.00), "ACOMPANHAMENTO"),
        
        new ProdutoDto("Mandioca Frita", "Porção de mandioca frita", 
        BigDecimal.valueOf(5.00), "ACOMPANHAMENTO"),

        // Bebidas
        new ProdutoDto("Refrigerante Cola", "Refrigerante de cola 350ml", 
        BigDecimal.valueOf(3.50), "BEBIDA"),
        
        new ProdutoDto("Suco de Laranja", "Suco de laranja natural 300ml", 
        BigDecimal.valueOf(4.00), "BEBIDA"),
        
        new ProdutoDto("Água Mineral", "Água mineral sem gás 500ml", 
        BigDecimal.valueOf(2.50), "BEBIDA"),
        
        new ProdutoDto("Chá Gelado", "Chá gelado de limão 300ml", 
        BigDecimal.valueOf(3.00), "BEBIDA"),

        // Lanches
        new ProdutoDto("Cheeseburger Bacon", "Hambúrguer com queijo e bacon", 
        BigDecimal.valueOf(12.00), "LANCHE"),
        
        new ProdutoDto("Sanduíche de Frango", "Sanduíche de frango grelhado", 
        BigDecimal.valueOf(10.00), "LANCHE"),
        
        new ProdutoDto("Wrap Vegetariano", "Wrap vegetariano com legumes", 
        BigDecimal.valueOf(9.00), "LANCHE"),
        
        new ProdutoDto("Hot Dog", "Hot dog com molho especial", 
        BigDecimal.valueOf(8.00), "LANCHE"),

        // Sobremesas
        new ProdutoDto("Sorvete de Chocolate", "Sorvete de chocolate 2 bolas", 
        BigDecimal.valueOf(6.50),            "SOBREMESA"),
        
        new ProdutoDto("Torta de Maçã", "Torta de maçã com canela", 
        BigDecimal.valueOf(5.50), "SOBREMESA"),
        
        new ProdutoDto("Brownie", "Brownie de chocolate com nozes", 
        BigDecimal.valueOf(7.00), "SOBREMESA"),
        
        new ProdutoDto("Pudim", "Pudim de leite condensado", 
        BigDecimal.valueOf(4.50), "SOBREMESA")
    };
  }

  private static PedidoDto[] gerarPedidos() {
    return new PedidoDto[] {
        new PedidoDto(23456789092L, gerarItensPedido()),
        new PedidoDto(34567890175L, gerarItensPedido()),
        new PedidoDto(45678901249L, gerarItensPedido()),
        new PedidoDto(56789012303L, gerarItensPedido()),
        new PedidoDto(12345678909L, gerarItensPedido())
    };
  }

  private static List<ItemPedidoDto> gerarItensPedido() {
    int numeroDeItens = random.nextInt(2) + 1;

    return Arrays.asList(
        new ItemPedidoDto((long) (random.nextInt(16) + 1), random.nextInt(3) + 1),
        numeroDeItens == 2 ? new ItemPedidoDto((long) (random.nextInt(16) + 1), 
        random.nextInt(3) + 1) : null).stream().filter(Objects::nonNull).toList();
  }
}