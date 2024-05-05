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
@Table(name = "Empresas")
public class Empresas {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_EMPRESAS")
    @SequenceGenerator(name = "SQ_EMPRESAS", sequenceName = "SQ_EMPRESAS", allocationSize = 1)
    @Column(name = "ID_Empresa")
    private Long id_Empresa;

    @Column(name = "Nome")
    private String nome;

    @Column(name = "Tamanho")
    private String tamanho;

    @Column(name = "Setor")
    private String setor;

    @Column(name = "Localizacao_Geografica")
    private String localizacaoGeografica;

    @Column(name = "Numero_Funcionarios")
    private int numeroFuncionarios;

    @Column(name = "Tipo_Empresa")
    private String tipoEmpresa;

    @Column(name = "Cliente")
    private boolean cliente;
}