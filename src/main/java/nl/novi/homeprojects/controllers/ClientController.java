package nl.novi.homeprojects.controllers;

import nl.novi.homeprojects.dtos.input.ClientInputDto;
import nl.novi.homeprojects.dtos.output.ClientOutputDto;
import nl.novi.homeprojects.services.ClientService;
import nl.novi.homeprojects.services.FileService;
import nl.novi.homeprojects.utils.Utils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.util.Objects;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("clients")
public class ClientController {


    private final FileService fileService;
    private final ClientService clientService;

    public ClientController (ClientService clientService, FileService fileService) {
        this.clientService = clientService;
        this.fileService = fileService;
    }

    @GetMapping("")
    public ResponseEntity<Iterable<ClientOutputDto>> getClients() {
        return ResponseEntity.ok(clientService.getClients());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientOutputDto> getClientById(@PathVariable String id) {
        return ResponseEntity.ok(clientService.getOneClient(id));
    }

    @PostMapping("")
    public ResponseEntity<String> createClient(@Valid @RequestBody ClientInputDto clientInputDto, BindingResult br) {

        if (br.hasErrors()) {
            String errorString = Utils.reportErrors(br);
            return new ResponseEntity<>(errorString, HttpStatus.BAD_REQUEST);
        } else {
            String createdId = clientService.createClient(clientInputDto);

            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/clients/" + createdId).toUriString());

            return ResponseEntity.created(uri).body("Client created!");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientOutputDto> overWriteClient(@PathVariable String id, @RequestBody ClientInputDto clientInputDto) {
        return ResponseEntity.ok(clientService.overrideClient(id, clientInputDto));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClientById(@PathVariable String id) {
        return ResponseEntity.ok(clientService.deleteClient(id));
    }

    @PutMapping("/{id}/assignments/{assignmentId}")
    public void assignAssignmentToClient(@PathVariable String id, @PathVariable String assignmentId) {
        ClientService.assignAssignmentToClient(id, assignmentId);
    }



    @PostMapping("/{id}/upload")
    public void assignPhotoToClient(@PathVariable String id, @RequestBody MultipartFile file) {

        String url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/download/").path(Objects.requireNonNull(file.getOriginalFilename())).toUriString();
        String photo = fileService.storeFile(file, url);

        ClientService.assignFileToClient(photo, id);

    }






}
