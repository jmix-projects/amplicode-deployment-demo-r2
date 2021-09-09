package io.jmix2mvp.petclinic.cassandra.entity;

import org.springframework.data.annotation.Id;

public class CBaseEntity {
    @Id
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}