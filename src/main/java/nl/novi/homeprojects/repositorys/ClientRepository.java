package nl.novi.homeprojects.repositorys;

import nl.novi.homeprojects.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, String> {
}
