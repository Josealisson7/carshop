package br.com.alisson.carshop.service;

import br.com.alisson.carshop.exception.BusinessException;
import br.com.alisson.carshop.exception.RegisterNotFoundException;
import br.com.alisson.carshop.request.CustomerRequest;
import br.com.alisson.carshop.entity.Customer;
import br.com.alisson.carshop.repository.CustomerRepository;
import br.com.alisson.carshop.response.CustomerResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
class CustomerServiceTests {
    @Mock
    private CustomerRepository customerRepository;
    @InjectMocks
    private CustomerService customerService;
    @InjectMocks
    private CustomerRequest customerRequest;
    private Customer customer;

    @BeforeEach
    void createCustomer() {
        customer = new Customer("test", "27238053012", LocalDate.now());
    }

    @Test
    @DisplayName("Successful user registration")
    void successfulRegistration() throws BusinessException {
        CustomerResponse customerResponseMock = CustomerResponse.convertEntityToReply(customer);
        customerRequest.setName(customer.getName());
        customerRequest.setCpf(customer.getCpf());
        customerRequest.setBirthDate(customer.getBirthDate());
        Mockito.when(customerRepository.save(Mockito.any(Customer.class))).thenReturn(customer);
        CustomerResponse customerResponse = customerService.register(customerRequest);
        assertEquals(customerResponse, customerResponseMock);
        Mockito.verify(customerRepository, Mockito.times(1)).save(Mockito.any(Customer.class));
    }

    @Test
    @DisplayName("Exception throwing when registering user")
    void recordingWithExceptionRelease() {
        customerRequest.setName(customer.getName());
        customerRequest.setCpf(customer.getCpf());
        customerRequest.setBirthDate(customer.getBirthDate());
        Mockito.when(customerRepository.save(Mockito.any(Customer.class))).thenThrow(new DataIntegrityViolationException("Data breach error, make sure all data entered is correct"));
        assertThrows(DataIntegrityViolationException.class, () -> customerService.register(customerRequest));
        Mockito.verify(customerRepository, Mockito.times(1)).save(Mockito.any(Customer.class));
    }

    @Test
    @DisplayName("Successful user update")
    void successfulUpdate() throws BusinessException, RegisterNotFoundException {
        customerRequest.setName("New name test");
        customerRequest.setCpf("27238053011");
        customerRequest.setBirthDate(LocalDate.now());
        Mockito.when(customerRepository.existsByCpf(Mockito.any(String.class))).thenReturn(false);
        Mockito.when(customerRepository.findByCpf(Mockito.any(String.class))).thenReturn(customer);
        Customer newCustomer = new Customer(customerRequest.getName(), customerRequest.getCpf(), LocalDate.now());
        Mockito.when(customerRepository.save(Mockito.any(Customer.class))).thenReturn(newCustomer);
        CustomerResponse customerResponse = customerService.update(customerRequest.getCpf(), customerRequest);
        assertEquals(customerResponse.getCpf(), customerRequest.getCpf());
        assertEquals(customerResponse.getName(), customerRequest.getName());
        Mockito.verify(customerRepository, Mockito.times(1)).save(Mockito.any(Customer.class));
    }

    @Test
    @DisplayName("Exception throwing when update user")
    void updateWithExceptionRelease() {
        customerRequest.setName(customer.getName());
        customerRequest.setCpf(customer.getCpf());
        customerRequest.setBirthDate(customer.getBirthDate());
        Mockito.when(customerRepository.existsByCpf(Mockito.any(String.class))).thenReturn(true);
        Mockito.when(customerRepository.findByCpf(Mockito.any(String.class))).thenReturn(customer);
        Mockito.when(customerRepository.save(Mockito.any(Customer.class))).thenThrow(new DataIntegrityViolationException("Data breach error, make sure all data entered is correct"));
        assertThrows(DataIntegrityViolationException.class, () -> customerService.update(customerRequest.getCpf(), customerRequest));
        Mockito.verify(customerRepository, Mockito.times(1)).save(Mockito.any(Customer.class));
    }

    @Test
    @DisplayName("Successful user delete")
    void successfulDelete() {
        Mockito.when(customerRepository.existsByCpf(Mockito.any(String.class))).thenReturn(true);
        Mockito.when(customerRepository.deleteByCpf(Mockito.any(String.class))).thenReturn(1);
        assertDoesNotThrow(() -> customerService.delete("27238053012"));
        Mockito.verify(customerRepository, Mockito.times(1)).deleteByCpf(Mockito.any(String.class));
    }

    @Test
    @DisplayName("Exception throwing when delete user")
    void deleteWithExceptionRelease() {
        Mockito.when(customerRepository.existsByCpf(Mockito.any(String.class))).thenReturn(true);
        Mockito.doThrow(new DataIntegrityViolationException("Data breach error, make sure all data entered is correct")).when(customerRepository).deleteByCpf(Mockito.any(String.class));
        assertThrows(DataIntegrityViolationException.class, () -> customerService.delete("27238053012"));
        Mockito.verify(customerRepository, Mockito.times(1)).deleteByCpf(Mockito.any(String.class));
    }

    @Test
    @DisplayName("Find user")
    void findUser() {
        Mockito.when(customerRepository.findByCpf(Mockito.any(String.class))).thenReturn(customer);
        CustomerResponse customerResponse = customerService.find(customer.getCpf());
        assertEquals(customerResponse.getCpf(), customer.getCpf());
        assertEquals(customerResponse.getName(), customer.getName());
        Mockito.verify(customerRepository, Mockito.times(1)).findByCpf(Mockito.any(String.class));
    }

    @Test
    @DisplayName("Find all users")
    void findAll() {
        CustomerResponse customerResponse = CustomerResponse.convertEntityToReply(customer);
        List<CustomerResponse> customerResponseListMock = List.of(customerResponse);
        List<Customer> customerListMock = List.of(customer);
        Mockito.when(customerRepository.findAll()).thenReturn(customerListMock);
        List<CustomerResponse> customerResponseList = customerService.list();
        assertEquals(customerResponseListMock, customerResponseList);
        Mockito.verify(customerRepository, Mockito.times(1)).findAll();
    }
}
