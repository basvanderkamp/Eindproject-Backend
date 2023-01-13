package nl.novi.homeprojects.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "executor")
public class Executor {

    @Id
    private String username;

    @OneToOne(mappedBy = "executor")
    private Assignment assignment;

    @OneToOne(mappedBy = "executor")
    private Client client;


}




