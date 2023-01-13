package nl.novi.homeprojects.dtos.output;

import lombok.Getter;
import lombok.Setter;
import nl.novi.homeprojects.models.Assignment;
import nl.novi.homeprojects.models.Client;

@Getter
@Setter
public class ExecutorOutputDto {

    private String username;
    private Client client;
    private Assignment assignment;
}
