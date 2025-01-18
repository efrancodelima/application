package br.com.fiap.techchallenge.interfacelayer.controllers.adapters.response;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.fiap.techchallenge.businesslayer.entities.produto.Produto;

public final class ProdutoResponseAdapter {

    private ProdutoResponseAdapter() {}

    public static ResponseEntity<Produto> adaptar(Produto produto, HttpStatus status) {
        return new ResponseEntity<>(produto, status);
    }

    public static ResponseEntity<List<Produto>> adaptar(List<Produto> produtos, HttpStatus status) {
        return new ResponseEntity<>(produtos, status);
    }

}
