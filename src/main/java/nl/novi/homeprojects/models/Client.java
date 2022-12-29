package nl.novi.homeprojects.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String fullName;
    private String mobile;
    private String adres;
    private String place;
    private String zipcode;
    private String email;


    //Relations
    @OneToMany(mappedBy = "client")
    private List <Assignment> assignment;


}
