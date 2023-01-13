package nl.novi.homeprojects.dtos.input;

import lombok.Getter;
import lombok.Setter;
import nl.novi.homeprojects.models.AssignmentStatus;
import nl.novi.homeprojects.models.Client;
import nl.novi.homeprojects.models.Executor;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
public class AssignmentInputDto {

    @NotBlank
    private String title;
    private String description;
    private String essentials;
    private String demands;
    private String reward;
    private AssignmentStatus assignmentStatus;



    //Relations
    private Client client;
    private Executor executor;


}
