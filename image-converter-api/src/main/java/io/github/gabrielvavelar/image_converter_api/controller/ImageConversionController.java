package io.github.gabrielvavelar.image_converter_api.controller;

import io.github.gabrielvavelar.image_converter_api.dto.ImageConversionRequestDTO;
import io.github.gabrielvavelar.image_converter_api.enums.ImageFormat;
import io.github.gabrielvavelar.image_converter_api.service.ImageConversionService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.InputStream;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ImageConversionController {
    private final ImageConversionService service;

    @PostMapping
    public void convertImage(
            @ModelAttribute ImageConversionRequestDTO request) {

        service.convertImage(request);
    }

    @GetMapping()
    public ResponseEntity<InputStreamResource> download(
            @RequestParam UUID id,
            @RequestParam ImageFormat format) {

        InputStream image = service.loadConvertedImage(id, format);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("image/" + format.name().toLowerCase()))
                .body(new InputStreamResource(image));
    }

}
