package pl.sdacademy.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.sdacademy.store.dto.PolicyDto;
import pl.sdacademy.store.model.Customer;
import pl.sdacademy.store.model.Insurance;
import pl.sdacademy.store.model.Vehicle;
import pl.sdacademy.store.services.CustomerService;
import pl.sdacademy.store.services.InsuranceService;
import pl.sdacademy.store.services.VehicleService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/insurance")
public class InsuranceController {
    private final InsuranceService insuranceService;
    private final VehicleService vehicleService;
    private final CustomerService customerService;

    @Autowired
    public InsuranceController(InsuranceService insuranceService, VehicleService vehicleService, CustomerService customerService) {
        this.insuranceService = insuranceService;
        this.vehicleService = vehicleService;
        this.customerService = customerService;
    }

    @GetMapping("/list")
    public String showList(Model model) {
        List<Insurance> insurances = insuranceService.findAll();
        model.addAttribute("insurances", insurances);
        return "insurance/list";
    }

    @GetMapping("/add")
    public String showAddNewInsurance(Model model) {
        model.addAttribute("insurance", new Insurance());
        return "insurance/edit";
    }

    @GetMapping("/{id}/edit")
    public String showEditInsurance(@PathVariable("id") Integer id, Model model) {
//        Optional<Insurance> first = Arrays.stream(insurances).filter(c -> c.getId().equals(id)).findFirst();
        Optional<Insurance> first = insuranceService.findById(id);
        model.addAttribute("insurance", first.get());
        return "insurance/edit";
    }

    @PostMapping("/save")
    public String saveInsurance(@ModelAttribute("insurance") Insurance insurance) {
        insuranceService.save(insurance);
        return "redirect:/insurance/list";
    }

    @GetMapping("/{id}/delete")
    public String deleteInsurance(@PathVariable("id") Integer id, Model model) {
        insuranceService.deleteById(id);
        return "redirect:/insurance/list";
    }

    @GetMapping("/addMaster")
    public String showAddMasterInsurance(Model model) {
        model.addAttribute("policyDto", new PolicyDto());
        return "insurance/masterPolicy";
    }

    @PostMapping("/saveMaster")
    public String saveMasterInsurance(@ModelAttribute("policyDto") PolicyDto policyDto) {
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
        Date data = null;
        try {
            data = sdf.parse("2019-01-01");
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            data = new Date();
        }
        Optional<Vehicle> vehicle = vehicleService.findAll().stream()
                .filter(v -> v.getRegistrationNumber().equals(policyDto.getRegistrationNumber()))
                .findFirst();
        Vehicle vehicleObject = null;
        if (!vehicle.isPresent()) {
            vehicleObject = new Vehicle(policyDto.getFirm(), policyDto.getModel(), policyDto.getRegistrationNumber(), policyDto.getProductionDate());
        } else {
            vehicleObject = vehicle.get();
            vehicleObject.setFirm(policyDto.getFirm());
            vehicleObject.setModel(policyDto.getModel());
            vehicleObject.setProductionDate(policyDto.getProductionDate());
        }
        Optional<Insurance> insurance = insuranceService.findAll().stream()
                .filter(i -> i.getNumber().equals(policyDto.getNumber()))
                .findFirst();
        Insurance insuranceObject = null;
        if (!insurance.isPresent()) {
            insuranceObject = new Insurance(policyDto.getNumber(), policyDto.getValue(), data);
        } else {
            insuranceObject = insurance.get();
            insuranceObject.setValue(policyDto.getValue());
            insuranceObject.setExpireDate(data);
        }
        Customer customer = customerService.findAll().stream()
                .filter(c -> c.getLastName().equals(policyDto.getLastName()))
                .filter(c -> c.getFirstName().equals(policyDto.getFirstName()))
                .findFirst()
                .orElse(new Customer(policyDto.getFirstName(), policyDto.getLastName()));
        customer.addVehicle(vehicleObject);
        vehicleObject.addCustomer(customer);
        vehicleObject.addInsurance(insuranceObject);
        insuranceObject.setVehicle(vehicleObject);

        insuranceService.save(insuranceObject);

        return "redirect:/insurance/list";
    }
}
