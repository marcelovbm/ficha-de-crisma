package com.ficha.crisma.ficha.crisma.service;

import com.ficha.crisma.ficha.crisma.dto.CatequeseDTO;
import com.ficha.crisma.ficha.crisma.dto.DadosReligiososDTO;
import com.ficha.crisma.ficha.crisma.dto.EnderecoDTO;
import com.ficha.crisma.ficha.crisma.dto.StudentDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.Queue;

import static java.io.File.separator;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExcelTemplateService {

    private static final String EXCEL_TEMPLATE_FILE = "excel/template.xlsx";

    public void createReport(Queue<StudentDTO> studentDTOList) throws IOException {
        var resource = "C:\\tmp";
        var file = new File(resource + separator + getComunidade(studentDTOList));
        if (!file.mkdir())
            return;

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(EXCEL_TEMPLATE_FILE);
        assert inputStream != null;

        try (var wb = new XSSFWorkbook(inputStream)) {
            var sheet = wb.getSheetAt(0);
            boolean isFirstStudent = true;
            var fileName = "";
            FileOutputStream outputStream = null;
            while (!studentDTOList.isEmpty()) {
                var student = studentDTOList.poll();
                if (isFirstStudent) {
                    insertFirstStudent(student, sheet);
                    fileName = file.getAbsolutePath() + separator + student.getName() + ".xlsx";
                    outputStream = new FileOutputStream(fileName);
                    isFirstStudent = false;
                } else {
                    insertSecondStudent(student, sheet);
                    isFirstStudent = true;
                }
                if (isFirstStudent || studentDTOList.isEmpty()) {
                    wb.write(outputStream);
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    private void insertFirstStudent(StudentDTO studentDTO, Sheet sheet) {
        setComunidade(studentDTO.getCatequeseDTO().getComunidade(), sheet, true);
        setNome(studentDTO.getName(), sheet, true);
        setDataNascimento(studentDTO.getBirthdayDate(), sheet, true);
        setGenero(studentDTO.getSex(), sheet, true);
        setEndereco(studentDTO.getAddressDTO(), sheet, true);
        setBairro(studentDTO.getAddressDTO(), sheet, true);
        setTelefone(studentDTO.getAddressDTO(), sheet, true);
        setFiliacao(studentDTO, sheet, true);
        setLocalIniciacaoEucaristica(studentDTO.getCatequeseDTO(), sheet, true);
        setParoquiaBatismo(studentDTO.getDadosReligiososDTO(), sheet, true);
        setDataBatizado(studentDTO.getDadosReligiososDTO(), sheet, true);
    }

    private void insertSecondStudent(StudentDTO studentDTO, Sheet sheet) {
        setComunidade(studentDTO.getCatequeseDTO().getComunidade(), sheet, false);
        setNome(studentDTO.getName(), sheet, false);
        setDataNascimento(studentDTO.getBirthdayDate(), sheet, false);
        setGenero(studentDTO.getSex(), sheet, false);
        setEndereco(studentDTO.getAddressDTO(), sheet, false);
        setBairro(studentDTO.getAddressDTO(), sheet, false);
        setTelefone(studentDTO.getAddressDTO(), sheet, false);
        setFiliacao(studentDTO, sheet, false);
        setLocalIniciacaoEucaristica(studentDTO.getCatequeseDTO(), sheet, false);
        setParoquiaBatismo(studentDTO.getDadosReligiososDTO(), sheet, false);
        setDataBatizado(studentDTO.getDadosReligiososDTO(), sheet, false);
    }

    private void setComunidade(String comunidade, Sheet sheet, boolean isPrimeiroAluno) {
        var row = sheet.getRow(isPrimeiroAluno ? 4 : 31);
        var cell = row.getCell(2);
        cell.setCellValue(comunidade);
    }

    private void setNome(String nome, Sheet sheet, boolean isPrimeiroAluno) {
        var row = sheet.getRow(isPrimeiroAluno ? 5 : 32);
        var cell = row.getCell(2);
        cell.setCellValue(nome);
    }

    private void setDataNascimento(LocalDate dataNascimento, Sheet sheet, boolean isPrimeiroAluno) {
        var row = sheet.getRow(isPrimeiroAluno ? 6 : 33);
        var cell = row.getCell(2);
        cell.setCellValue(dataNascimento);
    }

    private void setGenero(String genero, Sheet sheet, boolean isPrimeiroAluno) {
        var row = sheet.getRow(isPrimeiroAluno ? 6 : 33);
        var cell = row.getCell(7);
        cell.setCellValue(genero);
    }

    private void setEndereco(EnderecoDTO enderecoDTO, Sheet sheet, boolean isPrimeiroAluno) {
        var row = sheet.getRow(isPrimeiroAluno ? 7 : 34);
        var cellEndereco = row.getCell(2);
        cellEndereco.setCellValue(enderecoDTO.getNome());
        var cellNumero = row.getCell(10);
        cellNumero.setCellValue(enderecoDTO.getNumero());
    }

    private void setBairro(EnderecoDTO enderecoDTO, Sheet sheet, boolean isPrimeiroAluno) {
        var row = sheet.getRow(isPrimeiroAluno ? 8 : 35);
        var cell = row.getCell(2);
        cell.setCellValue(enderecoDTO.getBairro());
    }

    private void setTelefone(EnderecoDTO enderecoDTO, Sheet sheet, boolean isPrimeiroAluno) {
        var linhaBairroETelefone = sheet.getRow(isPrimeiroAluno ? 8 : 35);
        var telefone = linhaBairroETelefone.getCell(8);
        telefone.setCellValue(enderecoDTO.getTelefone());
    }

    private void setFiliacao(StudentDTO studentDTO, Sheet sheet, boolean isPrimeiroAluno) {
        var rowPai = sheet.getRow(isPrimeiroAluno ? 9 : 36);
        var cellPai = rowPai.getCell(3);
        cellPai.setCellValue(studentDTO.getFather());
        var rowMae = sheet.getRow(isPrimeiroAluno ? 10 : 37);
        var cellMae = rowMae.getCell(3);
        cellMae.setCellValue(studentDTO.getMother());
    }

    private void setLocalIniciacaoEucaristica(CatequeseDTO catequeseDTO, Sheet sheet, boolean isPrimeiroAluno) {
        var row = sheet.getRow(isPrimeiroAluno ? 11 : 38);
        var cell = row.getCell(4);
        cell.setCellValue(catequeseDTO.getComunidade());
    }

    private void setParoquiaBatismo(DadosReligiososDTO dadosReligiososDTO, Sheet sheet, boolean isPrimeiroAluno) {
        var row = sheet.getRow(isPrimeiroAluno ? 15 : 42);
        var cell = row.getCell(2);
        cell.setCellValue(dadosReligiososDTO.getParoquiaDoBatismo());
    }

    private void setDataBatizado(DadosReligiososDTO dadosReligiososDTO, Sheet sheet, boolean isPrimeiroAluno) {
        var row = sheet.getRow(isPrimeiroAluno ? 16 : 43);
        var cell = row.getCell(2);
        cell.setCellValue(dadosReligiososDTO.getBatismo());
    }

    private String getComunidade(Queue<StudentDTO> studentDTOList) {
        return studentDTOList.stream()
                .map(StudentDTO::getComunidade)
                .findFirst()
                .orElse("");
    }

}
