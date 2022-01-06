package br.com.alisson.carshop.response;

import br.com.alisson.carshop.entity.Car;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CarResponse {
    private BigDecimal value;
    private String brand;
    private String model;
    private Long code;

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public static CarResponse convertEntityToReply(Car car) {
        CarResponse carResponse = new CarResponse();
        if (car != null) {
            carResponse.setBrand(car.getBrand());
            carResponse.setCode(car.getCode());
            carResponse.setModel(car.getModel());
            carResponse.setValue(car.getValue());
        }
        return carResponse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarResponse that = (CarResponse) o;
        return value.equals(that.value) && brand.equals(that.brand) && model.equals(that.model) && code.equals(that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, brand, model, code);
    }
}
