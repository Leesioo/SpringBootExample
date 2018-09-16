package pl.sdacademy.store.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "vehicles")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String firm;
    @Column
    private String model;
    @Column
    private String registrationNumber;
    @Column
    private Integer productionDate;
    @OneToMany(mappedBy = "vehicle")
    private Set<Insurance> insurances;
    @ManyToMany
    @JoinTable(name = "vehicles_customer", joinColumns = {@JoinColumn(name = "vehicle_id")}, inverseJoinColumns = {@JoinColumn(name="customer_id")})
    private Set<Customer> customers;

    public Vehicle() {
    }

    public Vehicle(String firm, String model, String registrationNumber, Integer productionDate) {
        this.firm = firm;
        this.model = model;
        this.registrationNumber = registrationNumber;
        this.productionDate = productionDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirm() {
        return firm;
    }

    public void setFirm(String firm) {
        this.firm = firm;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public Integer getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(Integer productionDate) {
        this.productionDate = productionDate;
    }

    public Set<Insurance> getInsurances() {
        return insurances;
    }

    public void setInsurances(Set<Insurance> insurances) {
        this.insurances = insurances;
    }

    public Set<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }

    public void addCustomer(Customer customer) {
        if (customers == null) {
            customers = new HashSet<>();
        }
        customers.add(customer);
    }

    public void addInsurance(Insurance insurance) {
        if (insurances == null) {
            insurances = new HashSet<>();
        }
        insurances.add(insurance);
    }
}
