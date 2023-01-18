package nl.novi.homeprojects.dtos.output;

import lombok.Getter;
import lombok.Setter;
import nl.novi.homeprojects.models.Assignment;
import nl.novi.homeprojects.models.Client;

import java.util.List;

@Getter
@Setter
public class ExecutorOutputDto {

    private String name;
    private Client client;
    private List<Assignment> assignments;
}
