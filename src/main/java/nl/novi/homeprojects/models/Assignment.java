package nl.novi.homeprojects.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "assignments")
public class Assignment {

    @Id
    private String title;
    private String description;
    private String essentials;
    private String demands;
    private String reward;
    @Enumerated(EnumType.STRING)
    private AssignmentStatus assignmentStatus;

    public Assignment(String title, String description, String essentials, String demands, String reward, AssignmentStatus assignmentStatus) {
        this.title = title;
        this.description = description;
        this.essentials = essentials;
        this.demands = demands;
        this.reward = reward;
        this.assignmentStatus = assignmentStatus;
    }

    //Relations
    @ManyToOne
    @JsonIncludeProperties(value = {"username","firstname", "lastname", "mobile", "adres", "place", "zipcode", "email", "story"})
    private Client client;


    @ManyToOne
    @JsonIgnoreProperties("assignments")
    private Executor executor;



}
