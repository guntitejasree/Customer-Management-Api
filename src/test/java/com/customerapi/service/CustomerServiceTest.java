
package com.example.customerapi.service;

import com.example.customerapi.model.Customer;
import com.example.customerapi.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class CustomerServiceTest {

    private CustomerRepository repo;
    private CustomerService service;

    @BeforeEach
    void setUp() {
        repo = mock(CustomerRepository.class);
        service = new CustomerService(repo);
    }

    @Test
    void createCustomer_persistsAndReturnsDto() {
        Customer toSave = new Customer(null, "Teja", "Teja@yahoo.com",
                                       new BigDecimal("2500"), OffsetDateTime.now());
        Customer saved  = new Customer(UUID.randomUUID(), toSave.getName(),
                                       toSave.getEmail(), toSave.getAnnualSpend(),
                                       toSave.getLastPurchaseDate());
        when(repo.save(any(Customer.class))).thenReturn(saved);

        var dto = service.createCustomer(toSave);

        ArgumentCaptor<Customer> cap = ArgumentCaptor.forClass(Customer.class);
        verify(repo).save(cap.capture());
        assertThat(cap.getValue().getId()).isNull();
        assertThat(dto.id()).isEqualTo(saved.getId());
    }

    @Test
    void getCustomer_calculatesTier() {
        Customer c = new Customer(UUID.randomUUID(), "Tej", "tej@yahoo.com",
                                  new BigDecimal("15000"), OffsetDateTime.now().minusMonths(3));
        when(repo.findById(c.getId())).thenReturn(Optional.of(c));

        var dto = service.getCustomer(c.getId());

        assertThat(dto.tier()).isEqualTo("Platinum");
    }
}
