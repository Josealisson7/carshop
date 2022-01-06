package br.com.alisson.carshop.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class CustomerRequest {
    @NotBlank(message = "name is required")
    @Size(min = 3, message = "the name must have at least 3 characters")
    private String name;

    @NotBlank(message = "cpf is required")
    @Size(min = 11, max = 11, message = "cpf should have at least 11 characters")
    @Pattern(regexp = "^[0-9]+$", message = "erro")
    private String cpf;

    private LocalDate birthDate;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
