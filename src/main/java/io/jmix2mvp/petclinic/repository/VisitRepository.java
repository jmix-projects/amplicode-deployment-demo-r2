package io.jmix2mvp.petclinic.repository;

import io.jmix2mvp.petclinic.entity.Visit;
import io.jmix2mvp.petclinic.entity.VisitState;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface VisitRepository extends JpaRepository<Visit, Long> {
    @Query("select v from Visit v where v.visitStart <= ?2 and v.visitEnd >= ?1 and v.state = ?3")
    List<Visit> findByDateRangeAndState(LocalDateTime from, LocalDateTime to, VisitState visitState, Pageable pageable);

    @Query("select v from Visit v where v.visitStart <= ?2 and v.visitEnd >= ?1")
    List<Visit> findByDateRange(LocalDateTime from, LocalDateTime to, Pageable pageable);
}