package br.com.fiap.challenge.dto.response;

import lombok.Builder;

@Builder
public record EmpresasResponse(

        Long id,
        String nome,
        String tamanho,
        String setor,
        String localizacaoGeografica,
        Long numeroFuncionarios,
        String tipoEmpresa,
        boolean cliente
) {
}
