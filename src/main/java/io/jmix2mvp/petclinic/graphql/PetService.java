package io.jmix2mvp.petclinic.graphql;

import io.jmix2mvp.petclinic.dto.PetDTO;
import io.jmix2mvp.petclinic.dto.PetInputDTO;
import io.jmix2mvp.petclinic.entity.Owner;
import io.jmix2mvp.petclinic.entity.Pet;
import io.jmix2mvp.petclinic.entity.QOwner;
import io.jmix2mvp.petclinic.repository.OwnerRepository;
import io.jmix2mvp.petclinic.repository.PetRepository;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLNonNull;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

import static io.jmix2mvp.petclinic.Authorities.*;

@GraphQLApi
@Service
public class PetService {
    private final PetRepository crudRepository;
    private final ModelMapper mapper;
    private final OwnerRepository ownerRepository;
    @PersistenceContext
    private EntityManager entityManager;

    public PetService(PetRepository crudRepository, ModelMapper mapper, OwnerRepository ownerRepository) {
        this.crudRepository = crudRepository;
        this.mapper = mapper;
        this.ownerRepository = ownerRepository;
    }

    @Secured({ADMIN, VETERINARIAN})
    @GraphQLQuery(name = "pet")
    @Transactional
    public PetDTO findById(@GraphQLArgument(name = "id") Long id) {
        return crudRepository.findById(id)
                .map(e -> mapper.map(e, PetDTO.class))
                .orElse(null);
    }

    @Secured({ADMIN, VETERINARIAN})
    @GraphQLQuery(name = "petList")
    @Transactional
    public List<PetDTO> findAll(@GraphQLArgument(name = "page") Pageable pageable) {
        return crudRepository.findAll(pageable).stream()
                .map(e -> mapper.map(e, PetDTO.class))
                .collect(Collectors.toList());
    }

    @Secured({OWNER})
    @GraphQLQuery(name = "myPets")
    @Transactional
    public List<PetDTO> loadMyPets(
            @GraphQLArgument(name = "page") Pageable pageable
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return crudRepository.findPetsByUser(authentication.getName(), pageable).stream()
                .map(e -> mapper.map(e, PetDTO.class))
                .collect(Collectors.toList());
    }

    @Secured({ADMIN, VETERINARIAN})
    @GraphQLQuery(name = "petListByOwner")
    @Transactional
    public List<PetDTO> findPetsOwner(
            @GraphQLArgument(name = "ownerId") @NonNull Long ownerId,
            @GraphQLArgument(name = "page") Pageable pageable
    ) {
        return crudRepository.findPetsByOwner(ownerId, pageable).stream()
                .map(e -> mapper.map(e, PetDTO.class))
                .collect(Collectors.toList());
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'VETERINARIAN')")
    @GraphQLMutation(name = "update_Pet")
    @Transactional
    public PetDTO update(PetInputDTO input, Long ownerId) {
        return savePet(input, ownerId);
    }

    @PreAuthorize("hasAnyRole('OWNER')")
    @GraphQLMutation(name = "saveMyPet")
    @Transactional
    public PetDTO savePet(PetInputDTO pet) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Owner owner = ownerRepository.findOne(QOwner.owner.user.username.eq(authentication.getName()))
                .orElseThrow(() -> new RuntimeException("Current user is not Owner"));

        return savePet(pet, owner.getId());
    }

    @Transactional(propagation = Propagation.MANDATORY)
    PetDTO savePet(PetInputDTO input, Long ownerId) {
        if (input.getId() != null) {
            if (!crudRepository.existsById(input.getId())) {
                throw new ResourceNotFoundException(
                        String.format("Unable to find entity by id: %s ", input.getId()));
            }
        }
        Pet entity = new Pet();
        mapper.map(input, entity);
        entity.setOwner(ownerRepository.getById(ownerId));

        entity = crudRepository.saveAndFlush(entity);

        entityManager.refresh(entity);

        return mapper.map(entity, PetDTO.class);
    }

    @Secured({ADMIN, VETERINARIAN, OWNER})
    @GraphQLMutation(name = "delete_Pet")
    @Transactional
    public void delete(@GraphQLNonNull Long id) {
        Pet entity = crudRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Unable to find entity by id: %s ", id)));

        crudRepository.delete(entity);
    }
}
