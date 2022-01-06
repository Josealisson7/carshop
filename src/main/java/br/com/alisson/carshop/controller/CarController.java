package br.com.alisson.carshop.controller;

import br.com.alisson.carshop.request.SellRequest;
import br.com.alisson.carshop.entity.Car;
import br.com.alisson.carshop.response.CarResponse;
import br.com.alisson.carshop.response.CustomerResponse;
import br.com.alisson.carshop.response.SellResponse;
import br.com.alisson.carshop.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/car")
@RestController
public class CarController {
    @Autowired
    private CarService carService;

    @PostMapping("/sell")
    public ResponseEntity<SellResponse> sell(@Valid @RequestBody SellRequest sellRequest) throws Exception {
        SellResponse car = this.carService.sell(sellRequest);
        return new ResponseEntity<>(car, HttpStatus.CREATED);
    }

    @GetMapping("/{code}")
    public ResponseEntity<CarResponse> find(@PathVariable long code) {
        CarResponse car = this.carService.find(code);
        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<CarResponse>> list() {
        List<CarResponse> cars = this.carService.list();
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }
}
