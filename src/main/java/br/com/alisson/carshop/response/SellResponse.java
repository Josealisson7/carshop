package br.com.alisson.carshop.response;

import br.com.alisson.carshop.entity.Car;
import br.com.alisson.carshop.entity.Customer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

public class SellResponse {
    private CustomerResponse customerResponse;
    private CarResponse carResponse;

    @JsonProperty("customer")
    public CustomerResponse getCustomerResponse() {
        return customerResponse;
    }

    private void setCustomerResponse(CustomerResponse customerResponse) {
        this.customerResponse = customerResponse;
    }

    @JsonIgnore
    public CarResponse getCarResponse() {
        return carResponse;
    }

    private void setCarResponse(CarResponse carResponse) {
        this.carResponse = carResponse;
    }

    public void setSaleData(CustomerResponse customerResponse, CarResponse carResponse) {
        this.customerResponse = customerResponse;
        this.carResponse = carResponse;
        this.customerResponse.setCars(List.of(carResponse));
    }

    public static SellResponse convertEntityToReply(Customer customer, Car car) {
        SellResponse sellResponse = new SellResponse();
        CarResponse carResponse = new CarResponse();
        if (customer != null && car != null) {
            CustomerResponse customerResponse = new CustomerResponse();
            customerResponse.setName(customer.getName());
            customerResponse.setCpf(customer.getCpf());
            customerResponse.setBirthDate(customer.getBirthDate());
            carResponse.setBrand(car.getBrand());
            carResponse.setCode(car.getCode());
            carResponse.setModel(car.getModel());
            carResponse.setValue(car.getValue());
            sellResponse.setSaleData(customerResponse, carResponse);
        }
        return sellResponse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SellResponse that = (SellResponse) o;
        return customerResponse.equals(that.customerResponse) && carResponse.equals(that.carResponse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerResponse, carResponse);
    }
}
