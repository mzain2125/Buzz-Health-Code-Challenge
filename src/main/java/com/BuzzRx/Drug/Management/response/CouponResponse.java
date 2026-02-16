package com.BuzzRx.Drug.Management.response;

import com.BuzzRx.Drug.Management.enums.DiscountType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CouponResponse {

    private UUID id;
    private UUID drugId;
    private String drugName;
    private UUID pharmacyId;
    private String pharmacyName;
    private String bin;
    private String pcn;
    private String memberId;
    private String groupId;
    private BigDecimal cashPrice;
    private DiscountType discountType;
    private BigDecimal discountValue;
    private BigDecimal minPrice;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean isActive;
    private LocalDateTime createdAt;

}
