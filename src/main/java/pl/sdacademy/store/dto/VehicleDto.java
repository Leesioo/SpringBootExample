package pl.sdacademy.store.dto;

public class VehicleDto {
    private String firm;
    private String model;
    private String registrationNumber;
    private Integer productionDate;

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
}
