package br.com.fiap.challenge.dto.response;

import lombok.Builder;

@Builder
public record CadastroFuncionarioResponse(

        Long id,
        String cargoFuncionario,
        CadastradosResponse cadastrado

) {
}
