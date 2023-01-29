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

    public Client(String username, String firstname, String lastname, String mobile, String adres, String place, String zipcode, String email, String story) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.mobile = mobile;
        this.adres = adres;
        this.place = place;
        this.zipcode = zipcode;
        this.email = email;
        this.story = story;
    }

    //Relations
    @OneToMany(mappedBy = "client")
    @JsonIgnoreProperties("assignments")
    private List <Assignment> assignments;

    @OneToOne(mappedBy = "client")
    @JsonIgnore
    private User user;

    @OneToOne(mappedBy = "client")
    @JsonIgnoreProperties(value = "client")
    private FileDocument fileDocument;

    @OneToOne
    private Executor executor;

}
