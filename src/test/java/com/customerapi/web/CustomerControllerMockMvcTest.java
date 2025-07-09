
package com.example.customerapi.web;

import com.example.customerapi.model.CustomerRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CustomerControllerMockMvcTest {

    @Autowired private MockMvc mvc;
    @Autowired private ObjectMapper json;

    @Test
    void createReadUpdateDelete_happyPath() throws Exception {
        var req = new CustomerRequest("Teja", "teja@yahoo.com",
                                      new BigDecimal("4500"), OffsetDateTime.now());
        String location =
            mvc.perform(post("/customers")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json.writeValueAsString(req)))
               .andExpect(status().isCreated())
               .andReturn().getResponse().getHeader("Location");

        mvc.perform(get(location))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.name").value("Mary"))
           .andExpect(jsonPath("$.tier").value("Gold"));

        req = new CustomerRequest("Mary J.", "tej.j@yahoo.com",
                                  new BigDecimal("800"), OffsetDateTime.now());
        mvc.perform(put(location)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json.writeValueAsString(req)))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.tier").value("Silver"));

        mvc.perform(delete(location))
           .andExpect(status().isNoContent());

        mvc.perform(get(location)).andExpect(status().isNotFound());
    }

    @Test
    void createCustomer_withBadEmail_returns400() throws Exception {
        var bad = new CustomerRequest("Bad", "invalid-email",
                                      new BigDecimal("100"), OffsetDateTime.now());
        mvc.perform(post("/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json.writeValueAsString(bad)))
           .andExpect(status().isBadRequest());
    }
}
