package nl.novi.homeprojects.dtos.output;

import lombok.Getter;
import lombok.Setter;
import nl.novi.homeprojects.models.Assignment;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
public class ClientOutputDto {

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
