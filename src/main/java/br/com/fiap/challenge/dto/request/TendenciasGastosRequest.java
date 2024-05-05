package br.com.fiap.challenge.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record TendenciasGastosRequest(
        @NotNull(message = "O ano é obrigatório")
        Long ano,
        @NotNull(message = "O gasto em marketing é obrigatório")
        Double gastoMarketing,
        @NotNull(message = "O gasto em automação é obrigatório")
        Double gastoAutomacao,
        @Valid
        @NotNull(message = "O id é obrigatório")
        AbstractRequest empresa
) {
}
