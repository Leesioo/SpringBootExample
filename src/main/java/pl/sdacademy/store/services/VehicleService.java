package pl.sdacademy.store.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sdacademy.store.model.Vehicle;
import pl.sdacademy.store.repositories.VehicleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    @Autowired
    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public List<Vehicle> findAll() {
        return vehicleRepository.findAll();
    }

    public Optional<Vehicle> findById(Integer id) {
        return vehicleRepository.findById(id);
    }

    public void save(Vehicle vehicle) {
        vehicleRepository.save(vehicle);
    }

    public void deleteById(Integer id) {
        vehicleRepository.deleteById(id);
    }

    public Vehicle update(Vehicle vehicle) {
        return vehicleRepository.findAll().stream()
                .filter(v -> v.getRegistrationNumber().equals(vehicle.getRegistrationNumber()))
                .findFirst()
                .orElse(vehicle);
    }
}
