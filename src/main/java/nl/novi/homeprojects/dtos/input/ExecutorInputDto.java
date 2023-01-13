package nl.novi.homeprojects.dtos.input;

import lombok.Getter;
import lombok.Setter;
import nl.novi.homeprojects.models.Assignment;
import nl.novi.homeprojects.models.Client;

@Getter
@Setter
public class ExecutorInputDto {

    private String username;
    private Client client;
    private Assignment assignment;
}