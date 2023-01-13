package nl.novi.homeprojects.controllers;

import nl.novi.homeprojects.messages.ResponseFile;
import nl.novi.homeprojects.messages.ResponseMessage;
import nl.novi.homeprojects.models.File;
import nl.novi.homeprojects.services.FileService;
import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
@CrossOrigin(origins = "*")
public class FileController {


    private FileService fileService;
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }



    @PostMapping("/upload")
    File singleFileUpload(@RequestParam("file") MultipartFile file){

        // next line makes url. example "http://localhost:8080/download/naam.jpg"
        String url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/download/").path(Objects.requireNonNull(file.getOriginalFilename())).toUriString();

        String contentType = file.getContentType();

        String fileName = fileService.storeFile(file, url);

        return new File(fileName, contentType, url );
    }

    @GetMapping("/files")
    public ResponseEntity<List<ResponseFile>> getListFiles() {
        List<ResponseFile> files = fileService.getAllFiles().map(file -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/files/")
                    .path(file.getFileName())
                    .toUriString();

            return new ResponseFile(
                    file.getFileName(),
                    fileDownloadUri,
                    file.getContentType(),
                    file.getData().length);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable String id) {
        File file = fileService.getFile(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getfileName() + "\"")
                .body(file.getData());
    }
}