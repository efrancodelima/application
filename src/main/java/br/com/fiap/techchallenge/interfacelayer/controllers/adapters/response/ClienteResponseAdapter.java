package br.com.fiap.techchallenge.interfacelayer.controllers.adapters.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.fiap.techchallenge.businesslayer.entities.cliente.Cliente;

public final class ClienteResponseAdapter {

    private ClienteResponseAdapter() {}

    public static ResponseEntity<Cliente> adaptar(Cliente cliente, HttpStatus status) {
        return new ResponseEntity<>(cliente, status);
    }

}
