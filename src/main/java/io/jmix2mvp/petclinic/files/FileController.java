package io.jmix2mvp.petclinic.files;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Controller
@RequestMapping("files/")
public class FileController {

    @Value("${storage.location}")
    private String storageLocation;

    @PostMapping
    public @ResponseBody
    String uploadFile(@RequestParam("file") MultipartFile file) throws Exception {
        String filename = file.getResource().getFilename();

        File targetFile = new File(storageLocation, filename);
        //noinspection ResultOfMethodCallIgnored
        targetFile.getParentFile().mkdirs();

        while (targetFile.exists()) {
            targetFile = new File(storageLocation, filename + UUID.randomUUID());
        }

        file.transferTo(targetFile.getAbsoluteFile());

        return targetFile.getName();
    }

    @GetMapping("{name}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String name) throws Exception {
        File file = new File(storageLocation, name);
        if (!file.exists())
            throw new RuntimeException("File does not exist");

        Resource resource = new FileSystemResource(file);
        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);

    }
}
