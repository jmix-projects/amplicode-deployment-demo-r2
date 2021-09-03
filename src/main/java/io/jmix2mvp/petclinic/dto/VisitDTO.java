package io.jmix2mvp.petclinic.dto;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Currency;

public class VisitDTO extends BaseDTO {
    private LocalDateTime visitStart;
    private LocalDateTime visitEnd;
    private String description;

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
