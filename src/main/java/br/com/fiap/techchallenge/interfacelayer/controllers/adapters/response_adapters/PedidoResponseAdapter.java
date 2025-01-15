package br.com.fiap.techchallenge.interfacelayer.controllers.adapters.response_adapters;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.fiap.techchallenge.businesslayer.entities.pedido.Pedido;

public class PedidoResponseAdapter {

    public static ResponseEntity<Pedido> adaptar(Pedido pedido, HttpStatus status) {
        return new ResponseEntity<>(pedido, status);
    }

    public static ResponseEntity<List<Pedido>> adaptar(List<Pedido> pedidos, HttpStatus status) {
        return new ResponseEntity<>(pedidos, status);
    }
}
