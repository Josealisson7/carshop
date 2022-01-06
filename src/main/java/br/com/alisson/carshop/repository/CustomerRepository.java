package br.com.alisson.carshop.repository;

import br.com.alisson.carshop.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByCpf(String cpf);

    Boolean existsByCpf(String cpf);

    @Transactional
    Integer deleteByCpf(String cpf);
}
