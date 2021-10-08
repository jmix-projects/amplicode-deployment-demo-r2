package io.jmix2mvp.petclinic.dto;

import io.jmix2mvp.petclinic.entity.VisitState;

import java.time.LocalDateTime;
import java.util.List;

public class VisitInputDTO extends BaseDTO {
    private PetInputDTO pet;
    private LocalDateTime visitStart;
    private String description;
    private List<AttachmentInputDTO> attachments;
    private VisitState visitState;
    private VeterinarianInputDTO veterinarian;

    public PetInputDTO getPet() {
        return pet;
    }

    public void setPet(PetInputDTO pet) {
        this.pet = pet;
    }

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

    public VeterinarianInputDTO getVeterinarian() {
        return veterinarian;
    }

    public void setVeterinarian(VeterinarianInputDTO veterinarian) {
        this.veterinarian = veterinarian;
    }
}
