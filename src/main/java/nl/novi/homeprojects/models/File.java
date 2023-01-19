package nl.novi.homeprojects.models;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "files")
public class File {


    @Id
    private String fileName;
    private String contentType;
    private String url;


    public File(String fileName, String contentType, String url) {
        this.fileName = fileName;
        this.contentType = contentType;
        this.url = url;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private Client client;

}


