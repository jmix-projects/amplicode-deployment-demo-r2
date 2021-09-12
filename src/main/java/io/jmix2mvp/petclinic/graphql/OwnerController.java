package io.jmix2mvp.petclinic.graphql;

import io.jmix2mvp.petclinic.dto.OwnerDTO;
import io.jmix2mvp.petclinic.dto.OwnerInputDTO;
import io.jmix2mvp.petclinic.entity.Owner;
import io.jmix2mvp.petclinic.repository.OwnerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static io.jmix2mvp.petclinic.Authorities.ADMIN;
import static io.jmix2mvp.petclinic.Authorities.VETERINARIAN;

@Controller
public class OwnerController {
    private final OwnerRepository crudRepository;
    private final ModelMapper mapper;

    public OwnerController(OwnerRepository crudRepository, ModelMapper mapper) {
        this.crudRepository = crudRepository;
        this.mapper = mapper;
    }

    @Secured({ADMIN, VETERINARIAN})
    @QueryMapping(name = "owner")
    @Transactional
    public OwnerDTO findById(@Argument Long id) {
        return crudRepository.findById(id)
                .map(e -> mapper.map(e, OwnerDTO.class))
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Unable to find entity by id: %s ", id)));
    }

    @Secured({ADMIN, VETERINARIAN})
    @QueryMapping(name = "ownerList")
    @Transactional
    public List<OwnerDTO> findAll() {
        return crudRepository.findAll().stream()
                .map(e -> mapper.map(e, OwnerDTO.class))
                .collect(Collectors.toList());
    }

    @Secured({ADMIN, VETERINARIAN})
    @MutationMapping(name = "update_Owner")
    @Transactional
    public OwnerDTO update(@Argument OwnerInputDTO input) {
        if (input.getId() != null) {
            if (!crudRepository.existsById(input.getId())) {
                throw new ResourceNotFoundException(
                        String.format("Unable to find entity by id: %s ", input.getId()));
            }
        }

        Owner entity = new Owner();
        mapper.map(input, entity);
        entity = crudRepository.save(entity);

        return mapper.map(entity, OwnerDTO.class);
    }

    @Secured({ADMIN, VETERINARIAN})
    @MutationMapping(name = "delete_Owner")
    @Transactional
    public void delete(@Argument Long id) {
        Owner entity = crudRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Unable to find entity by id: %s ", id)));

        crudRepository.delete(entity);
    }
}
