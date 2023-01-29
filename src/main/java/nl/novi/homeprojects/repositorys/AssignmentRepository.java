package nl.novi.homeprojects.repositorys;

import nl.novi.homeprojects.models.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssignmentRepository extends JpaRepository<Assignment, String> {
}
