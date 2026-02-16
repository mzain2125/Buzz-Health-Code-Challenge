package com.BuzzRx.Drug.Management.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PriceQuoteRequest {

    @NotNull(message = "Quantity is required")
    @DecimalMin(value = "0.01", inclusive = true, message = "Quantity must be greater than 0")
    private BigDecimal quantity;

}
