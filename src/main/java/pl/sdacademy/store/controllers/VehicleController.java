package pl.sdacademy.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.sdacademy.store.model.Vehicle;
import pl.sdacademy.store.services.VehicleService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/vehicle")
public class VehicleController {

    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping("/list")
    public String showList(Model model) {
        List<Vehicle> vehicles = vehicleService.findAll();
        model.addAttribute("vehicles", vehicles);
        return "vehicle/list";
    }

    @GetMapping("/add")
    public String showAddNewVehicle(Model model) {
        model.addAttribute("vehicle", new Vehicle());
        return "vehicle/edit";
    }

    @GetMapping("/{id}/edit")
    public String showEditVehicles(@PathVariable("id") Integer id, Model model) {
        Optional<Vehicle> first = vehicleService.findById(id);
        model.addAttribute("vehicle", first.get());
        return "vehicle/edit";
    }

    @PostMapping("/save")
    public String saveVehicle(@ModelAttribute("vehicle") Vehicle vehicle) {
        vehicleService.save(vehicle);
        return "redirect:/vehicle/list";
    }

    @GetMapping("/{id}/delete")
    public String deleteVehicle(@PathVariable("id") Integer id, Model model) {
        vehicleService.deleteById(id);
        return "redirect:/vehicle/list";
    }
}
