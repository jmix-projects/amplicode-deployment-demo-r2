package io.jmix2mvp.petclinic.entity;

import org.springframework.content.commons.annotations.ContentId;

import javax.persistence.*;

@Entity
@Table(name = "file_ref")
public class FileRef {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ContentId
    @Column(name = "content_id")
    private String contentId;

    @Column(name = "type")
    private String type;

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}