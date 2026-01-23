package io.github.gabrielvavelar.image_converter_worker.service;

import io.github.gabrielvavelar.image_converter_worker.enums.ImageFormat;
import io.github.gabrielvavelar.image_converter_worker.exception.ImageConversionException;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class ImageConverterService {
    public byte[] convertImage(InputStream image, ImageFormat format) throws ImageConversionException {

        try {
            String targetFormat = format.name().toLowerCase();
            BufferedImage sourceImage = ImageIO.read(image);

            if (sourceImage == null) {
                throw new ImageConversionException("Invalid file.");
            }

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(sourceImage, targetFormat, outputStream);

            return outputStream.toByteArray();

        } catch (IOException e) {
           throw new ImageConversionException("Image Conversion Failed", e);
        }
    }
}
