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
@Table(name = "HistoricoInteracoes")
public class HistoricoInteracoes {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_HISTORICO_INTERACOES")
    @SequenceGenerator(name = "SQ_HISTORICO_INTERACOES", sequenceName = "SQ_HISTORICO_INTERACOES", allocationSize = 1)
    @Column(name = "Id_Interacao")
    private Long id_Interacao;

    @Column(name = "Proposta_Negocio")
    private String propostaNegocio;

    @Column(name = "Contrato_Assinado")
    private String contratoAssinado;

    @Column(name = "Feedback_Servicos_Produtos")
    private String feedbackServicosProdutos;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(
            name = "Empresa",
            referencedColumnName = "ID_Empresa",
            foreignKey = @ForeignKey(name = "FK_EMPRESA_HIST")
    )
    private Empresas empresa;
}
