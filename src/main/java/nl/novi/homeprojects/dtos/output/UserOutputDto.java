package nl.novi.homeprojects.dtos.output;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;

@Getter
@Setter
public class UserOutputDto {

    @Email
    private String email;

    private String password;
}
