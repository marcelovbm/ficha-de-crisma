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
public class StudentDTO {

    private String name;
    private LocalDate birthdayDate;
    private String age;
    private String sex;
    private String father;
    private String mother;
    private String estadoCivil;
    private EnderecoDTO addressDTO;
    private DadosReligiososDTO dadosReligiososDTO;
    private CatequeseDTO catequeseDTO;

    public static StudentDTOBuilder create(Row row) {
        return StudentDTO.builder()
                .name(row.getCell(1).toString())
                .birthdayDate(row.getCell(2).getLocalDateTimeCellValue().toLocalDate())
                .age(row.getCell(3).toString())
                .sex(row.getCell(4).toString())
                .father(row.getCell(5).toString())
                .mother(row.getCell(6).toString())
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
                this.name, this.age, this.addressDTO.getNome(), this.catequeseDTO.getCatequista());
    }
}
