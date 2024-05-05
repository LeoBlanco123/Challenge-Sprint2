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
@Table(name = "ComportamentoNegocios")
public class ComportamentoNegocios {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_COMPORTAMENTO_NEGOCIOS")
    @SequenceGenerator(name = "SQ_COMPORTAMENTO_NEGOCIOS", sequenceName = "SQ_COMPORTAMENTO_NEGOCIOS", allocationSize = 1)
    @Column(name = "Id_Comportamento")
    private Long id_Comportamento;

    @Column(name = "Interacoes_Plataforma")
    private int interacoesPlataforma;

    @Column(name = "FrequenciaUso")
    private int frequenciaUso;

    @Column(name = "Feedback")
    private String feedback;

    @Column(name = "UsoRecursos_Especificos")
    private String usoRecursosEspecificos;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(
            name = "Empresa",
            referencedColumnName = "ID_Empresa",
            foreignKey = @ForeignKey(name = "FK_EMPRESA_COMPORT")
    )
    private Empresas empresa;
}
