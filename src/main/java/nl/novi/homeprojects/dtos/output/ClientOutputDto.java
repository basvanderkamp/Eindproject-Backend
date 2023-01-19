package nl.novi.homeprojects.dtos.output;

import lombok.Getter;
import lombok.Setter;
import nl.novi.homeprojects.models.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

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


    //Relations
    private List<Assignment> assignments;
    private User user;
    private Executor executor;
    private File file;
}
