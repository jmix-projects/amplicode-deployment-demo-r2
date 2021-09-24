package io.jmix2mvp.petclinic.dto;

import io.jmix2mvp.petclinic.entity.VisitState;

import java.time.LocalDateTime;
import java.util.List;

public class VisitDTO extends BaseDTO {
    private LocalDateTime visitStart;
    private String description;
    private List<AttachmentDTO> attachments;
    private VisitState visitState;
    private VeterinarianDTO veterinarian;

    public LocalDateTime getVisitStart() {
        return visitStart;
    }

    public void setVisitStart(LocalDateTime visitStart) {
        this.visitStart = visitStart;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<AttachmentDTO> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<AttachmentDTO> attachments) {
        this.attachments = attachments;
    }

    public VisitState getVisitState() {
        return visitState;
    }

    public void setVisitState(VisitState visitState) {
        this.visitState = visitState;
    }

    public VeterinarianDTO getVeterinarian() {
        return veterinarian;
    }

    public void setVeterinarian(VeterinarianDTO veterinarian) {
        this.veterinarian = veterinarian;
    }
}
