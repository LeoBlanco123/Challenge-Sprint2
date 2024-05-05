package br.com.fiap.challenge.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record ComportamentoNegociosRequest(

        @NotNull(message = "A interações é obrigatório")
        int interacoesPlataforma,
        @NotNull(message = "A frequencia é obrigatória")
        int frequenciaUso,
        String feedback,
        @NotNull(message = "O recursos é obrigatório")
        String usoRecursosEspecificos,
        @Valid
        @NotNull(message = "O id é obrigatório")
        AbstractRequest empresa
) {
}
