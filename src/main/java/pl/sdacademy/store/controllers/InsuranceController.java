package pl.sdacademy.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.sdacademy.store.dto.PolicyDto;
import pl.sdacademy.store.model.Customer;
import pl.sdacademy.store.model.Insurance;
import pl.sdacademy.store.model.Vehicle;
import pl.sdacademy.store.reposietories.CustomerRepository;
import pl.sdacademy.store.reposietories.InsuranceRepository;
import pl.sdacademy.store.reposietories.VehicleRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/insurance")
public class InsuranceController {

//    public static final Insurance[] insurances = new Insurance[]{
//            new Insurance("123ASD", new BigDecimal(1234), new Date(2019,1,1)),
//            new Insurance("112DER", new BigDecimal(2211), new Date(2019,1,1)),
//            new Insurance("143DS", new BigDecimal(112), new Date(2019,1,1)),
//            new Insurance("4123REW", new BigDecimal(654), new Date(2019,1,1)),
//            new Insurance("321TRE", new BigDecimal(543), new Date(2019,1,1)),
//    };

    private final InsuranceRepository insuranceRepository;
    private final VehicleRepository vehicleRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public InsuranceController(InsuranceRepository insuranceRepository, VehicleRepository vehicleRepository, CustomerRepository customerRepository) {
        this.insuranceRepository = insuranceRepository;
        this.vehicleRepository = vehicleRepository;
        this.customerRepository = customerRepository;
    }

    @GetMapping("/list")
    public String showList(Model model) {
        List<Insurance> insurances = insuranceRepository.findAll();
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
        Optional<Insurance> first = insuranceRepository.findById(id);
        model.addAttribute("insurance", first.get());
        return "insurance/edit";
    }

    @PostMapping("/save")
    public String saveInsurance(@ModelAttribute("insurance") Insurance insurance) {
        insuranceRepository.save(insurance);
        return "redirect:/insurance/list";
    }

    @GetMapping("/{id}/delete")
    public String deleteInsurance(@PathVariable("id") Integer id, Model model) {
        insuranceRepository.deleteById(id);
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
        Vehicle vehicle = vehicleRepository.findAll().stream()
                .filter(v -> v.getRegistrationNumber().equals(policyDto.getRegistrationNumber()))
                .findFirst()
                .orElse(new Vehicle(policyDto.getFirm(), policyDto.getModel(), policyDto.getRegistrationNumber(), policyDto.getProductionDate()));
        Insurance insurance = null;
        try {
            insurance = new Insurance(policyDto.getNumber(), policyDto.getValue(), sdf.parse("2019-01-01"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Customer customer = customerRepository.findAll().stream()
                .filter(c -> c.getLastName().equals(policyDto.getLastName()))
                .filter(c -> c.getFirstName().equals(policyDto.getFirstName()))
                .findFirst()
                .orElse(new Customer(policyDto.getFirstName(), policyDto.getLastName()));
        customer.addVehicle(vehicle);
        vehicle.addCustomer(customer);
        vehicle.addInsurance(insurance);
        insurance.setVehicle(vehicle);

        customerRepository.saveAndFlush(customer);
        vehicleRepository.saveAndFlush(vehicle);
        insuranceRepository.saveAndFlush(insurance);

        return "redirect:/insurance/list";
    }
}
