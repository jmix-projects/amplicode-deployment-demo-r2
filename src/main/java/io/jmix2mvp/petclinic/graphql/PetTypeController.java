package io.jmix2mvp.petclinic.graphql;

import io.jmix2mvp.petclinic.dto.PetTypeDTO;
import io.jmix2mvp.petclinic.dto.PetTypeInputDTO;
import io.jmix2mvp.petclinic.entity.PetType;
import io.jmix2mvp.petclinic.repository.PetTypeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static io.jmix2mvp.petclinic.Authorities.ADMIN;
import static io.jmix2mvp.petclinic.Authorities.VETERINARIAN;

@Validated
@Controller
public class PetTypeController {
    private final PetTypeRepository crudRepository;
    private final ModelMapper mapper;

    public PetTypeController(PetTypeRepository petTypeRepository, ModelMapper mapper) {
        this.crudRepository = petTypeRepository;
        this.mapper = mapper;
    }

    @Secured({ADMIN, VETERINARIAN})
    @QueryMapping(name = "petType")
    @Transactional
    public PetTypeDTO findById(Long id) {
        return crudRepository.findById(id)
                .map(e -> mapper.map(e, PetTypeDTO.class))
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Unable to find entity by id: %s ", id)));
    }

    @Secured({ADMIN, VETERINARIAN})
    @QueryMapping(name = "petTypeList")
    @Transactional
    public List<PetTypeDTO> findAll() {
        return crudRepository.findAll().stream()
                .map(e -> mapper.map(e, PetTypeDTO.class))
                .collect(Collectors.toList());
    }

    @Secured({ADMIN, VETERINARIAN})
    @MutationMapping(name = "update_PetType")
    @Transactional
    public PetTypeDTO update(@Argument @Valid PetTypeInputDTO input) {
        if (input.getId() != null) {
            if (!crudRepository.existsById(input.getId())) {
                throw new ResourceNotFoundException(
                        String.format("Unable to find entity by id: %s ", input.getId()));
            }
        }

        PetType entity = new PetType();
        mapper.map(input, entity);
        entity = crudRepository.save(entity);

        return mapper.map(entity, PetTypeDTO.class);
    }

    @Secured({ADMIN, VETERINARIAN})
    @MutationMapping(name = "delete_PetType")
    @Transactional
    public void delete(@Argument Long id) {
        PetType entity = crudRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Unable to find entity by id: %s ", id)));

        crudRepository.delete(entity);
    }
}
