package io.jmix2mvp.petclinic.dto;

import java.time.LocalDate;

public class PetInputDTO extends BaseDTO {
    private String identificationNumber;
    private LocalDate birthDate;
    private PetTypeInputDTO type;
    private String avatarUrl;

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

    public PetTypeInputDTO getType() {
        return type;
    }

    public void setType(PetTypeInputDTO type) {
        this.type = type;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
