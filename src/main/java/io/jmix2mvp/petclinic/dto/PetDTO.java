package io.jmix2mvp.petclinic.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class PetDTO extends BaseDTO {
    private String identificationNumber;
    private LocalDate birthDate;
    private PetTypeDTO type;
    private OwnerDTO owner;
}
