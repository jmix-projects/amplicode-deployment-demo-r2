package io.jmix2mvp.petclinic.graphql;

import io.jmix2mvp.petclinic.dto.PetDTO;
import io.jmix2mvp.petclinic.dto.PetInputDTO;
import io.jmix2mvp.petclinic.entity.Pet;
import io.jmix2mvp.petclinic.repository.PetRepository;
import org.modelmapper.ModelMapper;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static io.jmix2mvp.petclinic.Authorities.ADMIN;
import static io.jmix2mvp.petclinic.Authorities.VETERINARIAN;

@Controller
public class PetController {
    private final PetRepository crudRepository;
    private final ModelMapper mapper;
    @PersistenceContext
    private EntityManager entityManager;

    public PetController(PetRepository crudRepository, ModelMapper mapper) {
        this.crudRepository = crudRepository;
        this.mapper = mapper;
    }

    @Secured({ADMIN, VETERINARIAN})
    @QueryMapping(name = "pet")
    @Transactional
    public PetDTO findById(@Argument Long id) {
        return crudRepository.findById(id)
                .map(e -> mapper.map(e, PetDTO.class))
                .orElse(null);
    }

    @Secured({ADMIN, VETERINARIAN})
    @QueryMapping(name = "petList")
    @Transactional
    public List<PetDTO> findAll() {
        return crudRepository.findAll().stream()
                .map(e -> mapper.map(e, PetDTO.class))
                .collect(Collectors.toList());
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'VETERINARIAN')")
    @MutationMapping(name = "update_Pet")
    @Transactional
    public PetDTO update(@Argument PetInputDTO input, @Argument LocalDate localDate) {
        if (input.getId() != null) {
            if (!crudRepository.existsById(input.getId())) {
                throw new ResourceNotFoundException(
                        String.format("Unable to find entity by id: %s ", input.getId()));
            }
        }
        Pet entity = new Pet();
        mapper.map(input, entity);
        entity = crudRepository.saveAndFlush(entity);

        entityManager.refresh(entity);

        return mapper.map(entity, PetDTO.class);
    }

    @Secured({ADMIN, VETERINARIAN})
    @MutationMapping(name = "delete_Pet")
    @Transactional
    public void delete(@Argument Long id) {
        Pet entity = crudRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Unable to find entity by id: %s ", id)));

        crudRepository.delete(entity);
    }
}
