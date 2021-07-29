package io.jmix2mvp.petclinic.repository;

import io.jmix2mvp.petclinic.entity.PetType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetTypeRepository extends JpaRepository<PetType, Long> {
}