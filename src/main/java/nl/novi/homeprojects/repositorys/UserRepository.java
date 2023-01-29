package nl.novi.homeprojects.repositorys;

import nl.novi.homeprojects.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
