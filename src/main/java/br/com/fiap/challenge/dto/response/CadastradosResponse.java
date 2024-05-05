package br.com.fiap.challenge.dto.response;

import lombok.Builder;

@Builder
public record CadastradosResponse(

        Long id,
        String cnpj,
        String nome
) {
}
