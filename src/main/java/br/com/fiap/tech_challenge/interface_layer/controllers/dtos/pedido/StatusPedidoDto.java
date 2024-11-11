package br.com.fiap.tech_challenge.interface_layer.controllers.dtos.pedido;

import br.com.fiap.tech_challenge.business_layer.entities.pedido.StatusPedidoEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StatusPedidoDto {

    @Schema(description = "Número do pedido.", example = "123")
    public Long numeroPedido;

    @Schema(description = "Status do pedido.", example = "RECEBIDO")
    public StatusPedidoEnum status;

    @Schema(description = "Data e hora da última atualização do pedido.", example = "2024-09-08 09:10:00")
    public String dataHora;

}
