package nl.novi.homeprojects.controllers;

import nl.novi.homeprojects.services.ClientService;
import nl.novi.homeprojects.services.ExecutorService;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("executors")
public class ExecutorController {

    private  ExecutorService executorService;
    private  ClientService clientService;

    public ExecutorController(ClientService clientService, ExecutorService executorService) {
        this.clientService = clientService;
        this.executorService = executorService;
    }


    @PutMapping("/{id}/assignments/{assignmentId}")
    public void finishAssignment(@PathVariable String id, @PathVariable String assignmentId) {
        ExecutorService.finishAssignment(id, assignmentId);
    }
}
