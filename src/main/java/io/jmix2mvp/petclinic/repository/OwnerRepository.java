package io.jmix2mvp.petclinic.repository;

import io.jmix2mvp.petclinic.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
}