package com.fichaCrisma.ficaCrisma.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Iterator;

import org.apache.camel.Exchange;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.fichaCrisma.ficaCrisma.model.AlunosCrisma;
import com.fichaCrisma.ficaCrisma.model.Catequese;
import com.fichaCrisma.ficaCrisma.model.DadosReligiosos;
import com.fichaCrisma.ficaCrisma.model.Endereco;

@Service
public class ExecellService {

	public void process (Exchange exchange) throws ParseException {

		//		String fileName = (String) exchange.getIn().getHeader("CamelFileNameOnly");
		String fileAbsolutePath = (String) exchange.getIn().getHeader("CamelFilePath");

		try {

			FileInputStream excelFile = new FileInputStream(new File(fileAbsolutePath));
			Workbook workbook = new XSSFWorkbook(excelFile);
			Sheet datatypeSheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = datatypeSheet.iterator();

			while (iterator.hasNext()) {

				Row currentRow = iterator.next();

				if (currentRow.getCell(20).toString().equals("Crisma")) {
					
//					Date d2 = currentRow.getCell(13).getDateCellValue();
//					System.out.println(new SimpleDateFormat("dd/MM/yyyy").format(d2));
					
					Catequese catequese = new Catequese(currentRow.getCell(20).toString(), 
							currentRow.getCell(21).toString(), 
							currentRow.getCell(22).toString(), 
							currentRow.getCell(23).toString());
					DadosReligiosos dadosReligiosos = new DadosReligiosos(currentRow.getCell(13).getDateCellValue(), 
							currentRow.getCell(14).toString(), 
							currentRow.getCell(15).toString(), 
							currentRow.getCell(16).toString(), 
							currentRow.getCell(17).toString(), 
							currentRow.getCell(18).toString().equals("Não") ? false:true, 
							currentRow.getCell(19).toString().equals("Não") ? false:true);
					Endereco edereco = new Endereco(currentRow.getCell(7).toString(), 
							currentRow.getCell(8).toString(), 
							(int) currentRow.getCell(9).getNumericCellValue(), 
							currentRow.getCell(10).toString(), 
							currentRow.getCell(11).toString(),
							currentRow.getCell(12).toString());
					AlunosCrisma aluno = new AlunosCrisma(currentRow.getCell(1).toString(), 
							currentRow.getCell(2).getDateCellValue(), 
							currentRow.getCell(3).toString(), 
							currentRow.getCell(4).toString(), 
							currentRow.getCell(5).toString(), 
							currentRow.getCell(6).toString(), 
							edereco, 
							dadosReligiosos, 
							catequese);
					
					System.out.println(aluno.toString());
				}
			}
			workbook.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
