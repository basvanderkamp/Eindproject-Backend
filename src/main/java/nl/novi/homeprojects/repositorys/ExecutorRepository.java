package nl.novi.homeprojects.repositorys;

import nl.novi.homeprojects.models.Executor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExecutorRepository extends JpaRepository<Executor, String> {
}