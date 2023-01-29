package nl.novi.homeprojects.dtos.input;

import lombok.Getter;
import lombok.Setter;
import nl.novi.homeprojects.models.Assignment;
import nl.novi.homeprojects.models.Executor;
import nl.novi.homeprojects.models.FileDocument;
import nl.novi.homeprojects.models.User;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
public class ClientInputDto {

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

    public ClientInputDto() {
    }

    public ClientInputDto(String username, String firstname, String lastname, String mobile, String adres, String place, String zipcode, String email, String story) {
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
    private User user;
    private List<Assignment> assignments;
    private Executor executor;

    private FileDocument fileDocument;
}
