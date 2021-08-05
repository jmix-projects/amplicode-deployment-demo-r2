package io.jmix2mvp.petclinic.graphql;

import io.jmix2mvp.petclinic.dto.PetDTO;
import io.jmix2mvp.petclinic.dto.PetInputDTO;
import io.jmix2mvp.petclinic.entity.Pet;
import io.jmix2mvp.petclinic.mapper.Mapper;
import io.jmix2mvp.petclinic.repository.PetRepository;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLNonNull;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@GraphQLApi
@Service
public class PetService {
    private final PetRepository crudRepository;
    private final Mapper mapper;

    public PetService(PetRepository crudRepository, Mapper mapper) {
        this.crudRepository = crudRepository;
        this.mapper = mapper;
    }

    @Secured("ROLE_ADMIN")
    @GraphQLQuery(name = "pet")
    @Transactional
    public PetDTO findById(@GraphQLArgument(name = "id") Long id) {
        return crudRepository.findById(id)
                .map(mapper::mapToDTO)
                .orElse(null);
    }

    @Secured("ROLE_ADMIN")
    @GraphQLQuery(name = "petList")
    @Transactional
    public List<PetDTO> findAll(@GraphQLArgument(name = "page") Pageable pageable) {
        return crudRepository.findAll(pageable).stream()
                .map(mapper::mapToDTO)
                .collect(Collectors.toList());
    }

    @Secured("ROLE_ADMIN")
    @GraphQLMutation(name = "update_Pet")
    @Transactional
    public PetDTO update(PetInputDTO input) {
        if (input.getId() != null) {
            if (crudRepository.existsById(input.getId())) {
                throw new ResourceNotFoundException(
                        String.format("Unable to find entity by id: %s ", input.getId()));
            }
        }

        Pet entity = new Pet();
        mapper.mapFromDTO(input, entity);
        entity = crudRepository.save(entity);

        return mapper.mapToDTO(entity);
    }

    @Secured("ROLE_ADMIN")
    @GraphQLMutation(name = "delete_Pet")
    @Transactional
    public void delete(@GraphQLNonNull Long id) {
        Pet entity = crudRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Unable to find entity by id: %s ", id)));

        crudRepository.delete(entity);
    }
}
