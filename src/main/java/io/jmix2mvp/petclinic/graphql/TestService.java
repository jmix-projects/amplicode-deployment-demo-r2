package io.jmix2mvp.petclinic.graphql;

import io.jmix2mvp.petclinic.dto.PetInputDTO;
import io.jmix2mvp.petclinic.dto.TestDTO;
import io.jmix2mvp.petclinic.dto.TestInputDTO;
import io.jmix2mvp.petclinic.entity.TestEntity;
import io.jmix2mvp.petclinic.repository.TestRepository;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLNonNull;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

import static io.jmix2mvp.petclinic.Authorities.ADMIN;
import static io.jmix2mvp.petclinic.Authorities.VETERINARIAN;

@GraphQLApi
@Service
public class TestService {
    private final TestRepository crudRepository;
    private final ModelMapper mapper;
    @PersistenceContext
    private EntityManager entityManager;

    public TestService(TestRepository crudRepository, ModelMapper mapper) {
        this.crudRepository = crudRepository;
        this.mapper = mapper;
    }

    @Secured({ADMIN, VETERINARIAN})
    @GraphQLQuery(name = "test")
    @Transactional
    public TestDTO findById(@GraphQLArgument(name = "id") Long id) {
        return crudRepository.findById(id)
                .map(e -> mapper.map(e, TestDTO.class))
                .orElse(null);
    }

//    @Secured({ADMIN, VETERINARIAN})
//    @GraphQLQuery(name = "testList")
//    @Transactional
//    public List<TestDTO> findAll(@GraphQLArgument(name = "page") Pageable pageable) {
//        return crudRepository.findAll(pageable).stream()
//                .map(e -> mapper.map(e, TestDTO.class))
//                .collect(Collectors.toList());
//    }
//
//    @PreAuthorize("hasAnyRole('ADMIN', 'VETERINARIAN')")
//    @GraphQLMutation(name = "update_Test")
//    @Transactional
//    public TestDTO update(TestInputDTO input) {
//        if (input.getId() != null) {
//            if (!crudRepository.existsById(input.getId())) {
//                throw new ResourceNotFoundException(
//                        String.format("Unable to find entity by id: %s ", input.getId()));
//            }
//        }
//        TestEntity entity = new TestEntity();
//        mapper.map(input, entity);
//        entity = crudRepository.saveAndFlush(entity);
//
//        entityManager.refresh(entity);
//
//        return mapper.map(entity, TestDTO.class);
//    }
//
//    @Secured({ADMIN, VETERINARIAN})
//    @GraphQLMutation(name = "delete_Test")
//    @Transactional
//    public void delete(@GraphQLNonNull Long id) {
//        TestEntity entity = crudRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException(String.format("Unable to find entity by id: %s ", id)));
//
//        crudRepository.delete(entity);
//    }
}
