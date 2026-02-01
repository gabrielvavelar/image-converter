package io.github.gabrielvavelar.image_converter_api.controller;

import io.github.gabrielvavelar.image_converter_api.dto.ImageConversionRequestDTO;
import io.github.gabrielvavelar.image_converter_api.dto.ImageConversionsResponseDTO;
import io.github.gabrielvavelar.image_converter_api.enums.ImageFormat;
import io.github.gabrielvavelar.image_converter_api.service.ImageConversionService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.InputStream;
import java.util.Map;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ImageConversionController {
    private final ImageConversionService service;

    @PostMapping
    public ResponseEntity<ImageConversionsResponseDTO> convertImage(
            @ModelAttribute ImageConversionRequestDTO request) {

        return ResponseEntity.accepted().body(service.convertImage(request));
    }

    @GetMapping()
    public ResponseEntity<InputStreamResource> download(
            @RequestParam UUID id,
            @RequestParam ImageFormat format,
            @RequestParam String originalName) {

        InputStream image = service.loadConvertedImage(id, format);

        String cleanName = originalName.split("\\.")[0];
        String finalFileName = cleanName + "." + format.name().toLowerCase();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + finalFileName + "\"")
                .contentType(MediaType.parseMediaType("image/" + format.name().toLowerCase()))
                .body(new InputStreamResource(image));
    }

    @GetMapping("/{id}/status")
    public ResponseEntity<Map<String, String>> getStatus(@PathVariable UUID id) {
        return ResponseEntity.ok(Map.of("status", service.getStatus(id)));
    }

}
