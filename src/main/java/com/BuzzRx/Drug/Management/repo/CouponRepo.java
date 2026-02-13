package com.BuzzRx.Drug.Management.repo;

import com.BuzzRx.Drug.Management.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CouponRepo  extends JpaRepository<Coupon, UUID> {

}
