package nl.novi.homeprojects.dtos.output;

import lombok.Getter;
import lombok.Setter;
import nl.novi.homeprojects.models.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class ClientOutputDto {

    @NotBlank
    private String username;
    @NotBlank
    private String firstname;
    @NotBlank
    private String lastname;
    @NotBlank
    private String mobile;
    @NotBlank
    private String adres;
    @NotBlank
    private String place;
    @NotBlank
    private String zipcode;
    @NotBlank
    private String email;
    @NotBlank
    private String story;

    public ClientOutputDto() {
    }

    public ClientOutputDto(String username, String firstname, String lastname, String mobile, String adres, String place, String zipcode, String email, String story) {
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
    private List<Assignment> assignments;
    private User user;
    private Executor executor;
    private FileDocument fileDocument;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientOutputDto that = (ClientOutputDto) o;
        return Objects.equals(username, that.username) && Objects.equals(firstname, that.firstname) && Objects.equals(lastname, that.lastname) && Objects.equals(mobile, that.mobile) && Objects.equals(adres, that.adres) && Objects.equals(place, that.place) && Objects.equals(zipcode, that.zipcode) && Objects.equals(email, that.email) && Objects.equals(story, that.story) && Objects.equals(assignments, that.assignments) && Objects.equals(user, that.user) && Objects.equals(executor, that.executor) && Objects.equals(fileDocument, that.fileDocument);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, firstname, lastname, mobile, adres, place, zipcode, email, story, assignments, user, executor, fileDocument);
    }
}
