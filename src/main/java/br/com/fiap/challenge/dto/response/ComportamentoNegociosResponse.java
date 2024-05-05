package br.com.fiap.challenge.dto.response;

import lombok.Builder;

@Builder
public record ComportamentoNegociosResponse(

        Long id,
        Long interacoesPlataforma,
        Long frequenciaUso,
        String feedback,
        String usoRecursosEspecificos,
        EmpresasResponse empresa
) {
}
