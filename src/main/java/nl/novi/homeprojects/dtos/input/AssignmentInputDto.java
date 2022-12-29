package nl.novi.homeprojects.dtos.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class AssignmentInputDto {

    @NotBlank
    private String title;
    @NotBlank
    private String description;
    private String essentials;
    private String demands;
    private String reward;
}
