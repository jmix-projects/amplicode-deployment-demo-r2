package io.jmix2mvp.petclinic.repository;

import io.jmix2mvp.petclinic.entity.Veterinarian;
import io.jmix2mvp.petclinic.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface VeterinarianRepository extends JpaRepository<Veterinarian, Long>, QuerydslPredicateExecutor<Veterinarian> {
}