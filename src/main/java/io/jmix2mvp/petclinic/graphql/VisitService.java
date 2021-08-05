package io.jmix2mvp.petclinic.graphql;

import io.jmix2mvp.petclinic.dto.VisitDTO;
import io.jmix2mvp.petclinic.dto.VisitInputDTO;
import io.jmix2mvp.petclinic.entity.Visit;
import io.jmix2mvp.petclinic.mapper.Mapper;
import io.jmix2mvp.petclinic.repository.VisitRepository;
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
public class VisitService {
    private final VisitRepository crudRepository;
    private final Mapper mapper;

    public VisitService(VisitRepository crudRepository, Mapper mapper) {
        this.crudRepository = crudRepository;
        this.mapper = mapper;
    }

    @Secured("ROLE_ADMIN")
    @GraphQLQuery(name = "visit")
    @Transactional
    public VisitDTO findById(@GraphQLArgument(name = "id") Long id) {
        return crudRepository.findById(id)
                .map(mapper::mapToDTO)
                .orElse(null);
    }

    @Secured("ROLE_ADMIN")
    @GraphQLQuery(name = "visitList")
    @Transactional
    public List<VisitDTO> findAll(@GraphQLArgument(name = "page") Pageable pageable) {
        return crudRepository.findAll(pageable).stream()
                .map(mapper::mapToDTO)
                .collect(Collectors.toList());
    }

    @Secured("ROLE_ADMIN")
    @GraphQLMutation(name = "update_Visit")
    @Transactional
    public VisitDTO update(VisitInputDTO input) {
        if (input.getId() != null) {
            if (crudRepository.existsById(input.getId())) {
                throw new ResourceNotFoundException(
                        String.format("Unable to find entity by id: %s ", input.getId()));
            }
        }

        Visit entity = new Visit();
        mapper.mapFromDTO(input, entity);
        entity = crudRepository.save(entity);

        return mapper.mapToDTO(entity);
    }

    @Secured("ROLE_ADMIN")
    @GraphQLMutation(name = "delete_Visit")
    @Transactional
    public void delete(@GraphQLNonNull Long id) {
        Visit entity = crudRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Unable to find entity by id: %s ", id)));

        crudRepository.delete(entity);
    }
}
