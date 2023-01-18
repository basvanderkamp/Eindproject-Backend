package nl.novi.homeprojects.repositorys;

import nl.novi.homeprojects.models.File;
import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FilesRepository extends JpaRepository<File, String> {
    Optional<FileUpload> findByFileName(String fileName);
}
