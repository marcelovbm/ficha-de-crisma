package com.ficha.crisma.ficha.crisma.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;

@NoArgsConstructor
@AllArgsConstructor
@Table(name = "catequese")
@Entity(name = "catequese")
@EqualsAndHashCode(of = "id")
public class Catequese {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @Column(name = "etapa")
    private String etapa;
    @Column(name = "catequista")
    private String catequista;
    @Column(name = "comunidade")
    private String comunidade;
    @Column(name = "cidade")
    private String cidade;
}
