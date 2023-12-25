package com.ficha.crisma.ficha.crisma.service;

import com.ficha.crisma.ficha.crisma.dto.StudentDTO;
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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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

            this.service.createReport(alunos);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    public void process(InputStream inputStream) throws IOException {
        var studentsList = getAlunosDeCrisma(inputStream);
        if (studentsList.isEmpty())
            return;

        this.service.createReport(studentsList);
    }

    private Queue<StudentDTO> getAlunosDeCrisma(InputStream inputStream) {
        Queue<StudentDTO> studentDTOList = new LinkedList<>();
        try (Workbook workbook = new XSSFWorkbook(inputStream)) {
            Sheet datatypeSheet = workbook.getSheetAt(0);
            for (Row currentRow : datatypeSheet) {
                if (isCrisma(currentRow)) {
                    CatequeseDTO catequeseDTO = CatequeseDTO.create(currentRow);
                    DadosReligiososDTO dadosReligiososDTO = DadosReligiososDTO.create(currentRow);
                    EnderecoDTO endereco = EnderecoDTO.create(currentRow);
                    StudentDTO aluno = StudentDTO.create(currentRow)
                            .addressDTO(endereco)
                            .dadosReligiososDTO(dadosReligiososDTO)
                            .catequeseDTO(catequeseDTO)
                            .build();
                    studentDTOList.add(aluno);
                }
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return studentDTOList;
    }

    private boolean isCrisma(Row row) {
        return row.getCell(20) != null && row.getCell(20).toString().equals("Crisma");
    }
}
