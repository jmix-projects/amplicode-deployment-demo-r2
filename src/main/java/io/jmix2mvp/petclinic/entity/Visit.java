package io.jmix2mvp.petclinic.entity;

import com.querydsl.core.annotations.QueryInit;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "visit")
public class Visit extends BaseEntity {
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id", nullable = false)
    @QueryInit("*.*")
    private Pet pet;

    @Column(name = "visit_start", nullable = false)
    private LocalDateTime visitStart;

    @Column(name = "description", length = 4000)
    private String description;

    @OneToMany(mappedBy = "visit", cascade = CascadeType.ALL)
    private List<Attachment> attachments;

    @Enumerated(EnumType.STRING)
    @Column(name = "state", nullable = false)
    private VisitState state;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "veterinarian_id")
    @QueryInit("*.*")
    private Veterinarian veterinarian;

    public Veterinarian getVeterinarian() {
        return veterinarian;
    }

    public void setVeterinarian(Veterinarian veterinarian) {
        this.veterinarian = veterinarian;
    }

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