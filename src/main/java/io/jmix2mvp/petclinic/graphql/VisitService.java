package io.jmix2mvp.petclinic.graphql;

import io.jmix2mvp.petclinic.dto.PetDTO;
import io.jmix2mvp.petclinic.dto.VisitDTO;
import io.jmix2mvp.petclinic.dto.VisitInputDTO;
import io.jmix2mvp.petclinic.entity.Visit;
import io.jmix2mvp.petclinic.mapper.DTOMapper;
import io.jmix2mvp.petclinic.repository.PetRepository;
import io.jmix2mvp.petclinic.repository.VisitRepository;
import io.leangen.graphql.annotations.*;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@GraphQLApi
@Service
public class VisitService {
    private final VisitRepository visitRepository;
    private final PetRepository petRepository;
    private final DTOMapper mapper;

    public VisitService(VisitRepository visitRepository, PetRepository petRepository, DTOMapper mapper) {
        this.visitRepository = visitRepository;
        this.petRepository = petRepository;
        this.mapper = mapper;
    }

    @Secured("ROLE_ADMIN")
    @GraphQLQuery(name = "visit")
    @Transactional
    public VisitDTO findById(@GraphQLArgument(name = "id") Long id) {
        return visitRepository.findById(id)
                .map(mapper::visitToDTO)
                .orElse(null);
    }

    @Secured("ROLE_ADMIN")
    @GraphQLQuery(name = "visitList")
    @Transactional
    public List<VisitDTO> findAll(@GraphQLArgument(name = "page") Pageable pageable) {
        return visitRepository.findAll(pageable).stream()
                .map(mapper::visitToDTO)
                .collect(Collectors.toList());
    }

    @Secured("ROLE_ADMIN")
    @GraphQLQuery(name = "pet")
    @Transactional
    public PetDTO getPet(@GraphQLContext VisitDTO visitDTO) {
        return petRepository.findPetByVisit(visitDTO.getId())
                .map(mapper::petToDTO)
                .orElse(null);
    }

    @Secured("ROLE_ADMIN")
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
        mapper.visitDTOToEntity(input, entity);
        entity = visitRepository.save(entity);

        return mapper.visitToDTO(entity);
    }

    @Secured("ROLE_ADMIN")
    @GraphQLMutation(name = "delete_Visit")
    @Transactional
    public void delete(@GraphQLNonNull Long id) {
        Visit entity = visitRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Unable to find entity by id: %s ", id)));

        visitRepository.delete(entity);
    }
}
