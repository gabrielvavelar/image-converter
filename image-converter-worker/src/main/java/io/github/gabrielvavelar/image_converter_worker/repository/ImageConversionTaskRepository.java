package io.github.gabrielvavelar.image_converter_worker.repository;

import io.github.gabrielvavelar.image_converter_worker.model.ImageConversionTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ImageConversionTaskRepository extends JpaRepository<ImageConversionTask, UUID> {
}
