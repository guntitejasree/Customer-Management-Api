package customerapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CustomerRequest {
	//class to get the customer request
    @NotBlank
    private String names;

    @NotBlank
    @Email
    private String email;

    private Double annualSpend;

    private LocalDate lastPurchaseDate;
}
