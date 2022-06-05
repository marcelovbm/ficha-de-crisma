package com.fichaCrisma.ficaCrisma.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.poi.ss.usermodel.Cell;
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

	private static final String TEMPLATE_FILE = "template.xlsx";
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	private File diretorioComunidade;

	public void process(Exchange exchange) throws ParseException {
		var file = exchange.getIn().getBody(File.class);
		var alunos = getAlunosDeCrisma(file);

		if (alunos.isEmpty())
			return;

		diretorioComunidade = new File(
				file.getParent() + File.separator + alunos.get(0).getCatequese().getComunidade());
		diretorioComunidade.mkdir();
		criaFicha(alunos);
	}

	private void criaFicha(List<AlunosCrisma> alunos) {
		ClassLoader classLoader = getClass().getClassLoader();
		InputStream inputStream = classLoader.getResourceAsStream(TEMPLATE_FILE);

		try (Workbook wb = new XSSFWorkbook(inputStream);) {
			Sheet sheet = wb.getSheetAt(0);

			for (int index = 0; index < alunos.size(); index = index + 2) {
				inseriPrimeiroAluno(alunos.get(index), sheet);
				if (alunos.size() > index + 1)
					inseriSegundoAluno(alunos.get(index + 1), sheet);

				FileOutputStream fileOut = new FileOutputStream(
						diretorioComunidade.getAbsolutePath() + File.separator + alunos.get(index).getNome() + ".xlsx");
				wb.write(fileOut);
				fileOut.close();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void inseriSegundoAluno(AlunosCrisma aluno, Sheet sheet) {
		Row linhaComunidade = sheet.getRow(31);
		Cell comunidade = linhaComunidade.getCell(2);
		comunidade.setCellValue(aluno.getCatequese().getComunidade());

		Row linhaNome = sheet.getRow(32);
		Cell nome = linhaNome.getCell(2);
		nome.setCellValue(aluno.getNome());

		Row linhaDataNascimentoESexo = sheet.getRow(33);
		Cell dataNascimento = linhaDataNascimentoESexo.getCell(2);
		dataNascimento.setCellValue(dateFormat.format(aluno.getDataNascimento()));
		Cell sexo = linhaDataNascimentoESexo.getCell(7);
		sexo.setCellValue(aluno.getSexo());

		Row linhaEndereco = sheet.getRow(34);
		Cell endereco = linhaEndereco.getCell(2);
		endereco.setCellValue(aluno.getEndereco().getNome());
		Cell numero = linhaEndereco.getCell(10);
		numero.setCellValue(
				aluno.getEndereco().getNumero().isEmpty() ? "-" : String.valueOf(aluno.getEndereco().getNumero()));

		Row linhaBairroETelefone = sheet.getRow(35);
		Cell bairro = linhaBairroETelefone.getCell(2);
		bairro.setCellValue(aluno.getEndereco().getBairro());
		Cell telefone = linhaBairroETelefone.getCell(8);
		telefone.setCellValue(aluno.getEndereco().getTelefone1() + " / " + aluno.getEndereco().getTelefone2());

		Row linhaFiliacaoPai = sheet.getRow(36);
		Row linhaFiliacaoMae = sheet.getRow(37);
		Cell pai = linhaFiliacaoPai.getCell(3);
		pai.setCellValue(aluno.getPai());
		Cell mae = linhaFiliacaoMae.getCell(3);
		mae.setCellValue(aluno.getMae());

		Row linhaLocalIniciacaoEucaristica = sheet.getRow(38);
		Cell localIniciacaoEucaristica = linhaLocalIniciacaoEucaristica.getCell(4);
		localIniciacaoEucaristica.setCellValue(aluno.getCatequese().getComunidade());

		Row linhaParoquiaBatismo = sheet.getRow(42);
		Cell paroquiaBatismo = linhaParoquiaBatismo.getCell(3);
		paroquiaBatismo.setCellValue(aluno.getDadosReligiosos().getParoquiaDoBatismo());

		Row linhaDataBatizado = sheet.getRow(43);
		Cell dataBatizado = linhaDataBatizado.getCell(2);
		dataBatizado.setCellValue(dateFormat.format(aluno.getDadosReligiosos().getBatismo()));
	}

	private void inseriPrimeiroAluno(AlunosCrisma aluno, Sheet sheet) {
		Row linhaComunidade = sheet.getRow(4);
		Cell comunidade = linhaComunidade.getCell(2);
		comunidade.setCellValue(aluno.getCatequese().getComunidade());

		Row linhaNome = sheet.getRow(5);
		Cell nome = linhaNome.getCell(2);
		nome.setCellValue(aluno.getNome());

		Row linhaDataNascimentoESexo = sheet.getRow(6);
		Cell dataNascimento = linhaDataNascimentoESexo.getCell(2);
		dataNascimento.setCellValue(dateFormat.format(aluno.getDataNascimento()));
		Cell sexo = linhaDataNascimentoESexo.getCell(7);
		sexo.setCellValue(aluno.getSexo());

		Row linhaEndereco = sheet.getRow(7);
		Cell endereco = linhaEndereco.getCell(2);
		endereco.setCellValue(aluno.getEndereco().getNome());
		Cell numero = linhaEndereco.getCell(10);
		numero.setCellValue(
				aluno.getEndereco().getNumero().isEmpty() ? "-" : String.valueOf(aluno.getEndereco().getNumero()));

		Row linhaBairroETelefone = sheet.getRow(8);
		Cell bairro = linhaBairroETelefone.getCell(2);
		bairro.setCellValue(aluno.getEndereco().getBairro());
		Cell telefone = linhaBairroETelefone.getCell(8);
		telefone.setCellValue(aluno.getEndereco().getTelefone1());

		Row linhaFiliacaoPai = sheet.getRow(9);
		Row linhaFiliacaoMae = sheet.getRow(10);
		Cell pai = linhaFiliacaoPai.getCell(3);
		pai.setCellValue(aluno.getPai());
		Cell mae = linhaFiliacaoMae.getCell(3);
		mae.setCellValue(aluno.getMae());

		Row linhaLocalIniciacaoEucaristica = sheet.getRow(11);
		Cell localIniciacaoEucaristica = linhaLocalIniciacaoEucaristica.getCell(4);
		localIniciacaoEucaristica.setCellValue(aluno.getCatequese().getComunidade());

		Row linhaParoquiaBatismo = sheet.getRow(15);
		Cell paroquiaBatismo = linhaParoquiaBatismo.getCell(3);
		paroquiaBatismo.setCellValue(aluno.getDadosReligiosos().getParoquiaDoBatismo());

		Row linhaDataBatizado = sheet.getRow(16);
		Cell dataBatizado = linhaDataBatizado.getCell(2);
		dataBatizado.setCellValue(dateFormat.format(aluno.getDadosReligiosos().getBatismo()));
	}

	private List<AlunosCrisma> getAlunosDeCrisma(File file) {
		List<AlunosCrisma> alunos = new ArrayList<>();
		try (FileInputStream excelFile = new FileInputStream(file); Workbook workbook = new XSSFWorkbook(excelFile);) {
			Sheet datatypeSheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = datatypeSheet.iterator();
			while (iterator.hasNext()) {
				Row currentRow = iterator.next();
				if (currentRow.getCell(20) != null && currentRow.getCell(20).toString().equals("Crisma")) {
					Catequese catequese = new Catequese(currentRow.getCell(20).toString(),
							currentRow.getCell(21).toString(), currentRow.getCell(22).toString(),
							currentRow.getCell(23).toString());
					DadosReligiosos dadosReligiosos = new DadosReligiosos(currentRow.getCell(13).getDateCellValue(),
							currentRow.getCell(14).toString(), currentRow.getCell(15).toString(),
							currentRow.getCell(16).toString(), currentRow.getCell(17).toString(),
							currentRow.getCell(18).toString().equals("Não") ? false : true,
							currentRow.getCell(19).toString().equals("Não") ? false : true);
					Endereco edereco = new Endereco(currentRow.getCell(8).toString(), currentRow.getCell(9).toString(),
							currentRow.getCell(10).toString(), currentRow.getCell(11).toString(),
							currentRow.getCell(12).toString());
					AlunosCrisma aluno = new AlunosCrisma(currentRow.getCell(1).toString(),
							currentRow.getCell(2).getDateCellValue(), currentRow.getCell(3).toString(),
							currentRow.getCell(4).toString(), currentRow.getCell(5).toString(),
							currentRow.getCell(6).toString(), currentRow.getCell(7).toString(), edereco,
							dadosReligiosos, catequese);
					alunos.add(aluno);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return alunos;
	}
}
