package nl.novi.homeprojects.controllers;

import nl.novi.homeprojects.dtos.input.AssignmentInputDto;
import nl.novi.homeprojects.dtos.input.ExecutorInputDto;
import nl.novi.homeprojects.services.ClientService;
import nl.novi.homeprojects.services.ExecutorService;
import nl.novi.homeprojects.services.FileService;
import nl.novi.homeprojects.utils.Utils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;


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



    @PostMapping("")
    public ResponseEntity<String> createExecutor(@RequestBody ExecutorInputDto executorInputDto, BindingResult br) {

        if (br.hasErrors()) {
            String errorString = Utils.reportErrors(br);
            return new ResponseEntity<>(errorString, HttpStatus.BAD_REQUEST);
        } else {
            String createdId = ExecutorService.createExecutor(executorInputDto);

            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/executors/" + createdId).toUriString());

            return ResponseEntity.created(uri).body("Executor with username: " + executorInputDto.getUsername() + " created!");
        }
    }
}
