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
@Table(name = "DesempenhoFinanceiro")
public class DesempenhoFinanceiro {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_DESEMPENHO_FINANCEIRO")
    @SequenceGenerator(name = "SQ_DESEMPENHO_FINANCEIRO", sequenceName = "SQ_DESEMPENHO_FINANCEIRO", allocationSize = 1)
    @Column(name = "Id_Desempenho")
    private Long id_Desempenho;

    @Column(name = "Receita")
    private double receita;

    @Column(name = "Lucro")
    private double lucro;

    @Column(name = "Crescimento")
    private double crescimento;


    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(
            name = "Empresa",
            referencedColumnName = "ID_Empresa",
            foreignKey = @ForeignKey(name = "FK_EMPRESA_DESEMP")
    )
    private Empresas empresa;
}
