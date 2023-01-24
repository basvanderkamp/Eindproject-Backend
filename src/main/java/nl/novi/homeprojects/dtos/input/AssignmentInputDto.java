package nl.novi.homeprojects.dtos.input;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.novi.homeprojects.models.AssignmentStatus;
import nl.novi.homeprojects.models.Client;
import nl.novi.homeprojects.models.Executor;
import javax.validation.constraints.NotBlank;

@Getter
@Setter

public class AssignmentInputDto {

    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotBlank
    private String essentials;
    @NotBlank
    private String demands;
    @NotBlank
    private String reward;
    private AssignmentStatus assignmentStatus;

    public AssignmentInputDto() {
    }

    public AssignmentInputDto(String title, String description, String essentials, String demands, String reward, AssignmentStatus assignmentStatus) {
        this.title = title;
        this.description = description;
        this.essentials = essentials;
        this.demands = demands;
        this.reward = reward;
        this.assignmentStatus = assignmentStatus;
    }

    //Relations
    private Client client;
    private Executor executor;


}
