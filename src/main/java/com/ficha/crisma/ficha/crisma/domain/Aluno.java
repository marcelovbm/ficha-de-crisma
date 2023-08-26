package com.ficha.crisma.ficha.crisma.domain;

import com.ficha.crisma.ficha.crisma.enums.GenerosEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static jakarta.persistence.GenerationType.IDENTITY;

@NoArgsConstructor
@AllArgsConstructor
@Table(name = "alunos")
@Entity(name = "alunos")
@EqualsAndHashCode(of = "id")
public class Aluno {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "nascimento")
    private LocalDate nascimento;
    @Column(name = "pai")
    private String pai;
    @Column(name = "mae")
    private String mae;
    @Column(name = "genero")
    private GenerosEnum genero;
    @OneToOne
    @JoinColumn(name = "endereco_id", table = "endereco")
    private Endereco endereco;

}

