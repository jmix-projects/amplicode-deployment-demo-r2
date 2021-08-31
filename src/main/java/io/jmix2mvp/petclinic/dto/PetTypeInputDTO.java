package io.jmix2mvp.petclinic.dto;

import javax.validation.constraints.Pattern;

public class PetTypeInputDTO extends BaseDTO {
    @Pattern(regexp = "[a-zA-Z]*")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
