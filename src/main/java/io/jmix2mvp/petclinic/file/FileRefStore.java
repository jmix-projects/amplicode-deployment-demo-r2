package io.jmix2mvp.petclinic.file;

import io.jmix2mvp.petclinic.entity.FileRef;
import org.springframework.content.commons.repository.ContentStore;
import org.springframework.content.rest.StoreRestResource;

public interface FileRefStore extends ContentStore<FileRef, String> {
}
