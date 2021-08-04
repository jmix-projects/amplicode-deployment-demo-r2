package io.jmix2mvp.petclinic.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OwnerDTO extends BaseDTO {
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String email;
    private String telephone;
}
