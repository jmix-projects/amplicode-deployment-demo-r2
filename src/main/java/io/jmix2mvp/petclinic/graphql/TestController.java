package io.jmix2mvp.petclinic.graphql;

import io.jmix2mvp.petclinic.dto.TestDTO;
import io.jmix2mvp.petclinic.dto.TestInputDTO;
import io.jmix2mvp.petclinic.entity.TestEntity;
import io.jmix2mvp.petclinic.repository.TestRepository;
import org.modelmapper.ModelMapper;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

import static io.jmix2mvp.petclinic.Authorities.ADMIN;
import static io.jmix2mvp.petclinic.Authorities.VETERINARIAN;

@Controller
public class TestController {
    private final TestRepository crudRepository;
    private final ModelMapper mapper;
    @PersistenceContext
    private EntityManager entityManager;

    public TestController(TestRepository crudRepository, ModelMapper mapper) {
        this.crudRepository = crudRepository;
        this.mapper = mapper;
    }

    @Secured({ADMIN, VETERINARIAN})
    @QueryMapping(name = "test")
    @Transactional
    public TestDTO findById(@Argument Long id) {
        return crudRepository.findById(id)
                .map(e -> mapper.map(e, TestDTO.class))
                .orElse(null);
    }

    @Secured({ADMIN, VETERINARIAN})
    @QueryMapping(name = "testList")
    @Transactional
    public List<TestDTO> findAll() {
        return crudRepository.findAll().stream()
                .map(e -> mapper.map(e, TestDTO.class))
                .collect(Collectors.toList());
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'VETERINARIAN')")
    @MutationMapping(name = "update_Test")
    @Transactional
    public TestDTO update(@Argument TestInputDTO input) {
        if (input.getId() != null) {
            if (!crudRepository.existsById(input.getId())) {
                throw new ResourceNotFoundException(
                        String.format("Unable to find entity by id: %s ", input.getId()));
            }
        }
        TestEntity entity = new TestEntity();
        mapper.map(input, entity);
        entity = crudRepository.saveAndFlush(entity);

        entityManager.refresh(entity);

        return mapper.map(entity, TestDTO.class);
    }

    @Secured({ADMIN, VETERINARIAN})
    @MutationMapping(name = "delete_Test")
    @Transactional
    public void delete(@Argument Long id) {
        TestEntity entity = crudRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Unable to find entity by id: %s ", id)));

        crudRepository.delete(entity);
    }
}
