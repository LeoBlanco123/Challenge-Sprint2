package br.com.fiap.challenge.dto.response;

import lombok.Builder;

@Builder
public record DesempenhoFinanceiroResponse(

        Long id,
        Double receita,
        Double lucro,
        Double crescimento,
        EmpresasResponse empresas
) {
}
