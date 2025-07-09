package customerapi.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class CustomerResponse {
	//class to get the customer Response
	//variables
    private UUID id;
    private String name;
    private String email;
    private Double annualSpend;
    private LocalDate lastPurchaseDate;
    private String tier;
	public UUID getId() {
		return id;
	}
	//Getter and Setters methods
	public void setId(UUID id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Double getAnnualSpend() {
		return annualSpend;
	}
	public void setAnnualSpend(Double annualSpend) {
		this.annualSpend = annualSpend;
	}
	public LocalDate getLastPurchaseDate() {
		return lastPurchaseDate;
	}
	public void setLastPurchaseDate(LocalDate lastPurchaseDate) {
		this.lastPurchaseDate = lastPurchaseDate;
	}
	public String getTier() {
		return tier;
	}
	public void setTier(String tier) {
		this.tier = tier;
	}
}
