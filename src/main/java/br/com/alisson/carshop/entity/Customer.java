package br.com.alisson.carshop.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Customer {
    @Id
    @GeneratedValue
    private Long id;
    @Column(length = 50, nullable = false)
    private String name;
    @Column(length = 11, nullable = false, unique = true)
    private String cpf;
    private LocalDate birthDate;
    @ManyToMany
    @JoinTable(name = "VENDA",
            joinColumns = {@JoinColumn(name = "customer_id",
                    referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "car_id",
                    referencedColumnName = "id")})
    private List<Car> cars = new ArrayList<>();

    public Customer(String name, String cpf, LocalDate birthDate) {
        this.name = name;
        this.cpf = cpf;
        this.birthDate = birthDate;
    }

    public Customer() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public List<Car> getCars() {
        return this.cars;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(name, customer.name) && Objects.equals(cpf, customer.cpf) && Objects.equals(birthDate, customer.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cpf, birthDate);
    }
}
