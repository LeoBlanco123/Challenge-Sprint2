package br.com.fiap.challenge.dto.request;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CNPJ;

public record CadastradosRequest(
        @NotNull(message = "O cnpj é obrigatório")
        @CNPJ
        String cnpj,
        @NotNull(message = "A senha é obrigatória")
        String senha,
        @NotNull(message = "O nome é obrigatório")
        String nome
) {
}
