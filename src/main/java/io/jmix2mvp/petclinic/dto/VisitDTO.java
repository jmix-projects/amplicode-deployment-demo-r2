package io.jmix2mvp.petclinic.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class VisitDTO extends BaseDTO {
    private PetDTO pet;
    private LocalDateTime visitStart;
    private LocalDateTime visitEnd;
    private String description;
}
