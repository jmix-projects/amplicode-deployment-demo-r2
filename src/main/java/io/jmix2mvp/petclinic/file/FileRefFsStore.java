package io.jmix2mvp.petclinic.file;

import io.jmix2mvp.petclinic.entity.FileRef;
import org.springframework.content.commons.repository.ContentStore;
import org.springframework.content.fs.store.FilesystemContentStore;
import org.springframework.content.rest.StoreRestResource;

@StoreRestResource(path = "files_fs")
public interface FileRefFsStore extends FilesystemContentStore<FileRef, String> {
}
