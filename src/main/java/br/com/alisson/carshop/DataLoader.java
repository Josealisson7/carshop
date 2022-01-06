package br.com.alisson.carshop;

import br.com.alisson.carshop.repository.CarRepository;
import br.com.alisson.carshop.repository.CustomerRepository;
import br.com.alisson.carshop.entity.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements ApplicationRunner {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CarRepository carRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        customerRepository.deleteAll();
        carRepository.deleteAll();
        List<Car> cars = new ArrayList<Car>();
        cars.add(new Car(BigDecimal.valueOf(6022.00), "Fiat", "Palio EX 1.0 mpi 2p", 133520L));
        cars.add(new Car(BigDecimal.valueOf(47965.00), "Agrale", "Agrale MARRUÁ 2.8 12V", 133521L));
        cars.add(new Car(BigDecimal.valueOf(13500.00), "Honda", "Accord Coupe EX ", 133522L));
        carRepository.saveAll(cars);
    }
}