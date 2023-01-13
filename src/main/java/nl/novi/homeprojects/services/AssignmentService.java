package nl.novi.homeprojects.services;

import nl.novi.homeprojects.dtos.input.AssignmentInputDto;
import nl.novi.homeprojects.dtos.output.AssignmentOutputDto;
import nl.novi.homeprojects.exceptions.RecordNotFoundException;
import nl.novi.homeprojects.models.Assignment;
import nl.novi.homeprojects.models.Client;
import nl.novi.homeprojects.repositorys.AssignmentRepository;
import nl.novi.homeprojects.repositorys.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

import static nl.novi.homeprojects.models.AssignmentStatus.*;

@Service
public class AssignmentService {
    public static AssignmentRepository assignmentRepository;
    public static ClientRepository clientRepository;

    public AssignmentService (AssignmentRepository assignmentRepository, ClientRepository clientRepository){
        this.assignmentRepository = assignmentRepository;
        this.clientRepository = clientRepository;
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
