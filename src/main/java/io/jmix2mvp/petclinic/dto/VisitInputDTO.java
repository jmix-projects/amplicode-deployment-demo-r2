package io.jmix2mvp.petclinic.dto;

import io.jmix2mvp.petclinic.entity.VisitState;

import java.time.LocalDateTime;
import java.util.List;

public class VisitInputDTO extends BaseDTO {
    private PetDTO pet;
    private LocalDateTime visitStart;
    private LocalDateTime visitEnd;
    private String description;
    private List<AttachmentInputDTO> attachments;
    private VisitState visitState;

    public PetDTO getPet() {
        return pet;
    }

    public void setPet(PetDTO pet) {
        this.pet = pet;
    }

    public LocalDateTime getVisitStart() {
        return visitStart;
    }

    public void setVisitStart(LocalDateTime visitStart) {
        this.visitStart = visitStart;
    }

    public LocalDateTime getVisitEnd() {
        return visitEnd;
    }

    public void setVisitEnd(LocalDateTime visitEnd) {
        this.visitEnd = visitEnd;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<AttachmentInputDTO> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<AttachmentInputDTO> attachments) {
        this.attachments = attachments;
    }

    public VisitState getVisitState() {
        return visitState;
    }

    public void setVisitState(VisitState visitState) {
        this.visitState = visitState;
    }
}
