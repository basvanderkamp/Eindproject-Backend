package nl.novi.homeprojects.controllers;

import nl.novi.homeprojects.dtos.output.AssignmentOutputDto;
import nl.novi.homeprojects.repositorys.AssignmentRepository;
import nl.novi.homeprojects.services.AssignmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("assignments")
public class AssignmentController {
    private final AssignmentService assignmentService;
    private final AssignmentRepository assignmentRepository;

    public AssignmentController(AssignmentService assignmentService,
                                AssignmentRepository assignmentRepository) {
        this.assignmentService = assignmentService;
        this.assignmentRepository = assignmentRepository;
    }

    @GetMapping("")
    public ResponseEntity<Iterable<AssignmentOutputDto>> getAssignments() {
        return ResponseEntity.ok(assignmentService.getAssignments());
    }
}
