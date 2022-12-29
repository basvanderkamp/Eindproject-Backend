package nl.novi.homeprojects.dtos.output;

import lombok.Getter;
import lombok.Setter;
import nl.novi.homeprojects.models.Assignment;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.List;

@Getter
@Setter
public class ExecutorOutputDto {

    @NotBlank
    private String fullName;
    @Positive
    @DecimalMin("12")
    private int Age;
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
