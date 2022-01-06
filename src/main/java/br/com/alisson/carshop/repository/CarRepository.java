package br.com.alisson.carshop.repository;

import br.com.alisson.carshop.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    Car findByCode(Long code);
    Boolean existsByCode(Long code);
}
