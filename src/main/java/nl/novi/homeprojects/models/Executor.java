package nl.novi.homeprojects.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "executor")
public class Executor {
    @Id
    private String name;

    public Executor(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "executor")
    private List<Assignment> assignments;

    public void deleteAssignment(Assignment assignment) {
        this.assignments.remove(assignment);
        assignment.setExecutor(null);
    }

    @OneToOne(mappedBy = "executor")
    @JsonIgnore
    private Client client;
}




