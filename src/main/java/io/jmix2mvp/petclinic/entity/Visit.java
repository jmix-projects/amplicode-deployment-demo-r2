package io.jmix2mvp.petclinic.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "visit")
public class Visit extends BaseEntity {
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id", nullable = false)
    private Pet pet;

    @Column(name = "visit_start", nullable = false)
    private LocalDateTime visitStart;

    @Column(name = "visit_end", nullable = false)
    private LocalDateTime visitEnd;

    @Column(name = "description", length = 4000)
    private String description;

    @OneToMany(mappedBy = "visit", cascade = CascadeType.ALL)
    private List<Attachment> attachments;

    @Enumerated(EnumType.STRING)
    @Column(name = "state", nullable = false)
    private VisitState state;

    public VisitState getState() {
        return state;
    }

    public void setState(VisitState state) {
        this.state = state;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getVisitEnd() {
        return visitEnd;
    }

    public void setVisitEnd(LocalDateTime visitEnd) {
        this.visitEnd = visitEnd;
    }

    public LocalDateTime getVisitStart() {
        return visitStart;
    }

    public void setVisitStart(LocalDateTime visitStart) {
        this.visitStart = visitStart;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }
}