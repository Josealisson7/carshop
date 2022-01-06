package br.com.alisson.carshop.service;

import br.com.alisson.carshop.request.SellRequest;
import br.com.alisson.carshop.entity.Car;
import br.com.alisson.carshop.entity.Customer;
import br.com.alisson.carshop.repository.CarRepository;
import br.com.alisson.carshop.repository.CustomerRepository;
import br.com.alisson.carshop.response.CarResponse;
import br.com.alisson.carshop.response.SellResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class CarServiceTests {
    @Mock
    private CarRepository carRepository;
    @Mock
    private CustomerRepository customerRepository;
    private Car car;
    @InjectMocks
    private CarService carService;

    @BeforeEach
    void createCar() {
        car = new Car(BigDecimal.valueOf(6022.00), "Fiat", "Palio EX 1.0 mpi 2p", 133520L);
    }

    @Test
    @DisplayName("List cars")
    void list() {
        List<Car> carListMock = List.of(car);
        List<CarResponse> carResponseListMock = List.of(CarResponse.convertEntityToReply(car));
        Mockito.when(carRepository.findAll()).thenReturn(carListMock);
        List<CarResponse> carList = carService.list();
        assertEquals(carList, carResponseListMock);
        Mockito.verify(carRepository, Mockito.times(1)).findAll();
    }

    @Test
    @DisplayName("Find car")
    void find() {
        Mockito.when(carRepository.findByCode(Mockito.any(Long.class))).thenReturn(car);
        CarResponse carResult = carService.find(car.getCode());
        assertEquals(car.getCode(), carResult.getCode());
        assertEquals(car.getModel(), carResult.getModel());
        Mockito.verify(carRepository, Mockito.times(1)).findByCode(Mockito.any(Long.class));
    }

    @Test
    @DisplayName("Sell car")
    void sell() {
        SellRequest sellRequest = new SellRequest();
        sellRequest.setCarCode(car.getCode());
        Customer customer = new Customer("test", "27238053012", LocalDate.now());
        sellRequest.setCustomerCpf(customer.getCpf());
        SellResponse sellResponse = SellResponse.convertEntityToReply(customer, car);
        Mockito.when(customerRepository.findByCpf(Mockito.any(String.class))).thenReturn(customer);
        Mockito.when(carRepository.findByCode(Mockito.any(Long.class))).thenReturn(car);
        Mockito.when(customerRepository.existsByCpf(Mockito.any(String.class))).thenReturn(true);
        Mockito.when(carRepository.existsByCode(Mockito.any(Long.class))).thenReturn(true);
        assertDoesNotThrow(() -> carService.sell(sellRequest));
        Mockito.verify(carRepository, Mockito.times(1)).findByCode(Mockito.any(Long.class));
        Mockito.verify(customerRepository, Mockito.times(1)).findByCpf(Mockito.any(String.class));
    }
}
