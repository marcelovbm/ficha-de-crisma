package com.ficha.crisma.ficha.crisma.config;

import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
@Configuration
public class JasperTemplateConfig {

    private static final String JASPER_TEMPLATE = "jasper/ficha_de_inscricao_de_crisma.jrxml";

    @Bean
    public JasperReport jasperReport() {
        try {
            InputStream inputStream = new ClassPathResource(JASPER_TEMPLATE).getInputStream();
            return JasperCompileManager.compileReport(inputStream);
        } catch (IOException | JRException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }
}
