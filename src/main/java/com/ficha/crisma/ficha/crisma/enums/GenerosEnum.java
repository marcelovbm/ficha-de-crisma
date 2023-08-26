package com.ficha.crisma.ficha.crisma.enums;

import lombok.Getter;

@Getter
public enum GenerosEnum {

    MASCULINO("M"),
    FEMININO("F");

    private final String codigo;

    GenerosEnum(String codigo) {
        this.codigo = codigo;
    }
}
