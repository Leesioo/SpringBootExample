package pl.sdacademy.store.reposietories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sdacademy.store.model.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
}
