package br.com.fiap.challenge.dto.response;

import lombok.Builder;

@Builder
public record CadastroFuncionarioResponse(

        Long codFuncionario,
        String cargoFuncionario,
        CadastradosResponse cadastrado

) {
}
