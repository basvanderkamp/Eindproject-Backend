package nl.novi.homeprojects.dtos.input;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;
import nl.novi.homeprojects.models.Authority;
import nl.novi.homeprojects.models.Client;


import javax.validation.constraints.NotBlank;
import java.util.Set;


@Getter
@Setter
public class UserInputDto {

    @NotBlank
    public String username;
    @NotBlank
    public String password;
    public Boolean enabled;
    public String apikey;

    @JsonSerialize
    public Set<Authority> authorities;

    private Client client;

}