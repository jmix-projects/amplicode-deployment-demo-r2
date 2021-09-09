package io.jmix2mvp.petclinic.graphql;

import io.jmix2mvp.petclinic.dto.PetDTO;
import io.jmix2mvp.petclinic.dto.PetInputDTO;
import io.jmix2mvp.petclinic.entity.Pet;
import io.jmix2mvp.petclinic.repository.PetRepository;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLNonNull;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

import static io.jmix2mvp.petclinic.Authorities.ADMIN;
import static io.jmix2mvp.petclinic.Authorities.VETERINARIAN;

@GraphQLApi
@Service
public class PetService {
    private final PetRepository crudRepository;
    private final ModelMapper mapper;
    @PersistenceContext
    private EntityManager entityManager;

    public PetService(PetRepository crudRepository, ModelMapper mapper) {
        this.crudRepository = crudRepository;
        this.mapper = mapper;
    }

    @Secured({ADMIN, VETERINARIAN})
    @GraphQLQuery(name = "pet")
    @Transactional
    public PetDTO findById(@GraphQLArgument(name = "id") Long id) {
        return crudRepository.findById(id)
                .map(e -> mapper.map(e, PetDTO.class))
                .orElse(null);
    }

    @Secured({ADMIN, VETERINARIAN})
    @GraphQLQuery(name = "petList")
    @Transactional
    public List<PetDTO> findAll() {
        return crudRepository.findAll().stream()
                .map(e -> mapper.map(e, PetDTO.class))
                .collect(Collectors.toList());
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'VETERINARIAN')")
    @GraphQLMutation(name = "update_Pet")
    @Transactional
    public PetDTO update(PetInputDTO input) {
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
    @GraphQLMutation(name = "delete_Pet")
    @Transactional
    public void delete(@GraphQLNonNull Long id) {
        Pet entity = crudRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Unable to find entity by id: %s ", id)));

        crudRepository.delete(entity);
    }
}
