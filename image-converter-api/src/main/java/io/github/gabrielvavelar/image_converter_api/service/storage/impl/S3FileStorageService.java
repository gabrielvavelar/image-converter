package io.github.gabrielvavelar.image_converter_api.service.storage.impl;

import io.github.gabrielvavelar.image_converter_api.enums.ImageFormat;
import io.github.gabrielvavelar.image_converter_api.exception.ImageSaveException;
import io.github.gabrielvavelar.image_converter_api.exception.S3ImageRetriveException;
import io.github.gabrielvavelar.image_converter_api.exception.S3ImageUploadException;
import io.github.gabrielvavelar.image_converter_api.service.storage.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3FileStorageService implements FileStorageService {

    private final S3Client s3Client;

    @Value("${aws.s3.bucket}")
    private String bucketName;

    @Override
    public String saveImage(MultipartFile file, UUID id, ImageFormat extension){
        String key = "input/" + id + "." + extension.name().toLowerCase();

        try {
            PutObjectRequest putOb = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(key)
                    .contentType(file.getContentType())
                    .build();

            s3Client.putObject(putOb, RequestBody.fromInputStream(file.getInputStream(), file.getSize()));
        } catch (S3Exception e) {
            throw new S3ImageUploadException("Failed to upload image to S3", e);
        } catch (IOException e) {
            throw new ImageSaveException("Failed to save image", e);
        }

        return key;
    }

    @Override
    public InputStream loadImage(UUID id, ImageFormat extension) {

        String key = "output/" + id + "." + extension.name().toLowerCase();

        try {
            GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                    .bucket(bucketName)
                    .key(key)
                    .build();

            return s3Client.getObject(getObjectRequest);

        } catch (S3Exception e) {
            throw new S3ImageRetriveException("Failed to retrieve image from S3", e);
        }
    }
}
