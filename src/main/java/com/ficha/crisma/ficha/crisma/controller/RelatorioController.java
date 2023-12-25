package com.ficha.crisma.ficha.crisma.controller;

import com.ficha.crisma.ficha.crisma.service.RelatorioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Response;
import software.amazon.awssdk.services.s3.model.S3Object;

import java.io.ByteArrayInputStream;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/relatorios")
@RequiredArgsConstructor
public class RelatorioController {

    private static final String BUCKET = "arquivos-catequese";

    private final S3Client s3Client;
    private final RelatorioService service;

    @GetMapping("/list")
    public ResponseEntity<List<String>> listFiles() {
        ListObjectsV2Request request = ListObjectsV2Request.builder().bucket(BUCKET).build();
        ListObjectsV2Response response = this.s3Client.listObjectsV2(request);
        return ResponseEntity.ok(response.contents().stream().map(S3Object::key).toList());
    }

    @GetMapping("/")
    public void start(@RequestParam(value = "fileName") String fileName) {
        try {
            String key = "input/" + fileName;
            GetObjectRequest objectRequest = GetObjectRequest
                    .builder()
                    .bucket(BUCKET)
                    .key(key)
                    .build();
            ResponseBytes<GetObjectResponse> responseBytes = this.s3Client.getObjectAsBytes(objectRequest);
            this.service.process(new ByteArrayInputStream(responseBytes.asByteArray()));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

}
