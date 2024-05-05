package br.com.fiap.challenge.dto.response;

import lombok.Builder;

@Builder
public record ComportamentoNegociosResponse(

        Long id_Comportamento,
        int interacoesPlataforma,
        int frequenciaUso,
        String feedback,
        String usoRecursosEspecificos,
        EmpresasResponse empresa
) {
}
