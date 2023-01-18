package nl.novi.homeprojects.services;

import nl.novi.homeprojects.dtos.input.ExecutorInputDto;
import nl.novi.homeprojects.dtos.output.ExecutorOutputDto;
import nl.novi.homeprojects.models.Client;
import nl.novi.homeprojects.models.Executor;
import nl.novi.homeprojects.repositorys.ExecutorRepository;
import nl.novi.homeprojects.repositorys.AssignmentRepository;
import nl.novi.homeprojects.repositorys.ClientRepository;
import org.springframework.stereotype.Service;

@Service
public class ExecutorService {

    public static ClientRepository clientRepository;
    public static AssignmentRepository assignmentRepository;
    private static ExecutorRepository executorRepository;


    public ExecutorService (ClientRepository clientRepository, AssignmentRepository assignmentRepository,
                            ExecutorRepository executorRepository) {
        this.clientRepository = clientRepository;
        this.assignmentRepository = assignmentRepository;
        this.executorRepository = executorRepository;
    }

    public ExecutorOutputDto transferToExecutorDto(Executor executor) {
        ExecutorOutputDto outputDto = new ExecutorOutputDto();

        outputDto.setName(executor.getName());
        outputDto.setAssignments(executor.getAssignments());
        return outputDto;
    }


}

