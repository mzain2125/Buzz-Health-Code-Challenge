package com.BuzzRx.Drug.Management.controller;

import com.BuzzRx.Drug.Management.request.CouponRequest;
import com.BuzzRx.Drug.Management.request.PriceQuoteRequest;
import com.BuzzRx.Drug.Management.response.CouponResponse;
import com.BuzzRx.Drug.Management.response.PriceQuoteResponse;
import com.BuzzRx.Drug.Management.service.CouponService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/api/coupon")
public class CouponController {
    @Autowired
    CouponService couponService;

    @PostMapping
    public ResponseEntity<?> saveCoupon(@RequestBody CouponRequest couponRequest){
       CouponResponse response= couponService.saveCoupon(couponRequest);
       return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCouponById(@PathVariable UUID id){
        CouponResponse response=couponService.getCouponById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCoupon(@PathVariable UUID id,@RequestBody CouponRequest couponRequest){
        CouponResponse response = couponService.putCouponById(id, couponRequest);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CouponResponse> patchCoupon(@PathVariable UUID id,@RequestBody CouponRequest request) {
        CouponResponse couponResponse=couponService.patchCouponById(id, request);
        return ResponseEntity.ok(couponResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> dltCouponById(@PathVariable UUID id){
        couponService.dltCouponById(id);
        return ResponseEntity.ok("Successfully Deleted");
    }


    @PostMapping("/{id}/quantity")
    public ResponseEntity<PriceQuoteResponse> generatePriceQuote(@PathVariable UUID id, @Valid @RequestBody PriceQuoteRequest request) {
        PriceQuoteResponse response =couponService.getPriceOnDiscount(id, request.getQuantity());

        return ResponseEntity.ok(response);
    }

}
