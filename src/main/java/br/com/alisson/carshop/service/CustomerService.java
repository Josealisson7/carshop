package br.com.alisson.carshop.service;

import br.com.alisson.carshop.request.CustomerRequest;
import br.com.alisson.carshop.entity.Customer;
import br.com.alisson.carshop.exception.BusinessException;
import br.com.alisson.carshop.exception.RegisterNotFoundException;
import br.com.alisson.carshop.repository.CustomerRepository;
import br.com.alisson.carshop.response.CustomerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public CustomerResponse register(CustomerRequest customerRequest) throws BusinessException {
        if (this.customerRepository.existsByCpf(customerRequest.getCpf())) {
            throw new BusinessException("This CPF has already been registered in the system");
        }
        Customer customer = new Customer(
                customerRequest.getName(),
                customerRequest.getCpf(),
                customerRequest.getBirthDate()
        );
        return CustomerResponse.convertEntityToReply(this.customerRepository.save(customer));
    }

    public List<CustomerResponse> list() {
        return this.customerRepository.findAll().stream().map(CustomerResponse::convertEntityToReply).collect(Collectors.toList());
    }

    public CustomerResponse update(String cpf, CustomerRequest customerRequest) throws RegisterNotFoundException, BusinessException {
        Customer customer = this.customerRepository.findByCpf(cpf);
        this.validateDataBeforeUpdating(customer, customerRequest);
        customer.setName(customerRequest.getName());
        customer.setBirthDate(customerRequest.getBirthDate());
        customer.setCpf(customerRequest.getCpf());
        return CustomerResponse.convertEntityToReply(this.customerRepository.save(customer));
    }

    private void validateDataBeforeUpdating(Customer customer, CustomerRequest customerRequest) throws RegisterNotFoundException, BusinessException {
        if (customer == null) {
            throw new RegisterNotFoundException("User not registered in the system");
        }
        if (!customerRequest.getCpf().equals(customer.getCpf())) {
            if (this.customerRepository.existsByCpf(customerRequest.getCpf())) {
                throw new BusinessException("This CPF has already been registered in the system");
            }
        }
    }

    public void delete(String cpf) throws RegisterNotFoundException {
        if (!customerRepository.existsByCpf(cpf)) {
            throw new RegisterNotFoundException("User not registered in the system");
        }
        this.customerRepository.deleteByCpf(cpf);
    }

    public CustomerResponse find(String cpf) {
        return CustomerResponse.convertEntityToReply(this.customerRepository.findByCpf(cpf));
    }
}
