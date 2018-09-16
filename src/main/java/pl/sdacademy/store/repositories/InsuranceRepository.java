package pl.sdacademy.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sdacademy.store.model.Insurance;

public interface InsuranceRepository extends JpaRepository<Insurance, Integer> {
}
