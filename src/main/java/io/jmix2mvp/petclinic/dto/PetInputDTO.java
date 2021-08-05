package io.jmix2mvp.petclinic.dto;

import java.time.LocalDate;

public class PetInputDTO extends BaseDTO {
    private String identificationNumber;
    private LocalDate birthDate;
    private PetTypeDTO type;
    private OwnerDTO owner;

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public PetTypeDTO getType() {
        return type;
    }

    public void setType(PetTypeDTO type) {
        this.type = type;
    }

    public OwnerDTO getOwner() {
        return owner;
    }

    public void setOwner(OwnerDTO owner) {
        this.owner = owner;
    }
}
