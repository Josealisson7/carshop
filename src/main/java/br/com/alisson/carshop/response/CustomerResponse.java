package br.com.alisson.carshop.response;

import br.com.alisson.carshop.entity.Customer;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CustomerResponse {
    private String name;
    private String cpf;
    private LocalDate birthDate;
    private List<CarResponse> cars;

    public CustomerResponse() {
        this.cars = new ArrayList<>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getName() {
        return this.name;
    }

    public String getCpf() {
        return this.cpf;
    }

    public LocalDate getBirthDate() {
        return this.birthDate;
    }

    public List<CarResponse> getCars() {
        return cars;
    }

    public void setCars(List<CarResponse> cars) {
        this.cars = cars;
    }

    public static CustomerResponse convertEntityToReply(Customer customer) {
        CustomerResponse customerResponse = new CustomerResponse();
        if (customer != null) {
            customerResponse.setName(customer.getName());
            customerResponse.setCpf(customer.getCpf());
            customerResponse.setBirthDate(customer.getBirthDate());
            if (!customer.getCars().isEmpty()) {
                List<CarResponse> carResponseList = customer.getCars().stream().map(car -> {
                    CarResponse carResponse = new CarResponse();
                    carResponse.setBrand(car.getBrand());
                    carResponse.setCode(car.getCode());
                    carResponse.setModel(car.getModel());
                    carResponse.setValue(car.getValue());
                    return carResponse;
                }).collect(Collectors.toList());
                customerResponse.setCars(carResponseList);
            }
        }
        return customerResponse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerResponse that = (CustomerResponse) o;
        return name.equals(that.name) && cpf.equals(that.cpf) && birthDate.equals(that.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cpf, birthDate);
    }
}
