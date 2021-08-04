package io.jmix2mvp.petclinic.gql;

import com.cosium.spring.data.jpa.entity.graph.domain.EntityGraph;
import io.jmix2mvp.petclinic.dto.PetDTO;
import io.jmix2mvp.petclinic.entity.Pet;
import io.jmix2mvp.petclinic.mapper.Mapper;
import io.jmix2mvp.petclinic.repository.PetRepository;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLEnvironment;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.execution.ResolutionEnvironment;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@GraphQLApi
@Service
public class PetService {
    private final PetRepository petRepository;
    private final Mapper mapper;
    private final EntityGraphs entityGraphs;

    public PetService(PetRepository petRepository, Mapper mapper, EntityGraphs entityGraphs) {
        this.petRepository = petRepository;
        this.mapper = mapper;
        this.entityGraphs = entityGraphs;
    }

    @Secured("ROLE_ADMIN")
    @GraphQLQuery(name = "pet")
    public PetDTO findById(@GraphQLArgument(name = "id") Long id,
                           @GraphQLEnvironment ResolutionEnvironment environment) {
        EntityGraph entityGraph = entityGraphs.getEntityGraph(Pet.class, environment);
        return petRepository.findById(id, entityGraph)
                .map(this::mapToDTO)
                .orElse(null);
    }

    @Secured("ROLE_ADMIN")
    @GraphQLQuery(name = "petList")
    public List<PetDTO> findAll(@GraphQLEnvironment ResolutionEnvironment environment) {
        EntityGraph entityGraph = entityGraphs.getEntityGraph(Pet.class, environment);
        return StreamSupport.stream(petRepository.findAll(entityGraph).spliterator(), false)
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    protected PetDTO mapToDTO(Pet pet) {
        return mapper.map(pet);
    }
}
