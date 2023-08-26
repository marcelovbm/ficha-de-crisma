package com.ficha.crisma.ficha.crisma.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
@Configuration
public class ExcelTemplateConfig {

    private static final String EXCEL_TEMPLATE_FILE = "excel/template.xlsx";

    @Bean
    public XSSFWorkbook excelTemplate() {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream(EXCEL_TEMPLATE_FILE);

            assert inputStream != null;
            return new XSSFWorkbook(inputStream);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }
}
