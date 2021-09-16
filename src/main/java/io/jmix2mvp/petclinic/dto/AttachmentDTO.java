package io.jmix2mvp.petclinic.dto;

public class AttachmentDTO extends BaseDTO {
    private String name;
    private String url;
    private VisitDTO visit;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public VisitDTO getVisit() {
        return visit;
    }

    public void setVisit(VisitDTO visit) {
        this.visit = visit;
    }
}
