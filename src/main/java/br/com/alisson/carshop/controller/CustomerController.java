package br.com.alisson.carshop.controller;

import br.com.alisson.carshop.request.CustomerRequest;
import br.com.alisson.carshop.exception.BusinessException;
import br.com.alisson.carshop.exception.RegisterNotFoundException;
import br.com.alisson.carshop.response.CustomerResponse;
import br.com.alisson.carshop.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/customer")
@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping()
    public ResponseEntity<CustomerResponse> register(@Valid @RequestBody CustomerRequest customerRequest) throws BusinessException {
        CustomerResponse customerResponse = this.customerService.register(customerRequest);
        return new ResponseEntity<>(customerResponse, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<CustomerResponse>> list() {
        List<CustomerResponse> result = this.customerService.list();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<CustomerResponse> update(@PathVariable("cpf") String cpf, @Valid @RequestBody CustomerRequest customerRequest)
            throws RegisterNotFoundException, BusinessException {
        CustomerResponse customerResponse = this.customerService.update(cpf, customerRequest);
        return new ResponseEntity<>(customerResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<String> delete(@PathVariable("cpf") String cpf) throws RegisterNotFoundException {
        this.customerService.delete(cpf);
        return new ResponseEntity<>("Customer successfully deleted", HttpStatus.OK);
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<CustomerResponse> find(@PathVariable("cpf") String cpf) {
        CustomerResponse customerResponse = this.customerService.find(cpf);
        return new ResponseEntity<>(customerResponse, HttpStatus.OK);
    }
}
