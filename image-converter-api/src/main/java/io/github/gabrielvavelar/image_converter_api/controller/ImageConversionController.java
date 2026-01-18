package io.github.gabrielvavelar.image_converter_api.controller;

import io.github.gabrielvavelar.image_converter_api.dto.ImageConversionRequestDTO;
import io.github.gabrielvavelar.image_converter_api.service.ImageConversionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ImageConversionController {
    private final ImageConversionService service;

    @PostMapping
    public void convertImage(
            @ModelAttribute ImageConversionRequestDTO request) {

        service.convertImage(request);
    }
}
