package br.com.fiap.challenge.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record HistoricoInteracoesRequest(

        @NotNull(message = "A proposta é obrigatória")
        String propostaNegocio,
        @NotNull(message = "O contrato é obrigatório")
        String contratoAssinado,
        String feedbackServicosProdutos,
        @Valid
        @NotNull(message = "O id é obrigatório")
        AbstractRequest empresa
) {
}
