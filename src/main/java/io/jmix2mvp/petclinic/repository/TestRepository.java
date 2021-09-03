package io.jmix2mvp.petclinic.repository;

import io.jmix2mvp.petclinic.entity.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<TestEntity, Long> {
}
