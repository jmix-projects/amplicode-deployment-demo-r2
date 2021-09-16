package io.jmix2mvp.petclinic.dto;

public class AttachmentInputDTO extends BaseDTO {
    private String name;
    private String url;
    private VisitInputDTO visit;

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

    public VisitInputDTO getVisit() {
        return visit;
    }

    public void setVisit(VisitInputDTO visit) {
        this.visit = visit;
    }
}
