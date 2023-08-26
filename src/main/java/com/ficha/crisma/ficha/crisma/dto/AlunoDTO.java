package com.ficha.crisma.ficha.crisma.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.poi.ss.usermodel.Row;

import java.time.LocalDate;

import static java.text.MessageFormat.format;
import static java.util.Objects.isNull;
import static org.apache.logging.log4j.util.Strings.EMPTY;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlunoDTO {

    private String nome;
    private LocalDate dataNascimento;
    private String idade;
    private String sexo;
    private String pai;
    private String mae;
    private String estadoCivil;
    private EnderecoDTO enderecoDTO;
    private DadosReligiososDTO dadosReligiososDTO;
    private CatequeseDTO catequeseDTO;

    public static AlunoDTOBuilder create(Row row) {
        return AlunoDTO.builder()
                .nome(row.getCell(1).toString())
                .dataNascimento(row.getCell(2).getLocalDateTimeCellValue().toLocalDate())
                .idade(row.getCell(3).toString())
                .sexo(row.getCell(4).toString())
                .pai(row.getCell(5).toString())
                .mae(row.getCell(6).toString())
                .estadoCivil(row.getCell(7).toString());
    }

    public String getComunidade() {
        if (isNull(this.catequeseDTO))
            return EMPTY;
        return this.catequeseDTO.getComunidade();
    }

    @Override
    public String toString() {
        return format("[Aluno: {0}; Idade: {1}; EnderecoDTO: {2}; Catequista: {3}]",
                this.nome, this.idade, this.enderecoDTO.getNome(), this.catequeseDTO.getCatequista());
    }
}
