package io.jmix2mvp.petclinic.mapper;

import io.jmix2mvp.petclinic.dto.*;
import io.jmix2mvp.petclinic.entity.Owner;
import io.jmix2mvp.petclinic.entity.Pet;
import io.jmix2mvp.petclinic.entity.PetType;
import io.jmix2mvp.petclinic.entity.Visit;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = EntityMapper.class)
public interface DTOMapper {
    VisitDTO visitToDTO(Visit visit);

    PetTypeDTO petTypeToDTO(PetType petType);

    PetDTO petToDTO(Pet pet);

    OwnerDTO ownerToDTO(Owner owner);

    void visitDTOToEntity(VisitInputDTO dto, @MappingTarget Visit entity);

    void petDTOToEntity(PetInputDTO dto, @MappingTarget Pet entity);

    void ownerDTOToEntity(OwnerInputDTO dto, @MappingTarget Owner entity);

    void petTypeDTOToEntity(PetTypeInputDTO dto, @MappingTarget PetType entity);
}
