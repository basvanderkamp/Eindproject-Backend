package nl.novi.homeprojects.controllers;

import nl.novi.homeprojects.messages.ResponseFile;
import nl.novi.homeprojects.messages.ResponseMessage;
import nl.novi.homeprojects.models.File;
import nl.novi.homeprojects.services.FileService;
import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;



import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
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


    @GetMapping("/download/{fileName}")
    ResponseEntity<Resource> downLoadSingleFile(@PathVariable String fileName, HttpServletRequest request) {

        Resource resource = fileService.downLoadFile(fileName);

        String mimeType;
        try{
            mimeType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            mimeType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(mimeType)).header(HttpHeaders.CONTENT_DISPOSITION, "inline;fileName=" + resource.getFilename()).body(resource);
    }
}


