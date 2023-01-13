package nl.novi.homeprojects.services;

import nl.novi.homeprojects.models.File;
import nl.novi.homeprojects.repositorys.FilesRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.stream.Stream;


@Service
public class FileService {

    @org.springframework.beans.factory.annotation.Value("${my.upload_location}")
    private Path fileStoragePath;
    private final String fileStorageLocation;


    private FilesRepository filesRepository;

    public FileService(@Value("${my.upload_location}") String fileStorageLocation, FilesRepository filesRepository) {
        fileStoragePath = Paths.get(fileStorageLocation).toAbsolutePath().normalize();

        this.fileStorageLocation = fileStorageLocation;
        this.filesRepository = filesRepository;

        try {
            Files.createDirectories(fileStoragePath);
        } catch (IOException e) {
            throw new RuntimeException("Issue in creating file directory");
        }

    }



    public String storeFile(MultipartFile file, String url) {

        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        Path filePath = Paths.get(fileStoragePath + "\\" + fileName);

        try {
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Issue in storing the file", e);
        }

        filesRepository.save(new File(fileName, file.getContentType(), url));

        return fileName;
    }

    public File getFile(String id) {
        return filesRepository.findById(id).get();
    }

    public Stream<File> getAllFiles() {
        return filesRepository.findAll().stream();
    }
}


