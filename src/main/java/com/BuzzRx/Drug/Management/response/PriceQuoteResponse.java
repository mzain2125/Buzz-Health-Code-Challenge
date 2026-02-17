package com.BuzzRx.Drug.Management.response;

import com.BuzzRx.Drug.Management.model.Coupon;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PriceQuoteResponse {
    private BigDecimal priceBefore;
    private BigDecimal priceAfter;
    private CouponResponse coupon;

}
