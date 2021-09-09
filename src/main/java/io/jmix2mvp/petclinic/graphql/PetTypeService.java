package io.jmix2mvp.petclinic.graphql;

import io.jmix2mvp.petclinic.dto.PetTypeDTO;
import io.jmix2mvp.petclinic.dto.PetTypeInputDTO;
import io.jmix2mvp.petclinic.entity.PetType;
import io.jmix2mvp.petclinic.repository.PetTypeRepository;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLNonNull;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static io.jmix2mvp.petclinic.Authorities.ADMIN;
import static io.jmix2mvp.petclinic.Authorities.VETERINARIAN;

@GraphQLApi
@Service
@Validated
public class PetTypeService {
    private final PetTypeRepository crudRepository;
    private final ModelMapper mapper;

    public PetTypeService(PetTypeRepository petTypeRepository, ModelMapper mapper) {
        this.crudRepository = petTypeRepository;
        this.mapper = mapper;
    }

    @Secured({ADMIN, VETERINARIAN})
    @GraphQLQuery(name = "petType")
    @Transactional
    public PetTypeDTO findById(@GraphQLArgument(name = "id") Long id) {
        return crudRepository.findById(id)
                .map(e -> mapper.map(e, PetTypeDTO.class))
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Unable to find entity by id: %s ", id)));
    }

    @Secured({ADMIN, VETERINARIAN})
    @GraphQLQuery(name = "petTypeList")
    @Transactional
    public List<PetTypeDTO> findAll() {
        return crudRepository.findAll().stream()
                .map(e -> mapper.map(e, PetTypeDTO.class))
                .collect(Collectors.toList());
    }

    @Secured({ADMIN, VETERINARIAN})
    @GraphQLMutation(name = "update_PetType")
    @Transactional
    public PetTypeDTO update(@Valid PetTypeInputDTO input) {
        if (input.getId() != null) {
            if (!crudRepository.existsById(input.getId())) {
                throw new ResourceNotFoundException(
                        String.format("Unable to find entity by id: %s ", input.getId()));
            }
        }

        PetType entity = new PetType();
        mapper.map(input, entity);
        entity = crudRepository.save(entity);

        return mapper.map(entity, PetTypeDTO.class);
    }

    @Secured({ADMIN, VETERINARIAN})
    @GraphQLMutation(name = "delete_PetType")
    @Transactional
    public void delete(@GraphQLNonNull Long id) {
        PetType entity = crudRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Unable to find entity by id: %s ", id)));

        crudRepository.delete(entity);
    }
}
