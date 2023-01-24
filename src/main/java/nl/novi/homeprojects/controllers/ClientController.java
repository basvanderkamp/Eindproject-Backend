package nl.novi.homeprojects.controllers;

import nl.novi.homeprojects.dtos.input.ClientInputDto;
import nl.novi.homeprojects.dtos.output.ClientOutputDto;
import nl.novi.homeprojects.models.FileDocument;
import nl.novi.homeprojects.services.ClientService;
import nl.novi.homeprojects.services.DatabaseService;
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

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("clients")
public class ClientController {

    private final DatabaseService databaseService;
    private final ClientService clientService;

    public ClientController (ClientService clientService, DatabaseService databaseService) {
        this.clientService = clientService;
        this.databaseService = databaseService;
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


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClientById(@PathVariable String id) {
        return ResponseEntity.ok(clientService.deleteClient(id));
    }


    @PatchMapping("/{id}")
    public ResponseEntity<ClientOutputDto> changeClientByID(@PathVariable String id, @RequestBody ClientInputDto clientInputDto) {
        return ResponseEntity.ok(clientService.patchClient(id, clientInputDto));
    }


    @PutMapping("/{id}/assignments/{assignmentId}")
    public void assignAssignmentToClient(@PathVariable String id, @PathVariable String assignmentId) {
        ClientService.assignAssignmentToClient(id, assignmentId);
    }


    @PostMapping("/{id}/upload")
    public void assignPhotoToClient(@PathVariable String id, @RequestBody MultipartFile file) throws IOException {

        //String url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/download/").path(Objects.requireNonNull(file.getOriginalFilename())).toUriString();
        FileDocument fileDocument = databaseService.uploadFileDocument(file);

        ClientService.assignFileToClient(fileDocument.getFileName(), id);
    }
}
