package customerapi.controller;

import customerapi.dto.CustomerRequest;
import customerapi.dto.CustomerResponse;
import customerapi.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(@Valid @RequestBody CustomerRequest request) {
        return ResponseEntity.status(201).body(service.createCustomer(request));
    }

    @GetMapping("/{id}") //GetMapping for access by id
    public ResponseEntity<CustomerResponse> getById(@PathVariable UUID id) {
    	System.out.println("abcd= "+id);
        return ResponseEntity.ok(service.getCustomerById(id));
    }

    @GetMapping("/name") //GetMapping for access by name
    public ResponseEntity<CustomerResponse> getByName(@RequestParam String name) {
    	System.out.println("name provided is = "+name);
        return ResponseEntity.ok(service.getCustomerByName(name));
    }

    @GetMapping("/email") //GetMapping for access by email
    public ResponseEntity<CustomerResponse> getByEmail(@RequestParam String email) {
    	System.out.println("eamil provided is = "+email);
        return ResponseEntity.ok(service.getCustomerByEmail(email));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponse> update(@PathVariable UUID id, @Valid @RequestBody CustomerRequest request) {
        return ResponseEntity.ok(service.updateCustomer(id, request));
    }

    @DeleteMapping("/{id}") //Delete for customer by Id
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}
