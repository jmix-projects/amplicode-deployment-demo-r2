package io.jmix2mvp.petclinic.graphql;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.dsl.BooleanExpression;
import io.jmix2mvp.petclinic.dto.VeterinarianDTO;
import io.jmix2mvp.petclinic.entity.QVisit;
import io.jmix2mvp.petclinic.entity.Visit;
import io.jmix2mvp.petclinic.entity.VisitState;
import io.jmix2mvp.petclinic.repository.VeterinarianRepository;
import io.jmix2mvp.petclinic.repository.VisitRepository;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLNonNull;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

import static io.jmix2mvp.petclinic.Authorities.*;

@GraphQLApi
@Service
public class VeterinarianService {
    private final VisitRepository visitRepository;
    private final VeterinarianRepository veterinarianRepository;
    private final ModelMapper mapper;

    public VeterinarianService(VisitRepository visitRepository, VeterinarianRepository veterinarianRepository, ModelMapper mapper) {
        this.visitRepository = visitRepository;
        this.veterinarianRepository = veterinarianRepository;
        this.mapper = mapper;
    }

    @Secured({ADMIN, VETERINARIAN, OWNER})
    @GraphQLQuery(name = "availableVeterinarianHours")
    @Transactional
    public int[] getAvailableHours(@GraphQLArgument(name = "id") @GraphQLNonNull Long veterinarianId, @GraphQLArgument(name = "day") LocalDate day) {
        BooleanExpression datePredicate = QVisit.visit.visitStart.between(day.atStartOfDay(), day.plusDays(1).atStartOfDay());
        BooleanExpression veterinarianPredicate = QVisit.visit.veterinarian.id.eq(veterinarianId);
        BooleanExpression activeVisits = QVisit.visit.state.ne(VisitState.CANCELED);

        Iterable<Visit> visits = visitRepository.findAll(ExpressionUtils.allOf(
                datePredicate,
                veterinarianPredicate,
                activeVisits
        ));

        List<Integer> occupiedHours = StreamSupport.stream(visits.spliterator(), false)
                .map(visit -> visit.getVisitStart().getHour())
                .collect(Collectors.toList());

        return IntStream.range(9, 19)
                .filter(value -> value != 13)
                .filter(value -> !occupiedHours.contains(value))
                .toArray();
    }


    @Secured({ADMIN, VETERINARIAN, OWNER})
    @GraphQLQuery(name = "veterinarianList")
    @Transactional
    public List<VeterinarianDTO> findAll(@GraphQLArgument(name = "page") Pageable page) {
        return veterinarianRepository.findAll(page)
                .stream()
                .map(veterinarian -> mapper.map(veterinarian, VeterinarianDTO.class))
                .collect(Collectors.toList());
    }
}