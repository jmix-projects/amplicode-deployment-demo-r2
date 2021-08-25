package io.jmix2mvp.petclinic.dto;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;

public class VisitDTO extends BaseDTO {
    private OffsetDateTime visitStart;
    private LocalDateTime visitEnd;
    private String description;

    public OffsetDateTime getVisitStart() {
        return visitStart;
    }

    public void setVisitStart(OffsetDateTime visitStart) {
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
