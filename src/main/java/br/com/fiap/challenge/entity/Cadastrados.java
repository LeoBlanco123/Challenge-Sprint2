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
@Table(name = "Cadastrados")
public class Cadastrados {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_CADASTRADOS")
    @SequenceGenerator(name = "SQ_CADASTRADOS", sequenceName = "SQ_CADASTRADOS", allocationSize = 1)
    @Column(name = "Id_cadastrados")
    private Long id;

    @Column(name = "Cnpj")
    private String cnpj;

    @Column(name = "Senha")
    private String senha;

    @Column(name = "Nome")
    private String nome;
}
