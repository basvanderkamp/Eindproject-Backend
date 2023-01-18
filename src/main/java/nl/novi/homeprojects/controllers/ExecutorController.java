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






}
