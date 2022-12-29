package nl.novi.homeprojects.dtos.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;


@Getter
@Setter
public class UserInputDto {

    @Email
    private String email;

    private String password;
}
