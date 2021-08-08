package io.jmix2mvp.petclinic.mapper;

import io.jmix2mvp.petclinic.dto.ReferenceInputDTO;
import io.jmix2mvp.petclinic.entity.BaseEntity;
import org.mapstruct.TargetType;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class EntityMapper {
    @PersistenceContext
    private EntityManager entityManager;

    public <T extends BaseEntity> T resolveReference(ReferenceInputDTO referenceDTO, @TargetType Class<T> entityClass) {
        return entityManager.find(entityClass, referenceDTO.getId());
    }
}
