package br.com.fiap.techchallenge.interfacelayer.gateways.mappers;

import br.com.fiap.techchallenge.businesslayer.entities.pedido.StatusPedido;
import br.com.fiap.techchallenge.interfacelayer.gateways.entities.StatusPedidoJpa;

public final class StatusPedidoMapper {

    private StatusPedidoMapper() {}

    public static StatusPedidoJpa getStatusPedidoJpa(StatusPedido status) {
        return new StatusPedidoJpa(status.getStatus(), status.getDataHora());
    }

    public static StatusPedido getStatusPedido(StatusPedidoJpa status)
            throws Exception {
        return new StatusPedido(status.getStatus(), status.getDataHora());
    }

}
