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
public class CatequeseDTO {

	private String etapa;
	private String catequista;
	private String comunidade;
	private String cidade;

	public static CatequeseDTO create(Row row) {
		return CatequeseDTO.builder()
				.etapa(row.getCell(20).toString())
				.catequista(row.getCell(21).toString())
				.comunidade(row.getCell(22).toString())
				.cidade(row.getCell(23).toString())
				.build();
	}
}
