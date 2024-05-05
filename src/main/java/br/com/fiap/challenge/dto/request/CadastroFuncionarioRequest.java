package br.com.fiap.challenge.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record CadastroFuncionarioRequest(

        @NotNull(message = "A senha é obrigatória")
        String senhaFuncionario,
        @NotNull(message = "O cargo é obrigatório")
        String cargoFuncionario,
        @Valid
        @NotNull(message = "O cnpj é obrigatório")
        AbstractRequest cadastrado

) {
}
