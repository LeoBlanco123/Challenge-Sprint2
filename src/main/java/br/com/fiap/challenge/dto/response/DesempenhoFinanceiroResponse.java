package br.com.fiap.challenge.dto.response;

import lombok.Builder;

@Builder
public record DesempenhoFinanceiroResponse(

        Long id_Desempenho,
        double receita,
        double lucro,
        double crescimento,
        EmpresasResponse empresas
) {
}
