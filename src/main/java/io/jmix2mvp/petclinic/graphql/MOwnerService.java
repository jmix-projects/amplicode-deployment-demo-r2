package io.jmix2mvp.petclinic.graphql;

import io.jmix2mvp.petclinic.dto.MOwnerDTO;
import io.jmix2mvp.petclinic.dto.MOwnerInputDTO;
import io.jmix2mvp.petclinic.dto.OwnerDTO;
import io.jmix2mvp.petclinic.dto.OwnerInputDTO;
import io.jmix2mvp.petclinic.entity.Owner;
import io.jmix2mvp.petclinic.mongodb.MOwnerRepository;
import io.jmix2mvp.petclinic.mongodb.entity.MOwner;
import io.jmix2mvp.petclinic.repository.OwnerRepository;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLNonNull;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static io.jmix2mvp.petclinic.Authorities.ADMIN;
import static io.jmix2mvp.petclinic.Authorities.VETERINARIAN;

@GraphQLApi
@Service
public class MOwnerService {
    private final MOwnerRepository crudRepository;
    private final ModelMapper mapper;

    public MOwnerService(MOwnerRepository crudRepository, ModelMapper mapper) {
        this.crudRepository = crudRepository;
        this.mapper = mapper;
    }

    @Secured({ADMIN, VETERINARIAN})
    @GraphQLQuery(name = "mOwner")
    @Transactional
    public MOwnerDTO findById(@GraphQLArgument(name = "id") String id) {
        return crudRepository.findById(id)
                .map(e -> mapper.map(e, MOwnerDTO.class))
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Unable to find entity by id: %s ", id)));
    }

    @Secured({ADMIN, VETERINARIAN})
    @GraphQLQuery(name = "mOwnerList")
    @Transactional
    public List<MOwnerDTO> findAll(@GraphQLArgument(name = "page") Pageable pageable) {
        return crudRepository.findAll(pageable).stream()
                .map(e -> mapper.map(e, MOwnerDTO.class))
                .collect(Collectors.toList());
    }

    @Secured({ADMIN, VETERINARIAN})
    @GraphQLMutation(name = "update_MOwner")
    @Transactional
    public MOwnerDTO update(MOwnerInputDTO input) {
        if (input.getId() != null) {
            if (!crudRepository.existsById(input.getId())) {
                throw new ResourceNotFoundException(
                        String.format("Unable to find entity by id: %s ", input.getId()));
            }
        }

        MOwner entity = new MOwner();
        mapper.map(input, entity);
        entity = crudRepository.save(entity);

        return mapper.map(entity, MOwnerDTO.class);
    }

    @Secured({ADMIN, VETERINARIAN})
    @GraphQLMutation(name = "delete_MOwner")
    @Transactional
    public void delete(@GraphQLNonNull String id) {
        MOwner entity = crudRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Unable to find entity by id: %s ", id)));

        crudRepository.delete(entity);
    }
}
