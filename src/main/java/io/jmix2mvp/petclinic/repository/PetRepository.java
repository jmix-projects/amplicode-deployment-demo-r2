package io.jmix2mvp.petclinic.repository;

import io.jmix2mvp.petclinic.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Long> {
}