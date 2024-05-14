package br.com.fiap.challenge.dto.request;

import jakarta.validation.constraints.NotNull;

public record EmpresasRequest(

        @NotNull(message = "O nome é obrigatório")
        String nome,
        @NotNull(message = "O tamanho é obrigatório")
        String tamanho,
        @NotNull(message = "O setor é obrigatório")
        String setor,
        @NotNull(message = "A localização é obrigatória")
        String localizacaoGeografica,
        @NotNull(message = "O numero de funcionario é obrigatório")
        Long numeroFuncionarios,
        @NotNull(message = "O tipo da emopresa é obrigatório")
        String tipoEmpresa,
        @NotNull(message = "O campo cliente é obrigatório (true or false)")
        Boolean cliente
) {
}
