package io.jmix2mvp.petclinic.entity;

import javax.persistence.*;

@Table(name = "attachment")
@Entity
public class Attachment extends BaseEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "url")
    private String url;

    @ManyToOne(optional = false)
    @JoinColumn(name = "visit_id", nullable = false)
    private Visit visit;

    public Visit getVisit() {
        return visit;
    }

    public void setVisit(Visit visit) {
        this.visit = visit;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}