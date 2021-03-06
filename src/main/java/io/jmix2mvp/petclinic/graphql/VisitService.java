package io.jmix2mvp.petclinic.graphql;

import io.jmix2mvp.petclinic.dto.PetDTO;
import io.jmix2mvp.petclinic.dto.VisitDTO;
import io.jmix2mvp.petclinic.dto.VisitInputDTO;
import io.jmix2mvp.petclinic.entity.Visit;
import io.jmix2mvp.petclinic.repository.PetRepository;
import io.jmix2mvp.petclinic.repository.VisitRepository;
import io.leangen.graphql.annotations.*;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static io.jmix2mvp.petclinic.Authorities.ADMIN;
import static io.jmix2mvp.petclinic.Authorities.VETERINARIAN;

@GraphQLApi
@Service
public class VisitService {
    private final VisitRepository visitRepository;
    private final PetRepository petRepository;
    private final ModelMapper mapper;

    public VisitService(VisitRepository visitRepository, PetRepository petRepository, ModelMapper mapper) {
        this.visitRepository = visitRepository;
        this.petRepository = petRepository;
        this.mapper = mapper;
    }

    @Secured({ADMIN, VETERINARIAN})
    @GraphQLQuery(name = "visit")
    @Transactional
    public VisitDTO findById(@GraphQLArgument(name = "id") Long id) {
        return visitRepository.findById(id)
                .map(e -> mapper.map(e, VisitDTO.class))
                .orElse(null);
    }

    @Secured({ADMIN, VETERINARIAN})
    @GraphQLQuery(name = "visitList")
    @Transactional
    public List<VisitDTO> findAll() {
        return visitRepository.findAll().stream()
                .map(e -> mapper.map(e, VisitDTO.class))
                .collect(Collectors.toList());
    }

    @Secured({ADMIN, VETERINARIAN})
    @GraphQLQuery(name = "pet")
    @Transactional
    public PetDTO getPet(@GraphQLContext VisitDTO visitDTO) {
        return petRepository.findPetByVisit(visitDTO.getId())
                .map(e -> mapper.map(e, PetDTO.class))
                .orElse(null);
    }

    @Secured({ADMIN, VETERINARIAN})
    @GraphQLMutation(name = "update_Visit")
    @Transactional
    public VisitDTO update(VisitInputDTO input) {
        if (input.getId() != null) {
            if (!visitRepository.existsById(input.getId())) {
                throw new ResourceNotFoundException(
                        String.format("Unable to find entity by id: %s ", input.getId()));
            }
        }

        Visit entity = new Visit();
        mapper.map(input, entity);
        entity = visitRepository.save(entity);

        return mapper.map(entity, VisitDTO.class);
    }

    @Secured({ADMIN, VETERINARIAN})
    @GraphQLMutation(name = "delete_Visit")
    @Transactional
    public void delete(@GraphQLNonNull Long id) {
        Visit entity = visitRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Unable to find entity by id: %s ", id)));

        visitRepository.delete(entity);
    }
}
