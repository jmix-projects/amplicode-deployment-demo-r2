package io.jmix2mvp.petclinic.dto;

import java.time.LocalDate;

public class PetInputDTO extends BaseDTO {
    private String identificationNumber;
    private LocalDate birthDate;
    private ReferenceInputDTO type;
    private ReferenceInputDTO owner;

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

    public ReferenceInputDTO getType() {
        return type;
    }

    public void setType(ReferenceInputDTO type) {
        this.type = type;
    }

    public ReferenceInputDTO getOwner() {
        return owner;
    }

    public void setOwner(ReferenceInputDTO owner) {
        this.owner = owner;
    }
}
