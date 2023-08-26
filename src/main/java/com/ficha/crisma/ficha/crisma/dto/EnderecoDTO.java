package com.ficha.crisma.ficha.crisma.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.poi.ss.usermodel.Row;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoDTO {

    private String nome;
    private String numero;
    private String bairro;
    private String telefone1;
    private String telefone2;

    public static EnderecoDTO create(Row row) {
        return EnderecoDTO.builder()
                .nome(row.getCell(8).toString())
                .numero(row.getCell(9).toString())
                .bairro(row.getCell(10).toString())
                .telefone1(row.getCell(11).toString())
                .telefone2(row.getCell(12).toString())
                .build();
    }

    public String getNumero(){
        return this.numero.isEmpty() ? "-" : this.numero;
    }

    public String getTelefone() {
        return this.telefone1 + " / " + this.telefone2;
    }
}
