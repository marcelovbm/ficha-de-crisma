package com.ficha.crisma.ficha.crisma.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;

@Table(name = "endereco")
@Entity(name = "endereco")
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private String id;
    private String rua;
    private String numero;
    private String bairro;
    private String telefone1;
    private String telefone2;
}
