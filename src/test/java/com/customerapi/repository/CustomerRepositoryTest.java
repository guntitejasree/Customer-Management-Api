
package com.example.customerapi.repository;

import com.example.customerapi.model.Customer;

import customerapi.repository.CustomerRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository repository;

    @Test
    void crudOperations_workAsExpected() {
        Customer c = new Customer(null, "Teja", "teja@yahoo.com",
                                  new BigDecimal("500"), OffsetDateTime.now());
        Customer saved = repository.save(c);
        assertThat(saved.getId()).isNotNull();

        Customer found = repository.findById(saved.getId()).orElseThrow();
        assertThat(found.getName()).isEqualTo("Teja");

        found.setName("Teja Sree");
        repository.save(found);
        assertThat(repository.findById(found.getId()).orElseThrow().getName()).isEqualTo("Teja Sree");

        repository.deleteById(found.getId());
        assertThat(repository.existsById(found.getId())).isFalse();
    }
}
