package pl.sdacademy.store.reposietories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sdacademy.store.model.Insurance;

public interface InsuranceRepository extends JpaRepository<Insurance, Integer> {
}
