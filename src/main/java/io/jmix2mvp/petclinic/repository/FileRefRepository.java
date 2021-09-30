package io.jmix2mvp.petclinic.repository;

import io.jmix2mvp.petclinic.entity.FileRef;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRefRepository extends JpaRepository<FileRef, Long> {
}