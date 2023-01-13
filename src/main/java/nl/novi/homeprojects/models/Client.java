package nl.novi.homeprojects.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;
import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "clients")
public class Client {

    @Id
    private String username;
    private String firstname;
    private String lastname;
    private String mobile;
    private String adres;
    private String place;
    private String zipcode;
    private String email;
    private String story;


    //Relations
    @OneToMany(mappedBy = "client")
    @JsonIgnore
//    @JoinTable(joinColumns = @JoinColumn(name = "client_id"),
//            inverseJoinColumns = @JoinColumn(name = "assignment_id"),
//            name = "client_assignment")
    private List <Assignment> assignments;

    public void addAssignment(Assignment assignment) {
        this.assignments.add(assignment);
    }

    @OneToOne(mappedBy = "client")
    @JsonIgnore
    private User user;

    @OneToOne(mappedBy = "client")
    @JsonIgnoreProperties(value = "client")
    private File file;

    @OneToOne
    private Executor executor;




}
