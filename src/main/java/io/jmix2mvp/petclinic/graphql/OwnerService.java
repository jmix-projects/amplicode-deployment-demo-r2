package io.jmix2mvp.petclinic.graphql;

import io.jmix2mvp.petclinic.dto.OwnerDTO;
import io.jmix2mvp.petclinic.dto.OwnerInputDTO;
import io.jmix2mvp.petclinic.entity.Owner;
import io.jmix2mvp.petclinic.repository.OwnerRepository;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLNonNull;
import io.leangen.graphql.annotations.GraphQLQuery;
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
public class OwnerService {
    private final OwnerRepository crudRepository;
    private final ModelMapper mapper;

    public OwnerService(OwnerRepository crudRepository, ModelMapper mapper) {
        this.crudRepository = crudRepository;
        this.mapper = mapper;
    }

    @Secured({ADMIN, VETERINARIAN})
    @GraphQLQuery(name = "owner")
    @Transactional
    public OwnerDTO findById(@GraphQLArgument(name = "id") Long id) {
        return crudRepository.findById(id)
                .map(e -> mapper.map(e, OwnerDTO.class))
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Unable to find entity by id: %s ", id)));
    }

    @Secured({ADMIN, VETERINARIAN})
    @GraphQLQuery(name = "ownerList")
    @Transactional
    public List<OwnerDTO> findAll() {
        return crudRepository.findAll().stream()
                .map(e -> mapper.map(e, OwnerDTO.class))
                .collect(Collectors.toList());
    }

    @Secured({ADMIN, VETERINARIAN})
    @GraphQLMutation(name = "update_Owner")
    @Transactional
    public OwnerDTO update(OwnerInputDTO input) {
        if (input.getId() != null) {
            if (!crudRepository.existsById(input.getId())) {
                throw new ResourceNotFoundException(
                        String.format("Unable to find entity by id: %s ", input.getId()));
            }
        }

        Owner entity = new Owner();
        mapper.map(input, entity);
        entity = crudRepository.save(entity);

        return mapper.map(entity, OwnerDTO.class);
    }

    @Secured({ADMIN, VETERINARIAN})
    @GraphQLMutation(name = "delete_Owner")
    @Transactional
    public void delete(@GraphQLNonNull Long id) {
        Owner entity = crudRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Unable to find entity by id: %s ", id)));

        crudRepository.delete(entity);
    }
}
