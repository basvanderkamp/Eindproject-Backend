package nl.novi.homeprojects.models;

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


    //Relations
    @ManyToOne
    private Client client;


    @OneToOne
    private Executor executor;



}
