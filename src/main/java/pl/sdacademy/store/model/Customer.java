package pl.sdacademy.store.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table (name = "customers")
public class Customer {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @ManyToMany(mappedBy = "customers")
    private Set<Vehicle> vehicles;

    public Customer() {
    }

    public Customer(String firstName, String lastName) {

        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Set<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(Set<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public void addVehicle(Vehicle vehicle) {
        if (vehicles == null) {
            vehicles = new HashSet<>();
        }
        vehicles.add(vehicle);
    }
}
