package pl.sdacademy.store.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sdacademy.store.model.Insurance;
import pl.sdacademy.store.repositories.InsuranceRepository;

import java.util.List;
import java.util.Optional;

@Service
public class InsuranceService {

    private final InsuranceRepository insuranceRepository;

    @Autowired
    public InsuranceService(InsuranceRepository insuranceRepository) {
        this.insuranceRepository = insuranceRepository;
    }

    public List<Insurance> findAll() {
        return insuranceRepository.findAll();
    }

    public Optional<Insurance> findById(Integer id) {
        return insuranceRepository.findById(id);
    }

    public void save(Insurance insurance) {
        insuranceRepository.save(insurance);
    }

    public void deleteById(Integer id) {
        insuranceRepository.deleteById(id);
    }

    public Insurance update(Insurance insurance) {
        return insuranceRepository.findAll().stream()
                .filter(v -> v.getNumber().equals(insurance.getNumber()))
                .findFirst()
                .orElse(insurance);
    }
}
