package io.jmix2mvp.petclinic.mapper;

import io.jmix2mvp.petclinic.dto.OwnerDTO;
import io.jmix2mvp.petclinic.dto.PetDTO;
import io.jmix2mvp.petclinic.dto.PetTypeDTO;
import io.jmix2mvp.petclinic.dto.VisitDTO;
import io.jmix2mvp.petclinic.entity.Owner;
import io.jmix2mvp.petclinic.entity.Pet;
import io.jmix2mvp.petclinic.entity.PetType;
import io.jmix2mvp.petclinic.entity.Visit;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    public PetTypeDTO map(PetType src) {
        PetTypeDTO dst = null;
        if (src != null) {
            dst = new PetTypeDTO();

            dst.setId(src.getId());
            dst.setName(src.getName());
        }
        return dst;
    }

    public OwnerDTO map(Owner src) {
        OwnerDTO dst = null;
        if (src != null) {
            dst = new OwnerDTO();

            dst.setId(src.getId());
            dst.setFirstName(src.getFirstName());
            dst.setLastName(src.getLastName());
            dst.setCity(src.getCity());
            dst.setAddress(src.getAddress());
            dst.setTelephone(src.getTelephone());
            dst.setEmail(src.getEmail());
        }
        return dst;
    }

    public PetDTO map(Pet src) {
        PetDTO dst = null;
        if (src != null) {
            dst = new PetDTO();

            dst.setId(src.getId());
            dst.setIdentificationNumber(src.getIdentificationNumber());
            dst.setBirthDate(src.getBirthDate());

            if (Hibernate.isInitialized(src.getType())) {
                dst.setType(map(src.getType()));
            }
            if (Hibernate.isInitialized(src.getOwner())) {
                dst.setOwner(map(src.getOwner()));
            }
        }
        return dst;
    }

    public VisitDTO map(Visit src) {
        VisitDTO dst = null;
        if (src != null) {
            dst = new VisitDTO();

            dst.setId(src.getId());
            dst.setDescription(src.getDescription());
            dst.setVisitStart(src.getVisitStart());
            dst.setVisitEnd(src.getVisitEnd());

            if (Hibernate.isInitialized(src.getPet())) {
                dst.setPet(map(src.getPet()));
            }
        }
        return dst;
    }
}
