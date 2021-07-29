package io.jmix2mvp.petclinic.repository;

import io.jmix2mvp.petclinic.entity.Pet;
import io.jmix2mvp.petclinic.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VisitRepository extends JpaRepository<Visit, Long> {
}