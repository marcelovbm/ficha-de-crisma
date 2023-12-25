package com.ficha.crisma.ficha.crisma.service;

import com.ficha.crisma.ficha.crisma.dto.AlunoDTO;
import com.ficha.crisma.ficha.crisma.dto.CatequeseDTO;
import com.ficha.crisma.ficha.crisma.dto.DadosReligiososDTO;
import com.ficha.crisma.ficha.crisma.dto.EnderecoDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RelatorioService {

    private final ExcelTemplateService service;

    public void process(File file) {
        try (FileInputStream inputStream = new FileInputStream(file)) {
            var alunos = getAlunosDeCrisma(inputStream);
            if (alunos.isEmpty())
                return;

            this.service.criarRelatorio(alunos);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    public void process(InputStream inputStream) {
        var alunos = getAlunosDeCrisma(inputStream);
        if (alunos.isEmpty())
            return;

        this.service.criarRelatorio(alunos);
    }

    private List<AlunoDTO> getAlunosDeCrisma(InputStream inputStream) {
        List<AlunoDTO> alunoDTOList = new ArrayList<>();
        try (Workbook workbook = new XSSFWorkbook(inputStream)) {
            Sheet datatypeSheet = workbook.getSheetAt(0);
            for (Row currentRow : datatypeSheet) {
                if (isCrisma(currentRow)) {
                    CatequeseDTO catequeseDTO = CatequeseDTO.create(currentRow);
                    DadosReligiososDTO dadosReligiososDTO = DadosReligiososDTO.create(currentRow);
                    EnderecoDTO endereco = EnderecoDTO.create(currentRow);
                    AlunoDTO aluno = AlunoDTO.create(currentRow)
                            .enderecoDTO(endereco)
                            .dadosReligiososDTO(dadosReligiososDTO)
                            .catequeseDTO(catequeseDTO)
                            .build();
                    alunoDTOList.add(aluno);
                }
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return alunoDTOList;
    }

    private boolean isCrisma(Row row) {
        return row.getCell(20) != null && row.getCell(20).toString().equals("Crisma");
    }
}
