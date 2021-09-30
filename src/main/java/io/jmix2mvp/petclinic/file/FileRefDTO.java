package io.jmix2mvp.petclinic.file;

import org.springframework.content.commons.annotations.ContentId;

public class FileRefDTO {
    @ContentId
    private String contentId;

    public FileRefDTO() {
    }

    public FileRefDTO(String contentId) {
        this.contentId = contentId;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }
}
