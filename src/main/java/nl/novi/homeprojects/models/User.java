package nl.novi.homeprojects.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

        @Id
        @Column(nullable = false, unique = true)
        private String username;

        @Column(nullable = false, length = 255)
        private String password;

        @Column(nullable = false)
        private boolean enabled = true;

        @Column
        private String apikey;



        @OneToMany(
                targetEntity = Authority.class,
                mappedBy = "username",
                cascade = CascadeType.ALL,
                orphanRemoval = true,
                fetch = FetchType.EAGER)
        private Set<Authority> authorities = new HashSet<>();

        public void addAuthority(Authority authority) {
                this.authorities.add(authority);
        }
        public void removeAuthority(Authority authority) {
                this.authorities.remove(authority);
        }


        @OneToOne(cascade = CascadeType.ALL)
        private Client client;


}