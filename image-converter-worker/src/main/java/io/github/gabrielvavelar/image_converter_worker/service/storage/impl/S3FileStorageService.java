package io.github.gabrielvavelar.image_converter_worker.service.storage.impl;

import io.github.gabrielvavelar.image_converter_worker.enums.ImageFormat;
import io.github.gabrielvavelar.image_converter_worker.exception.S3ImageRetriveException;
import io.github.gabrielvavelar.image_converter_worker.exception.S3ImageUploadException;
import io.github.gabrielvavelar.image_converter_worker.service.storage.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.io.InputStream;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3FileStorageService implements FileStorageService {

    private final S3Client s3Client;

    @Value("${aws.s3.bucket}")
    private String bucketName;

    @Override
    public InputStream loadImage(UUID id, ImageFormat extension) {

        String key = "input/" + id + "." + extension.name().toLowerCase();

        try {
            GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                    .bucket(bucketName)
                    .key(key)
                    .build();

            return s3Client.getObject(getObjectRequest);

        } catch (S3Exception e) {
            throw new S3ImageRetriveException("Failed to retrieve image to S3",e);
        }
    }

    @Override
    public void storeImage(byte[] file, UUID id, ImageFormat extension) {
        String key = "output/" + id + "." + extension.name().toLowerCase();

        try {
            PutObjectRequest putOb = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(key)
                    .contentType("image/" + extension.name().toLowerCase())
                    .build();

            s3Client.putObject(putOb, RequestBody.fromBytes(file));

        } catch (S3Exception e) {
            throw new S3ImageUploadException("Failed to store image to S3", e);
        }

    }

}
