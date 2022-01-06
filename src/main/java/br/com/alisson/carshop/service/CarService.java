package br.com.alisson.carshop.service;

import br.com.alisson.carshop.exception.RegisterNotFoundException;
import br.com.alisson.carshop.repository.CarRepository;
import br.com.alisson.carshop.request.SellRequest;
import br.com.alisson.carshop.entity.Car;
import br.com.alisson.carshop.entity.Customer;
import br.com.alisson.carshop.repository.CustomerRepository;
import br.com.alisson.carshop.response.CarResponse;
import br.com.alisson.carshop.response.SellResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CarRepository carRepository;

    public List<CarResponse> list() {
        return this.carRepository.findAll().stream().map(CarResponse::convertEntityToReply).collect(Collectors.toList());
    }

    public CarResponse find(Long code) {
        return CarResponse.convertEntityToReply(this.carRepository.findByCode(code));
    }

    public SellResponse sell(SellRequest sellRequest) throws Exception {
        if (!this.customerRepository.existsByCpf(sellRequest.getCustomerCpf())) {
            throw new RegisterNotFoundException("User not registered in the system");
        }
        if (!this.carRepository.existsByCode(sellRequest.getCarCode())) {
            throw new RegisterNotFoundException("Car not registered in the system");
        }
        Customer customer = this.customerRepository.findByCpf(sellRequest.getCustomerCpf());
        Car car = this.carRepository.findByCode(sellRequest.getCarCode());
        customer.getCars().add(car);
        this.customerRepository.save(customer);
        return SellResponse.convertEntityToReply(customer, car);
    }
}
