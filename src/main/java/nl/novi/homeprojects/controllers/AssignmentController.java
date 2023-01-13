package nl.novi.homeprojects.controllers;

import nl.novi.homeprojects.dtos.input.AssignmentInputDto;
import nl.novi.homeprojects.dtos.output.AssignmentOutputDto;
import nl.novi.homeprojects.services.AssignmentService;
import nl.novi.homeprojects.utils.Utils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("assignments")
public class AssignmentController {



    private final AssignmentService assignmentService;

    public AssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;

    }


    @GetMapping("")
    public ResponseEntity<Iterable<AssignmentOutputDto>> getAssignments() {
        return ResponseEntity.ok(assignmentService.getAssignments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssignmentOutputDto> getAssignmentById(@PathVariable String id) {
        return ResponseEntity.ok(assignmentService.getOneAssignment(id));
    }



    @PostMapping("")
    public ResponseEntity<String> createAssignment(@Valid @RequestBody AssignmentInputDto assignmentInputDto, BindingResult br) {

        if (br.hasErrors()) {
            String errorString = Utils.reportErrors(br);
            return new ResponseEntity<>(errorString, HttpStatus.BAD_REQUEST);
        } else {
            String createdId = assignmentService.createAssignment(assignmentInputDto);

            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/assignments/" + createdId).toUriString());

            return ResponseEntity.created(uri).body("Assignment with title: " + assignmentInputDto.getTitle() + " created!");
        }
    }



    @PutMapping("/{id}")
    public ResponseEntity<AssignmentOutputDto> overWriteAssignment(@PathVariable String id, @RequestBody AssignmentInputDto assignmentInputDto) {
        return ResponseEntity.ok(assignmentService.overrideAssignment(id, assignmentInputDto));
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAssignmentById(@PathVariable String id) {
        return ResponseEntity.ok(assignmentService.deleteAssignment(id));
    }


}
