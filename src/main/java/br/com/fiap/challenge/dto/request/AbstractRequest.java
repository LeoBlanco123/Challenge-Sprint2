package br.com.fiap.challenge.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record AbstractRequest(
        @Positive(message = "O Id deve ser um número positivo")
        @NotNull(message = "O id é obrigatório")
        Long id,
        @Positive(message = "O cnpj deve ser um número positivo")
        @NotNull(message = "O cnpj é obrigatório")
        Long cnpj
) {
}