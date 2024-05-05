package br.com.fiap.challenge.dto.response;

import lombok.Builder;

@Builder
public record TendenciasGastosResponse(

        Long id,
        Long ano,
        Double gastoMarketing,
        Double gastoAutomacao,
        EmpresasResponse empresa
) {
}
