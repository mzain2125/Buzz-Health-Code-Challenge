package com.BuzzRx.Drug.Management.request;

import com.BuzzRx.Drug.Management.enums.DiscountType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CouponRequest {
    @NotNull(message = "Drug ID is required")
    private UUID drugId;

    @NotNull(message = "Pharmacy ID is required")
    private UUID pharmacyId;

    @NotBlank(message = "BIN is required")
    private String bin;

    @NotBlank(message = "PCN is required")
    private String pcn;

    @NotBlank(message = "Member ID is required")
    private String memberId;

    @NotBlank(message = "Group ID is required")
    private String groupId;

    @NotNull(message = "Cash price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Cash price must be greater than 0")
    private BigDecimal cashPrice;

    @NotNull(message = "Discount type is required")
    private DiscountType discountType;

    @NotNull(message = "Discount value is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Discount value must be greater than 0")
    private BigDecimal discountValue;

    @NotNull(message = "Minimum price is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Minimum price cannot be negative")
    private BigDecimal minPrice;

    @NotNull(message = "Start date is required")
    private LocalDate startDate;

    @NotNull(message = "End date is required")
    private LocalDate endDate;

    @NotNull(message = "Active status is required")
    private Boolean isActive;
}
