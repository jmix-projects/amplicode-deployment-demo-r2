package io.jmix2mvp.petclinic.file;

import org.springframework.content.fs.store.FilesystemContentStore;
import org.springframework.content.rest.StoreRestResource;

@StoreRestResource(path = "files_dto")
public interface FileRefDTOStore extends FilesystemContentStore<FileRefDTO, String> {
}
