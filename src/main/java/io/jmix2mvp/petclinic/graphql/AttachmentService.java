package io.jmix2mvp.petclinic.graphql;

import io.jmix2mvp.petclinic.dto.AttachmentDTO;
import io.jmix2mvp.petclinic.dto.AttachmentInputDTO;
import io.jmix2mvp.petclinic.entity.Attachment;
import io.jmix2mvp.petclinic.repository.AttachmentRepository;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLNonNull;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static io.jmix2mvp.petclinic.Authorities.ADMIN;
import static io.jmix2mvp.petclinic.Authorities.VETERINARIAN;

@GraphQLApi
@Service
public class AttachmentService {
    private final AttachmentRepository crudRepository;
    private final ModelMapper mapper;

    public AttachmentService(AttachmentRepository crudRepository, ModelMapper mapper) {
        this.crudRepository = crudRepository;
        this.mapper = mapper;
    }

    @Secured({ADMIN, VETERINARIAN})
    @GraphQLQuery(name = "attachment")
    @Transactional
    public AttachmentDTO findById(@GraphQLArgument(name = "id") Long id) {
        return crudRepository.findById(id)
                .map(e -> mapper.map(e, AttachmentDTO.class))
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Unable to find entity by id: %s ", id)));
    }

    @Secured({ADMIN, VETERINARIAN})
    @GraphQLQuery(name = "attachmentList")
    @Transactional
    public List<AttachmentDTO> findAll(@GraphQLArgument(name = "page") Pageable pageable) {
        return crudRepository.findAll(pageable).stream()
                .map(e -> mapper.map(e, AttachmentDTO.class))
                .collect(Collectors.toList());
    }

    @Secured({ADMIN, VETERINARIAN})
    @GraphQLMutation(name = "update_Attachment")
    @Transactional
    public AttachmentDTO update(AttachmentInputDTO input) {
        if (input.getId() != null) {
            if (!crudRepository.existsById(input.getId())) {
                throw new ResourceNotFoundException(
                        String.format("Unable to find entity by id: %s ", input.getId()));
            }
        }

        Attachment entity = new Attachment();
        mapper.map(input, entity);
        entity = crudRepository.save(entity);

        return mapper.map(entity, AttachmentDTO.class);
    }

    @Secured({ADMIN, VETERINARIAN})
    @GraphQLMutation(name = "delete_Attachment")
    @Transactional
    public void delete(@GraphQLNonNull Long id) {
        Attachment entity = crudRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Unable to find entity by id: %s ", id)));

        crudRepository.delete(entity);
    }
}
