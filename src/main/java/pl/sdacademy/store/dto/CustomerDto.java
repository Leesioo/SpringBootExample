package pl.sdacademy.store.dto;

import pl.sdacademy.store.model.Customer;

public class CustomerDto {
    private String firstName;
    private String lastName;

    public CustomerDto() {
    }

    public CustomerDto(Customer customer) {
        this.firstName = customer.getFirstName();
        this.lastName = customer.getLastName();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
