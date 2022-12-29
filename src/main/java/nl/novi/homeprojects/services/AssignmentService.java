package nl.novi.homeprojects.services;

import nl.novi.homeprojects.dtos.output.AssignmentOutputDto;
import nl.novi.homeprojects.models.Assignment;
import nl.novi.homeprojects.repositorys.AssignmentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AssignmentService {
    public static AssignmentRepository assignmentRepository;

    public AssignmentService (AssignmentRepository assignmentRepository){
        this.assignmentRepository = assignmentRepository;
    }

    public AssignmentOutputDto transferToAssignmentDto(Assignment assignment) {
        AssignmentOutputDto outputDto = new AssignmentOutputDto();

        outputDto.setClient(assignment.getClient());
        outputDto.setExecutor(assignment.getExecutor());
        outputDto.setTitle(assignment.getTitle());
        outputDto.setDescription(assignment.getDescription());
        outputDto.setEssentials(assignment.getEssentials());
        outputDto.setDemands(assignment.getDemands());
        outputDto.setReward(assignment.getReward());
        return outputDto;
    }

    public Iterable<AssignmentOutputDto> getAssignments() {
        ArrayList<AssignmentOutputDto> assignmentList = new ArrayList<>();

        Iterable<Assignment> allAssignments = assignmentRepository.findAll();
        for (Assignment assignment : allAssignments) {
            assignmentList.add(transferToAssignmentDto(assignment));
        }
        return assignmentList;
    }
}
