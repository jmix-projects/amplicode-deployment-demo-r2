package io.jmix2mvp.petclinic.gql;

import com.cosium.spring.data.jpa.entity.graph.domain.EntityGraph;
import io.jmix2mvp.petclinic.dto.VisitDTO;
import io.jmix2mvp.petclinic.entity.Visit;
import io.jmix2mvp.petclinic.mapper.Mapper;
import io.jmix2mvp.petclinic.repository.VisitRepository;
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
public class VisitService {
    private final VisitRepository visitRepository;
    private final Mapper mapper;
    private final EntityGraphs entityGraphs;

    public VisitService(VisitRepository visitRepository, Mapper mapper, EntityGraphs entityGraphs) {
        this.visitRepository = visitRepository;
        this.mapper = mapper;
        this.entityGraphs = entityGraphs;
    }


    @Secured("ROLE_ADMIN")
    @GraphQLQuery(name = "visit")
    public VisitDTO findById(@GraphQLArgument(name = "id") Long id,
                             @GraphQLEnvironment ResolutionEnvironment environment) {
        EntityGraph entityGraph = entityGraphs.getEntityGraph(Visit.class, environment);
        return visitRepository.findById(id, entityGraph)
                .map(this::mapToDTO)
                .orElse(null);
    }

    @Secured("ROLE_ADMIN")
    @GraphQLQuery(name = "visitList")
    public List<VisitDTO> findAll(@GraphQLEnvironment ResolutionEnvironment environment) {
        EntityGraph entityGraph = entityGraphs.getEntityGraph(Visit.class, environment);
        return StreamSupport.stream(visitRepository.findAll(entityGraph).spliterator(), false)
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    protected VisitDTO mapToDTO(Visit visit) {
        return mapper.map(visit);
    }
}
