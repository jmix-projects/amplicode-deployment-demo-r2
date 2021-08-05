package io.jmix2mvp.petclinic.graphql;

import io.jmix2mvp.petclinic.dto.OwnerDTO;
import io.jmix2mvp.petclinic.dto.OwnerInputDTO;
import io.jmix2mvp.petclinic.entity.Owner;
import io.jmix2mvp.petclinic.mapper.Mapper;
import io.jmix2mvp.petclinic.repository.OwnerRepository;
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
public class OwnerService {
    private final OwnerRepository crudRepository;
    private final Mapper mapper;

    public OwnerService(OwnerRepository crudRepository, Mapper mapper) {
        this.crudRepository = crudRepository;
        this.mapper = mapper;
    }

    @Secured("ROLE_ADMIN")
    @GraphQLQuery(name = "owner")
    @Transactional
    public OwnerDTO findById(@GraphQLArgument(name = "id") Long id) {
        return crudRepository.findById(id)
                .map(mapper::mapToDTO)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Unable to find entity by id: %s ", id)));
    }

    @Secured("ROLE_ADMIN")
    @GraphQLQuery(name = "ownerList")
    @Transactional
    public List<OwnerDTO> findAll(@GraphQLArgument(name = "page") Pageable pageable) {
        return crudRepository.findAll(pageable).stream()
                .map(mapper::mapToDTO)
                .collect(Collectors.toList());
    }

    @Secured("ROLE_ADMIN")
    @GraphQLMutation(name = "update_Owner")
    @Transactional
    public OwnerDTO update(OwnerInputDTO input) {
        if (input.getId() != null) {
            if (crudRepository.existsById(input.getId())) {
                throw new ResourceNotFoundException(
                        String.format("Unable to find entity by id: %s ", input.getId()));
            }
        }

        Owner entity = new Owner();
        mapper.mapFromDTO(input, entity);
        entity = crudRepository.save(entity);

        return mapper.mapToDTO(entity);
    }

    @Secured("ROLE_ADMIN")
    @GraphQLMutation(name = "delete_Owner")
    @Transactional
    public void delete(@GraphQLNonNull Long id) {
        Owner entity = crudRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Unable to find entity by id: %s ", id)));

        crudRepository.delete(entity);
    }
}
