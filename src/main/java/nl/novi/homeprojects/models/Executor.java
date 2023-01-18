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

    @OneToMany(mappedBy = "executor")
    private List<Assignment> assignments;


    @OneToOne(mappedBy = "executor")
    @JsonIgnore
    private Client client;


}




