package io.github.gabrielvavelar.image_converter_worker.service.storage.impl;

import io.github.gabrielvavelar.image_converter_worker.enums.ImageFormat;
import io.github.gabrielvavelar.image_converter_worker.exception.S3ImageRetriveException;
import io.github.gabrielvavelar.image_converter_worker.service.storage.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;

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

        String key = "/input/" + id + "." + extension.name().toLowerCase();

        try {
            GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                    .bucket(bucketName)
                    .key(key)
                    .build();

            return s3Client.getObject(getObjectRequest);

        } catch (S3ImageRetriveException e) {
            throw new S3ImageRetriveException("Failed to retrieve image to S3",e);
        }
    }

}
