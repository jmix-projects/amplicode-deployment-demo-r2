package io.jmix2mvp.petclinic.repository;

import io.jmix2mvp.petclinic.entity.FileRef;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path="files", collectionResourceRel="files")
public interface FileRefRepository extends JpaRepository<FileRef, Long> {
}