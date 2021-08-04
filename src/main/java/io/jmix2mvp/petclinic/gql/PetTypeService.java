package io.jmix2mvp.petclinic.gql;

import io.jmix2mvp.petclinic.dto.PetTypeDTO;
import io.jmix2mvp.petclinic.entity.PetType;
import io.jmix2mvp.petclinic.mapper.Mapper;
import io.jmix2mvp.petclinic.repository.PetTypeRepository;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@GraphQLApi
@Service
public class PetTypeService {
    private final PetTypeRepository petTypeRepository;
    private final Mapper mapper;

    public PetTypeService(PetTypeRepository petTypeRepository, Mapper mapper) {
        this.petTypeRepository = petTypeRepository;
        this.mapper = mapper;
    }

    @Secured("ROLE_ADMIN")
    @GraphQLQuery(name = "petType")
    public PetTypeDTO findById(@GraphQLArgument(name = "id") Long id) {
        return petTypeRepository.findById(id)
                .map(this::mapToDTO)
                .orElse(null);
    }

    @Secured("ROLE_ADMIN")
    @GraphQLQuery(name = "petTypeList")
    public List<PetTypeDTO> findAll() {
        return petTypeRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    protected PetTypeDTO mapToDTO(PetType petType) {
        return mapper.map(petType);
    }
}
