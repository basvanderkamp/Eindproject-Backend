package nl.novi.homeprojects.dtos.output;

import lombok.Getter;
import lombok.Setter;
import nl.novi.homeprojects.models.Client;
import nl.novi.homeprojects.models.Executor;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class AssignmentOutputDto {

    @NotBlank
    private String title;
    @NotBlank
    private String description;
    private String essentials;
    private String demands;
    private String reward;


    //Relations
    private Client client;
    private Executor executor;
}
