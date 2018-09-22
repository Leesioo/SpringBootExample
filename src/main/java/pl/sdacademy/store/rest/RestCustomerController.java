package pl.sdacademy.store.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.sdacademy.store.dto.CustomerDto;
import pl.sdacademy.store.model.Customer;
import pl.sdacademy.store.services.CustomerService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/customer")
public class RestCustomerController {

    private final CustomerService customerService;

    @Autowired
    public RestCustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(value = "/list", produces = "application/json")
    public List<CustomerDto> showList() {
        return customerService.findAll().stream()
                .map(c -> new CustomerDto(c))
                .collect(Collectors.toList());
    }

    @PutMapping(value = "/{id}/edit", consumes = "application/json")
    public CustomerDto editCustomer(@PathVariable("id") Integer id, CustomerDto customerDto) {
        return new CustomerDto(customerService.update(new Customer(customerDto)));
    }

    @GetMapping(value = "/{id}/delete", produces = "application/json")
    public void deleteCustomer(@PathVariable("id") Integer id) {
        customerService.deleteById(id);
    }
}
