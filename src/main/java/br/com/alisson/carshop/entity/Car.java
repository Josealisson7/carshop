package br.com.alisson.carshop.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Car {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private BigDecimal value;
    @Column(nullable = false)
    private String brand;
    @Column(nullable = false)
    private String model;
    @Column(nullable = false, unique = true)
    private Long code;

    @ManyToMany
    @JoinTable(name = "VENDA",
            joinColumns = {@JoinColumn(name = "car_id",
                    referencedColumnName = "id", unique = false)},
            inverseJoinColumns = {@JoinColumn(name = "customer_id",
                    referencedColumnName = "id", unique = false)})
    private List<Customer> customers = new ArrayList<>();

    public Car(BigDecimal value, String brand, String model, Long code) {
        this.value = value;
        this.brand = brand;
        this.model = model;
        this.code = code;
    }

    public Car() {
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(value, car.value) && Objects.equals(brand, car.brand) && Objects.equals(model, car.model) && Objects.equals(code, car.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, brand, model, code);
    }
}
