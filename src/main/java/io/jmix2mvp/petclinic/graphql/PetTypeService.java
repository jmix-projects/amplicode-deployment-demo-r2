package io.jmix2mvp.petclinic.graphql;

import io.jmix2mvp.petclinic.dto.PetTypeDTO;
import io.jmix2mvp.petclinic.dto.PetTypeInputDTO;
import io.jmix2mvp.petclinic.entity.PetType;
import io.jmix2mvp.petclinic.mapper.Mapper;
import io.jmix2mvp.petclinic.repository.PetTypeRepository;
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
public class PetTypeService {
    private final PetTypeRepository crudRepository;
    private final Mapper mapper;

    public PetTypeService(PetTypeRepository petTypeRepository, Mapper mapper) {
        this.crudRepository = petTypeRepository;
        this.mapper = mapper;
    }

    @Secured("ROLE_ADMIN")
    @GraphQLQuery(name = "petType")
    @Transactional
    public PetTypeDTO findById(@GraphQLArgument(name = "id") Long id) {
        return crudRepository.findById(id)
                .map(mapper::mapToDTO)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Unable to find entity by id: %s ", id)));
    }

    @Secured("ROLE_ADMIN")
    @GraphQLQuery(name = "petTypeList")
    @Transactional
    public List<PetTypeDTO> findAll(@GraphQLArgument(name = "page") Pageable pageable) {
        return crudRepository.findAll(pageable).stream()
                .map(mapper::mapToDTO)
                .collect(Collectors.toList());
    }

    @Secured("ROLE_ADMIN")
    @GraphQLMutation(name = "update_PetType")
    @Transactional
    public PetTypeDTO update(PetTypeInputDTO input) {
        if (input.getId() != null) {
            if (!crudRepository.existsById(input.getId())) {
                throw new ResourceNotFoundException(
                        String.format("Unable to find entity by id: %s ", input.getId()));
            }
        }

        PetType entity = new PetType();
        mapper.mapFromDTO(input, entity);
        entity = crudRepository.save(entity);

        return mapper.mapToDTO(entity);
    }

    @Secured("ROLE_ADMIN")
    @GraphQLMutation(name = "delete_PetType")
    @Transactional
    public void delete(@GraphQLNonNull Long id) {
        PetType entity = crudRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Unable to find entity by id: %s ", id)));

        crudRepository.delete(entity);
    }
}
