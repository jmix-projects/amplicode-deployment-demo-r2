package io.jmix2mvp.petclinic.mapper;

import io.jmix2mvp.petclinic.dto.*;
import io.jmix2mvp.petclinic.entity.Owner;
import io.jmix2mvp.petclinic.entity.Pet;
import io.jmix2mvp.petclinic.entity.PetType;
import io.jmix2mvp.petclinic.entity.Visit;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
    private final ModelMapper modelMapper;

    public Mapper() {
        this.modelMapper = new ModelMapper();
        this.modelMapper.getConfiguration().setSkipNullEnabled(true);
    }

    public PetTypeDTO mapToDTO(PetType src) {
        return modelMapper.map(src, PetTypeDTO.class);
    }

    public void mapFromDTO(PetTypeInputDTO src, PetType dst) {
        modelMapper.map(src, dst);
    }

    public void mapFromDTO(OwnerInputDTO src, Owner dst) {
        modelMapper.map(src, dst);
    }

    public void mapFromDTO(PetInputDTO src, Pet dst) {
        modelMapper.map(src, dst);
    }

    public void mapFromDTO(VisitInputDTO src, Visit dst) {
        modelMapper.map(src, dst);
    }

    public OwnerDTO mapToDTO(Owner src) {
        return modelMapper.map(src, OwnerDTO.class);
    }

    public PetDTO mapToDTO(Pet src) {
        return modelMapper.map(src, PetDTO.class);
    }

    public VisitDTO mapToDTO(Visit src) {
        return modelMapper.map(src, VisitDTO.class);
    }
}
