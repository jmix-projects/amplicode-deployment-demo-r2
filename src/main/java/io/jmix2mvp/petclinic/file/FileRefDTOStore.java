package io.jmix2mvp.petclinic.file;

import org.springframework.content.commons.repository.ContentStore;
import org.springframework.content.rest.StoreRestResource;

@StoreRestResource(path = "files")
public interface FileRefDTOStore extends ContentStore<FileRefDTO, String> {
}
