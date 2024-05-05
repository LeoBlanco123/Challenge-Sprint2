package br.com.fiap.challenge.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    @Column(name = "Cnpj")
    private String cnpj;

    @Column(name = "Senha")
    private String senha;

    @Column(name = "Nome")
    private String nome;
}
