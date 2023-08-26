package com.ficha.crisma.ficha.crisma.service;

import com.ficha.crisma.ficha.crisma.dto.AlunoDTO;
import com.ficha.crisma.ficha.crisma.dto.CatequeseDTO;
import com.ficha.crisma.ficha.crisma.dto.DadosReligiososDTO;
import com.ficha.crisma.ficha.crisma.dto.EnderecoDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RelatorioService {

    private final ExcelService service;

    public void process(Exchange exchange) {
        var file = exchange.getIn().getBody(File.class);
        var alunos = getAlunosDeCrisma(file);
        if (alunos.isEmpty())
            return;

        this.service.criarRelatorio(alunos);
    }

    private List<AlunoDTO> getAlunosDeCrisma(File file) {
        List<AlunoDTO> alunoDTOList = new ArrayList<>();
        try (FileInputStream excelFile = new FileInputStream(file); Workbook workbook = new XSSFWorkbook(excelFile)) {
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
