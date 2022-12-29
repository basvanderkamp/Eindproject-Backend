package nl.novi.homeprojects.dtos.input;

import lombok.Getter;
import lombok.Setter;
import nl.novi.homeprojects.models.Assignment;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
public class ClientInputDto {

    @NotBlank
    private String fullName;
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

    //Relations
    private List<Assignment> assignment;
}
