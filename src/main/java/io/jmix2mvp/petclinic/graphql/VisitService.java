package io.jmix2mvp.petclinic.graphql;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import io.jmix2mvp.petclinic.dto.PetDTO;
import io.jmix2mvp.petclinic.dto.VisitDTO;
import io.jmix2mvp.petclinic.dto.VisitInputDTO;
import io.jmix2mvp.petclinic.entity.QVisit;
import io.jmix2mvp.petclinic.entity.Visit;
import io.jmix2mvp.petclinic.entity.VisitState;
import io.jmix2mvp.petclinic.repository.PetRepository;
import io.jmix2mvp.petclinic.repository.VisitExpressions;
import io.jmix2mvp.petclinic.repository.VisitRepository;
import io.leangen.graphql.annotations.*;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static io.jmix2mvp.petclinic.Authorities.*;

@GraphQLApi
@Service
public class VisitService {
    private final VisitRepository visitRepository;
    private final PetRepository petRepository;
    private final ModelMapper mapper;

    public VisitService(VisitRepository visitRepository, PetRepository petRepository, ModelMapper mapper) {
        this.visitRepository = visitRepository;
        this.petRepository = petRepository;
        this.mapper = mapper;
    }

    @Secured({ADMIN, VETERINARIAN})
    @GraphQLQuery(name = "visit")
    @Transactional
    public VisitDTO findById(@GraphQLArgument(name = "id") Long id) {
        return visitRepository.findById(id)
                .map(e -> mapper.map(e, VisitDTO.class))
                .orElse(null);
    }

    @Secured({ADMIN, VETERINARIAN})
    @GraphQLQuery(name = "visitList")
    @Transactional
    public List<VisitDTO> findAll(@GraphQLArgument(name = "page") Pageable pageable) {
        return visitRepository.findAll(pageable).stream()
                .map(e -> mapper.map(e, VisitDTO.class))
                .collect(Collectors.toList());
    }

    @Secured({ADMIN, VETERINARIAN, OWNER})
    @GraphQLQuery(name = "findVisits")
    @Transactional
    public List<VisitDTO> findVisits(
            @GraphQLArgument(name = "page") Pageable pageable,
            @GraphQLArgument(name = "fromDate") LocalDateTime from,
            @GraphQLArgument(name = "toDate") LocalDateTime to,
            @GraphQLArgument(name = "state") VisitState visitState) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        BooleanExpression predicate = VisitExpressions.byDatesRange(from, to);
        if (visitState != null) {
            predicate = predicate.and(VisitExpressions.byState(visitState));
        }

        predicate = VisitExpressions.withRowLevelPermissions(predicate, authentication);

        return visitRepository.findAll(predicate, pageable).stream()
                .map(e -> mapper.map(e, VisitDTO.class))
                .collect(Collectors.toList());
    }

    @Secured({ADMIN, VETERINARIAN, OWNER})
    @GraphQLQuery(name = "pet")
    @Transactional
    public PetDTO getPet(@GraphQLContext VisitDTO visitDTO) {
        return petRepository.findPetByVisit(visitDTO.getId())
                .map(e -> mapper.map(e, PetDTO.class))
                .orElse(null);
    }

    @Secured({ADMIN, VETERINARIAN})
    @GraphQLMutation(name = "update_Visit")
    @Transactional
    public VisitDTO update(VisitInputDTO input) {
        if (input.getId() != null) {
            if (!visitRepository.existsById(input.getId())) {
                throw new ResourceNotFoundException(
                        String.format("Unable to find entity by id: %s ", input.getId()));
            }
        }

        Visit entity = new Visit();
        mapper.map(input, entity);
        entity = visitRepository.save(entity);

        return mapper.map(entity, VisitDTO.class);
    }

    @Secured({ADMIN, VETERINARIAN})
    @GraphQLMutation(name = "delete_Visit")
    @Transactional
    public void delete(@GraphQLNonNull Long id) {
        Visit entity = visitRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Unable to find entity by id: %s ", id)));

        visitRepository.delete(entity);
    }

    @Secured({OWNER})
    @GraphQLQuery(name = "ownerPlannedVisits")
    @Transactional
    public List<VisitDTO> findOwnerPlannedVisits(@GraphQLArgument(name = "page") Pageable page) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        BooleanExpression ownerVisits = QVisit.visit.pet.owner.user.username.eq(username);
        BooleanExpression plannedVisits = QVisit.visit.state.eq(VisitState.PLANNED);

        return visitRepository.findAll(Expressions.allOf(ownerVisits, plannedVisits), page)
                .stream()
                .map(visit -> mapper.map(visit, VisitDTO.class))
                .collect(Collectors.toList());
    }


    @Secured({OWNER})
    @GraphQLQuery(name = "ownerPastVisits")
    @Transactional
    public List<VisitDTO> findOwnerPastVisits(@GraphQLArgument(name = "page") Pageable page) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        BooleanExpression ownerVisits = QVisit.visit.pet.owner.user.username.eq(username);
        BooleanExpression plannedVisits = QVisit.visit.state.ne(VisitState.PLANNED);

        return visitRepository.findAll(Expressions.allOf(ownerVisits, plannedVisits), page)
                .stream()
                .map(visit -> mapper.map(visit, VisitDTO.class))
                .collect(Collectors.toList());
    }


    @Secured({OWNER, VETERINARIAN, ADMIN})
    @GraphQLMutation(name = "cancelVisit")
    @Transactional
    public void cancelVisit(@GraphQLArgument(name = "id") @GraphQLNonNull Long id) {
        visitRepository.findById(id).ifPresent(visit -> {
            visit.setState(VisitState.CANCELED);

            visitRepository.save(visit);
        });
    }

    @Secured({OWNER})
    @GraphQLQuery(name = "visitListByPet")
    @Transactional
    public List<VisitDTO> findVisitsByPet(@GraphQLArgument(name = "petId") @GraphQLNonNull Long petId, @GraphQLArgument(name = "page") Pageable page) {
        return visitRepository.findAll(QVisit.visit.pet.id.eq(petId), page)
                .stream()
                .map(visit -> mapper.map(visit, VisitDTO.class))
                .collect(Collectors.toList());
    }
}
