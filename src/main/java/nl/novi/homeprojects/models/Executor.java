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
@Table(name = "executor")
public class Executor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String fullName;
    private int age;
    private String mobile;
    private String adres;
    private String place;
    private String zipcode;
    private String email;


    //Relations
    @OneToMany(mappedBy = "executor")
    private List<Assignment> assignment;
}
