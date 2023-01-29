package nl.novi.homeprojects.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "filedocuments")
public class FileDocument {

    @Id
    @GeneratedValue
    private Long id;

    private String fileName;

    @Lob
    private byte[] docFile;


    public String getFileName() {
        return fileName;
    }

    public byte[] getDocFile() {
        return docFile;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setDocFile(byte[] docFile) {
        this.docFile = docFile;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private Client client;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
