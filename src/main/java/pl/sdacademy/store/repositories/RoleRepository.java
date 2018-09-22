package pl.sdacademy.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sdacademy.store.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
