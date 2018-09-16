package pl.sdacademy.store.reposietories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sdacademy.store.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
