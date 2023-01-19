package nl.novi.homeprojects.services;

import nl.novi.homeprojects.dtos.input.AssignmentInputDto;
import nl.novi.homeprojects.dtos.output.AssignmentOutputDto;
import nl.novi.homeprojects.exceptions.RecordNotFoundException;
import nl.novi.homeprojects.models.Assignment;
import nl.novi.homeprojects.models.Executor;
import nl.novi.homeprojects.repositorys.AssignmentRepository;
import nl.novi.homeprojects.repositorys.ClientRepository;
import nl.novi.homeprojects.repositorys.ExecutorRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Optional;

import static nl.novi.homeprojects.models.AssignmentStatus.*;

@Service
public class AssignmentService {
    public static AssignmentRepository assignmentRepository;
    public static ClientRepository clientRepository;
    public static ExecutorRepository executorRepository;

    public AssignmentService ( AssignmentRepository assignmentRepository, ClientRepository clientRepository, ExecutorRepository executorRepository) {
        this.assignmentRepository = assignmentRepository;
        this.clientRepository = clientRepository;
        this.executorRepository = executorRepository;
    }



    public String createAssignment(AssignmentInputDto assignmentInputDto) {
        Assignment newAssignment = new Assignment();

        newAssignment.setTitle(assignmentInputDto.getTitle());
        newAssignment.setDescription(assignmentInputDto.getDescription());
        newAssignment.setEssentials(assignmentInputDto.getEssentials());
        newAssignment.setDemands(assignmentInputDto.getDemands());
        newAssignment.setReward(assignmentInputDto.getReward());
        newAssignment.setClient(assignmentInputDto.getClient());
        newAssignment.setAssignmentStatus(AVAILABLE);
        Assignment savedAssignment = assignmentRepository.save(newAssignment);

        return "Assignment with title: " + savedAssignment.getTitle() + " created!";
    }


    public Iterable<AssignmentOutputDto> getAssignments() {
        ArrayList<AssignmentOutputDto> assignmentList = new ArrayList<>();

        Iterable<Assignment> allAssignments = assignmentRepository.findAll();
        for (Assignment assignment : allAssignments) {
            assignmentList.add(transferToAssignmentDto(assignment));
        }
        return assignmentList;
    }


    public AssignmentOutputDto getOneAssignment(String id) {
        Optional<Assignment> assignment = assignmentRepository.findById(id);

        if (assignment.isEmpty()) {
            throw new RecordNotFoundException("No Assignment found with id: " + id);
        } else {
            return transferToAssignmentDto(assignment.get());
        }
    }


    public String deleteAssignment(String id) {
        Optional<Assignment> deleteAssignment = assignmentRepository.findById(id);

        if (deleteAssignment.isEmpty()) {
            throw new RecordNotFoundException("No assignment found with title: " + id);
        } else {
            assignmentRepository.deleteById(id);
            return "Assignment with title: " + id + " is deleted!";
        }
    }


    public AssignmentOutputDto overrideAssignment(String id, AssignmentInputDto assignmentInputDto) {
        Optional<Assignment> toOverrideAssignment = assignmentRepository.findById(id);

        if (toOverrideAssignment.isEmpty()) {
            throw new RecordNotFoundException("No assignment found with title: " + id);
        } else {

            Assignment updateAssignment = toOverrideAssignment.get();

            updateAssignment.setTitle(assignmentInputDto.getTitle());
            updateAssignment.setDescription(assignmentInputDto.getDescription());
            updateAssignment.setEssentials(assignmentInputDto.getEssentials());
            updateAssignment.setDemands(assignmentInputDto.getDemands());
            updateAssignment.setReward(assignmentInputDto.getReward());

            assignmentRepository.save(updateAssignment);
            return transferToAssignmentDto(updateAssignment);
        }
    }


    public static void assignExecutorToAssignment(String id, String executorId) {

        Optional<Assignment> optionalAssignment = assignmentRepository.findById(id);
        Optional<Executor> optionalExecutor = executorRepository.findById(executorId);

        if (optionalExecutor.isEmpty()) {
            throw new RecordNotFoundException("Executor with username id: " + executorId + " not found");
        } else if (optionalAssignment.isEmpty()) {
            throw new RecordNotFoundException("Assignment with title id: " + id + " not found");

        } else {
            Assignment assignment = optionalAssignment.get();
            Executor executor = optionalExecutor.get();

            assignment.setExecutor(executor);
            assignment.setAssignmentStatus(ACCEPTED);
            assignmentRepository.save(assignment);
        }
    }


    public AssignmentOutputDto transferToAssignmentDto(Assignment assignment) {
        AssignmentOutputDto outputDto = new AssignmentOutputDto();

        outputDto.setTitle(assignment.getTitle());
        outputDto.setDescription(assignment.getDescription());
        outputDto.setEssentials(assignment.getEssentials());
        outputDto.setDemands(assignment.getDemands());
        outputDto.setReward(assignment.getReward());
        outputDto.setClient(assignment.getClient());
        outputDto.setAssignmentStatus(assignment.getAssignmentStatus());
        outputDto.setExecutor(assignment.getExecutor());
        return outputDto;
    }
}
