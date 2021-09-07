package io.jmix2mvp.petclinic.repository;

import io.jmix2mvp.petclinic.entity.Visit;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface VisitRepository extends JpaRepository<Visit, Long> {
    @Query("select v from Visit v where v.visitStart <= ?2 and v.visitEnd >= ?1")
    List<Visit> findByDateRange(LocalDateTime from, LocalDateTime to, Pageable pageable);
}