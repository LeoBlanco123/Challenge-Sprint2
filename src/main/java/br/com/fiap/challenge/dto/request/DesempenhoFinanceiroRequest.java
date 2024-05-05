package br.com.fiap.challenge.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record DesempenhoFinanceiroRequest(

        @NotNull(message = "A receita é obrigatória")
        double receita,
        @NotNull(message = "O lucro é obrigatório")
        double lucro,
        @NotNull(message = "O crescimento é obrigatório")
        double crescimento,
        @Valid
        @NotNull(message = "O id é obrigatório")
        AbstractRequest empresa

) {
}
