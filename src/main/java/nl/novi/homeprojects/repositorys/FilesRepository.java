package nl.novi.homeprojects.repositorys;

import nl.novi.homeprojects.models.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilesRepository extends JpaRepository<File, String> {
}
