package customerapi.service;

import customerapi.dto.CustomerRequest;
import customerapi.dto.CustomerResponse;
import customerapi.model.Customer;
import customerapi.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerService { 
	//Service class for Controller

    @Autowired
    private CustomerRepository repository;

    public CustomerResponse createCustomer(CustomerRequest request) {
        Customer customer = Customer.builder()
                .name(request.getName())
                .email(request.getEmail())
                .annualSpend(request.getAnnualSpend())
                .lastPurchaseDate(request.getLastPurchaseDate())
                .build();
        Customer saved = repository.save(customer);
        return mapToResponse(saved);
    }

    //method to get customer details by id
    public CustomerResponse getCustomerById(UUID id) {
        Customer customer = repository.findById(id).orElseThrow();
        return mapToResponse(customer);
    }

    //method to get customer details by name
    public CustomerResponse getCustomerByName(String name) {
        Customer customer = repository.findByName(name).orElseThrow();
        return mapToResponse(customer);
    }

    //method to get customer details by email
    public CustomerResponse getCustomerByEmail(String email) {
        Customer customer = repository.findByEmail(email).orElseThrow();
        return mapToResponse(customer);
    }

    @Transactional
    public CustomerResponse updateCustomer(UUID id, CustomerRequest request) {
        Customer customer = repository.findById(id).orElseThrow();
        customer.setName(request.getName());
        customer.setEmail(request.getEmail());
        customer.setAnnualSpend(request.getAnnualSpend());
        customer.setLastPurchaseDate(request.getLastPurchaseDate());
        return mapToResponse(customer);
    }

    //method to delete customer by id
    public void deleteCustomer(UUID id) {
        repository.deleteById(id);
    }

    private CustomerResponse mapToResponse(Customer customer) {
        CustomerResponse response = new CustomerResponse();
        response.setId(customer.getId());
        response.setName(customer.getName());
        response.setEmail(customer.getEmail());
        response.setAnnualSpend(customer.getAnnualSpend());
        response.setLastPurchaseDate(customer.getLastPurchaseDate());
        response.setTier(determineTier(customer));
        return response;
    }

    private String determineTier(Customer customer) {
        Double spend = Optional.ofNullable(customer.getAnnualSpend()).orElse(0.0);
        LocalDate lastPurchase = customer.getLastPurchaseDate();
        LocalDate now = LocalDate.now();

        //logic to check the tier eligibility
        if (spend >= 10000 && lastPurchase != null && Period.between(lastPurchase, now).getMonths() <= 6) {
            return "Platinum";
        } else if (spend >= 1000 && spend < 10000 && lastPurchase != null && Period.between(lastPurchase, now).getMonths() <= 12) {
            return "Gold";
        } else {
            return "Silver";
        }
    }
}
