package br.com.fiap.challenge.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "TendenciasGastos")
public class TendenciasGastos {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_TENDENCIAS_GASTOS")
    @SequenceGenerator(name = "SQ_TENDENCIAS_GASTOS", sequenceName = "SQ_TENDENCIAS_GASTOS", allocationSize = 1)
    @Column(name = "Id_TendenciaGasto")
    private Long id;
    @Column(name = "Ano")
    private Long ano;

    @Column(name = "Gasto_Marketing")
    private Double gastoMarketing;

    @Column(name = "Gasto_Automacao")
    private Double gastoAutomacao;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(
            name = "Empresa",
            referencedColumnName = "ID_Empresa",
            foreignKey = @ForeignKey(name = "FK_EMPRESA_TENDEN")
    )
    private Empresas empresa;
}
