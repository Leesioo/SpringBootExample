package pl.sdacademy.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sdacademy.store.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
