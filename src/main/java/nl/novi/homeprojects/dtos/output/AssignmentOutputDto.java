package nl.novi.homeprojects.dtos.output;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.novi.homeprojects.models.AssignmentStatus;
import nl.novi.homeprojects.models.Client;
import nl.novi.homeprojects.models.Executor;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AssignmentOutputDto {

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


    public AssignmentOutputDto(String title, String description, String essentials, String demands, String reward, AssignmentStatus assignmentStatus) {
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

    // Test Benodigdheden


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AssignmentOutputDto that = (AssignmentOutputDto) o;
        return Objects.equals(title, that.title) && Objects.equals(description, that.description) && Objects.equals(essentials, that.essentials) && Objects.equals(demands, that.demands) && Objects.equals(reward, that.reward) && assignmentStatus == that.assignmentStatus && Objects.equals(client, that.client) && Objects.equals(executor, that.executor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, essentials, demands, reward, assignmentStatus, client, executor);
    }
}
