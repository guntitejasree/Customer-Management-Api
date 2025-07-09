
package com.example.customerapi.service;

import com.example.customerapi.model.Customer;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.assertThat;

class TierCalculatorParameterizedTest {

    private final TierCalculator calc = new TierCalculator();

    @ParameterizedTest(name = "{index} ⇒ spend={0}, monthsAgo={1} → {2}")
    @CsvSource({
        "500,   15, Silver",
        "2500,  11, Gold",
        "2500,  13, Silver",
        "12000, 4,  Platinum",
        "12000, 7,  Silver"
    })
    void tierIsCalculatedCorrectly(BigDecimal spend, int monthsAgo, String expectedTier) {
        Customer c = new Customer(null, "X", "x@x.com", spend,
                                  OffsetDateTime.now().minus(monthsAgo, ChronoUnit.MONTHS));
        assertThat(calc.calculateTier(c)).isEqualTo(expectedTier);
    }
}
