package io.jmix2mvp.petclinic.repository;

import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaRepository;
import io.jmix2mvp.petclinic.entity.Pet;

public interface PetRepository extends EntityGraphJpaRepository<Pet, Long> {
}