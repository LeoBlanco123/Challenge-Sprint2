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
@Table(name = "CadastroFuncionario")
public class CadastroFuncionario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_CAD_FUN")
    @SequenceGenerator(name = "SQ_CAD_FUN", sequenceName = "SQ_CAD_FUN", allocationSize = 1)
    @Column(name = "id_Funcionario")
    private Long id;

    @Column(name = "Senha_Funcionario")
    private String senhaFuncionario;

    @Column(name = "Cargo_Funcionario")
    private String cargoFuncionario;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(
            name = "CADASTRADOS",
            referencedColumnName = "Id_cadastrados",
            foreignKey = @ForeignKey(name = "FK_ID_CAD")
    )
    private Cadastrados cadastrado;
}
