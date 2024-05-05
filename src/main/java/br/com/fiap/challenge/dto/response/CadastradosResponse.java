package br.com.fiap.challenge.dto.response;

import lombok.Builder;

@Builder
public record CadastradosResponse(

        String cnpj,
        String nome
) {
}
