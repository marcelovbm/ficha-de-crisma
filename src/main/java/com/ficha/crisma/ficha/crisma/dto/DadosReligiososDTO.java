package com.ficha.crisma.ficha.crisma.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.poi.ss.usermodel.Row;

import java.util.Date;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DadosReligiososDTO {

	private Date batismo;
	private String paroquiaDoBatismo;
	private String padrinho;
	private String madrinha;
	private String celebrante;
	private boolean primeiraEucaristia;
	private boolean preCrisma;

	public static DadosReligiososDTO create(Row row) {
		return DadosReligiososDTO.builder()
				.batismo(row.getCell(13).getDateCellValue())
				.paroquiaDoBatismo(row.getCell(14).toString())
				.padrinho(row.getCell(15).toString())
				.madrinha(row.getCell(16).toString())
				.celebrante(row.getCell(17).toString())
				.primeiraEucaristia(!row.getCell(18).toString().equals("Não"))
				.preCrisma(!row.getCell(19).toString().equals("Não"))
				.build();
	}
}
