package io.jmix2mvp.petclinic.repository;

import io.jmix2mvp.petclinic.entity.Pet;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    @Query("select p from Visit v left join v.pet p where v.id = ?1")
    Optional<Pet> findPetByVisit(Long visitId);

    @Query("select p from Pet p where p.owner.id = ?1")
    List<Pet> findPetsByOwner(Long ownerId, Pageable pageable);
}