package br.com.fiap.challenge.dto.response;

import lombok.Builder;

@Builder
public record TendenciasGastosResponse(

        Long id_TendenciaGasto,
        int ano,
        double gastoMarketing,
        double gastoAutomacao,
        EmpresasResponse empresa
) {
}
