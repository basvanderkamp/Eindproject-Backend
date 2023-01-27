package nl.novi.homeprojects.services;

import nl.novi.homeprojects.dtos.output.ExecutorOutputDto;
import nl.novi.homeprojects.exceptions.RecordNotFoundException;
import nl.novi.homeprojects.models.Assignment;
import nl.novi.homeprojects.models.Executor;
import nl.novi.homeprojects.repositorys.ExecutorRepository;
import nl.novi.homeprojects.repositorys.AssignmentRepository;
import nl.novi.homeprojects.repositorys.ClientRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

import static nl.novi.homeprojects.models.AssignmentStatus.AVAILABLE;
import static nl.novi.homeprojects.models.AssignmentStatus.FINISHED;

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

    public static void finishAssignment(String id, String assignmentId ) {

        Optional<Assignment> finishedAssignment = assignmentRepository.findById(assignmentId);
        Optional<Executor> optionalExecutor = executorRepository.findById(id);

        if (finishedAssignment.isEmpty()) {
            throw new RecordNotFoundException("assignment with title id: " + assignmentId + " not found");
        } else if (optionalExecutor.isEmpty()) {
            throw new RecordNotFoundException("Executor with username id: " + id + " not found");
        }else {
            Assignment assignment = finishedAssignment.get();
            Executor executor = optionalExecutor.get();

            executor.deleteAssignment(assignment);
            executorRepository.save(executor);
            assignment.setAssignmentStatus(FINISHED);
            assignmentRepository.save(assignment);
        }
    }


    public static void cancelAssignment(String id, String assignmentId ) {

        Optional<Assignment> canceledAssignment = assignmentRepository.findById(assignmentId);
        Optional<Executor> optionalExecutor = executorRepository.findById(id);

        if (canceledAssignment.isEmpty()) {
            throw new RecordNotFoundException("assignment with title id: " + assignmentId + " not found");
        } else if (optionalExecutor.isEmpty()) {
            throw new RecordNotFoundException("Executor with username id: " + id + " not found");
        }else {
            Assignment assignment = canceledAssignment.get();
            Executor executor = optionalExecutor.get();

            executor.deleteAssignment(assignment);
            executorRepository.save(executor);
            assignment.setAssignmentStatus(AVAILABLE);
            assignmentRepository.save(assignment);
        }
    }



}

