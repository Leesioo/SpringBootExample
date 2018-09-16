package pl.sdacademy.store.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table (name = "insurances")
public class Insurance {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String number;
    @Column
    private BigDecimal value;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date expireDate;
    @ManyToOne (cascade=CascadeType.ALL)
    @JoinColumn (name="vehicle_id")
    private Vehicle vehicle;

    public Insurance() {
    }

    public Insurance(String number, BigDecimal value, Date expireDate) {
        this.number = number;
        this.value = value;
        this.expireDate = expireDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
