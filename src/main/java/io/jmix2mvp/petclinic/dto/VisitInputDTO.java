package io.jmix2mvp.petclinic.dto;

import java.time.LocalDateTime;

public class VisitInputDTO extends BaseDTO {
    private ReferenceInputDTO pet;
    private LocalDateTime visitStart;
    private LocalDateTime visitEnd;
    private String description;

    public ReferenceInputDTO getPet() {
        return pet;
    }

    public void setPet(ReferenceInputDTO pet) {
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
}
