package br.com.alisson.carshop.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class SellRequest {

    @NotBlank(message = "customerCpf is required")
    @Size(min = 11, max = 11, message = "customerCpf should have at least 11 characters")
    @Pattern(regexp = "^[0-9]+$")
    private String customerCpf;

    @NotNull(message = "carCode required")
    private Long carCode;

    public String getCustomerCpf() {
        return this.customerCpf;
    }

    public void setCustomerCpf(String customerCpf) {
        this.customerCpf = customerCpf;
    }

    public Long getCarCode() {
        return this.carCode;
    }

    public void setCarCode(Long carCode) {
        this.carCode = carCode;
    }
}
