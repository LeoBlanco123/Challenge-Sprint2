package br.com.fiap.challenge.dto.response;

import lombok.Builder;

@Builder
public record HistoricoInteracoesResponse(

        Long id,
        String propostaNegocio,
        String contratoAssinado,
        String feedbackServicosProdutos,
        EmpresasResponse empresa
) {
}
