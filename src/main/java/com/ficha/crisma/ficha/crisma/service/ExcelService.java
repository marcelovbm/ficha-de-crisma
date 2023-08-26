package com.ficha.crisma.ficha.crisma.service;

import com.ficha.crisma.ficha.crisma.dto.AlunoDTO;
import com.ficha.crisma.ficha.crisma.dto.CatequeseDTO;
import com.ficha.crisma.ficha.crisma.dto.DadosReligiososDTO;
import com.ficha.crisma.ficha.crisma.dto.EnderecoDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static java.io.File.separator;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExcelService {

    private final XSSFWorkbook xssfWorkbook;

    public void criarRelatorio(List<AlunoDTO> alunoDTOList) {
        var resource = getClass().getClassLoader().getResource(".");
        var diretorio = new File(resource + separator + getComunidade(alunoDTOList));
        if (!diretorio.mkdir())
            return;
        try (var wb = xssfWorkbook) {
            var sheet = wb.getSheetAt(0);
            boolean isPrimeiroAluno = true;
            for (var alunoDTO : alunoDTOList) {
                if (isPrimeiroAluno) {
                    inserirPrimeiroAluno(alunoDTO, sheet);
                    isPrimeiroAluno = false;
                } else {
                    inserirSegundoAluno(alunoDTO, sheet);
                }

                var fileOut = new FileOutputStream(diretorio.getAbsolutePath() + separator + alunoDTO.getNome() + ".xlsx");
                wb.write(fileOut);
                fileOut.close();
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    private void inserirPrimeiroAluno(AlunoDTO alunoDTO, Sheet sheet) {
        setComunidade(alunoDTO.getCatequeseDTO().getComunidade(), sheet, true);
        setNome(alunoDTO.getNome(), sheet, true);
        setDataNascimento(alunoDTO.getDataNascimento(), sheet, true);
        setGenero(alunoDTO.getSexo(), sheet, true);
        setEndereco(alunoDTO.getEnderecoDTO(), sheet, true);
        setBairro(alunoDTO.getEnderecoDTO(), sheet, true);
        setTelefone(alunoDTO.getEnderecoDTO(), sheet, true);
        setFiliacao(alunoDTO, sheet, true);
        setLocalIniciacaoEucaristica(alunoDTO.getCatequeseDTO(), sheet, true);
        setParoquiaBatismo(alunoDTO.getDadosReligiososDTO(), sheet, true);
        setDataBatizado(alunoDTO.getDadosReligiososDTO(), sheet, true);
    }

    private void inserirSegundoAluno(AlunoDTO alunoDTO, Sheet sheet) {
        setComunidade(alunoDTO.getCatequeseDTO().getComunidade(), sheet, false);
        setNome(alunoDTO.getNome(), sheet, false);
        setDataNascimento(alunoDTO.getDataNascimento(), sheet, false);
        setGenero(alunoDTO.getSexo(), sheet, false);
        setEndereco(alunoDTO.getEnderecoDTO(), sheet, false);
        setBairro(alunoDTO.getEnderecoDTO(), sheet, false);
        setTelefone(alunoDTO.getEnderecoDTO(), sheet, false);
        setFiliacao(alunoDTO, sheet, false);
        setLocalIniciacaoEucaristica(alunoDTO.getCatequeseDTO(), sheet, false);
        setParoquiaBatismo(alunoDTO.getDadosReligiososDTO(), sheet, false);
        setDataBatizado(alunoDTO.getDadosReligiososDTO(), sheet, false);
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

    private void setFiliacao(AlunoDTO alunoDTO, Sheet sheet, boolean isPrimeiroAluno) {
        var rowPai = sheet.getRow(isPrimeiroAluno ? 9 : 36);
        var cellPai = rowPai.getCell(3);
        cellPai.setCellValue(alunoDTO.getPai());
        var rowMae = sheet.getRow(isPrimeiroAluno ? 10 : 37);
        var cellMae = rowMae.getCell(3);
        cellMae.setCellValue(alunoDTO.getMae());
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

    private String getComunidade(List<AlunoDTO> alunoDTOList) {
        return alunoDTOList.stream()
                .map(AlunoDTO::getComunidade)
                .findFirst()
                .orElse("");
    }

}
