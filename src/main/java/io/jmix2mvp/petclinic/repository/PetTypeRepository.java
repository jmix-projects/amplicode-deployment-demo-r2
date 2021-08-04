package io.jmix2mvp.petclinic.repository;

import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaRepository;
import io.jmix2mvp.petclinic.entity.PetType;

public interface PetTypeRepository extends EntityGraphJpaRepository<PetType, Long> {
}