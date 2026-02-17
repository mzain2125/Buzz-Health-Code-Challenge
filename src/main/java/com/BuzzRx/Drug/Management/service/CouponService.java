package com.BuzzRx.Drug.Management.service;

import com.BuzzRx.Drug.Management.enums.DiscountType;
import com.BuzzRx.Drug.Management.exceptions.InactiveCouponException;
import com.BuzzRx.Drug.Management.exceptions.ResourceNotFoundException;
import com.BuzzRx.Drug.Management.model.Coupon;
import com.BuzzRx.Drug.Management.model.Drug;
import com.BuzzRx.Drug.Management.model.Pharmacy;
import com.BuzzRx.Drug.Management.repo.CouponRepo;
import com.BuzzRx.Drug.Management.repo.DrugRepo;
import com.BuzzRx.Drug.Management.repo.PharmacyRepo;
import com.BuzzRx.Drug.Management.request.CouponRequest;
import com.BuzzRx.Drug.Management.response.CouponResponse;
import com.BuzzRx.Drug.Management.response.PriceQuoteResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;

@Service
public class CouponService {

    @Autowired
    private CouponRepo couponRepo;

    @Autowired
    private DrugRepo drugRepo;

    @Autowired
    private PharmacyRepo pharmacyRepo;

    public CouponResponse saveCoupon(CouponRequest couponRequest) {

        Coupon coupon = new Coupon();
        BeanUtils.copyProperties(couponRequest, coupon, "drugId", "pharmacyId");
        Drug drug = drugRepo.findById(couponRequest.getDrugId())
                .orElseThrow(() -> new ResourceNotFoundException("Drug not found with ID: " + couponRequest.getDrugId()));
        Pharmacy pharmacy = pharmacyRepo.findById(couponRequest.getPharmacyId())
                .orElseThrow(() -> new ResourceNotFoundException("Pharmacy not found with ID: " + couponRequest.getPharmacyId()));
        coupon.setDrugId(drug);
        coupon.setPharmacyId(pharmacy);
        couponRepo.save(coupon);
        CouponResponse response = new CouponResponse();
        BeanUtils.copyProperties(coupon, response);
        response.setDrugName(drug.getName());
        response.setPharmacyName(pharmacy.getName());
        return response;
    }

    public CouponResponse getCouponById(UUID id){
        Coupon coupon = couponRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Coupon not found with id: " + id));
        CouponResponse couponResponse = new CouponResponse();
        BeanUtils.copyProperties(coupon, couponResponse);
        if (coupon.getDrugId() != null) {
            couponResponse.setDrugId(coupon.getDrugId().getId());
            couponResponse.setDrugName(coupon.getDrugId().getName());
        }
        if (coupon.getPharmacyId() != null) {
            couponResponse.setPharmacyId(coupon.getPharmacyId().getId());
            couponResponse.setPharmacyName(coupon.getPharmacyId().getName());
        }
        return couponResponse;
    }

    public CouponResponse putCouponById(UUID id,CouponRequest couponRequest){
        Coupon coupon=couponRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("coupon not found with id: "+id));
        CouponResponse response=new CouponResponse();
        BeanUtils.copyProperties(couponRequest, coupon, "drugId", "pharmacyId");
        Drug drug=drugRepo.findById(coupon.getDrugId().getId()).orElseThrow(()-> new ResourceNotFoundException(" not found"));
        Pharmacy pharmacy = pharmacyRepo.findById(couponRequest.getPharmacyId()).orElseThrow(() -> new ResourceNotFoundException("Pharmacy not found with ID: " + couponRequest.getPharmacyId()));
        coupon.setDrugId(drug);
        coupon.setPharmacyId(pharmacy);
        couponRepo.save(coupon);
        BeanUtils.copyProperties(coupon,response);
        response.setDrugId(drug.getId());
        response.setDrugName(drug.getName());
        response.setPharmacyId(pharmacy.getId());
        response.setPharmacyName(pharmacy.getName());
        return response;
    }

    public CouponResponse patchCouponById(UUID id, CouponRequest request) {

        Coupon coupon = couponRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Coupon not found with id: " + id));
        if (request.getBin() != null)
            coupon.setBin(request.getBin());
        if (request.getPcn() != null)
            coupon.setPcn(request.getPcn());
        if (request.getMemberId() != null)
            coupon.setMemberId(request.getMemberId());
        if (request.getGroupId() != null)
            coupon.setGroupId(request.getGroupId());
        if (request.getCashPrice() != null)
            coupon.setCashPrice(request.getCashPrice());
        if (request.getDiscountType() != null)
            coupon.setDiscountType(request.getDiscountType());
        if (request.getDiscountValue() != null)
            coupon.setDiscountValue(request.getDiscountValue());
        if (request.getMinPrice() != null)
            coupon.setMinPrice(request.getMinPrice());
        if (request.getStartDate() != null)
            coupon.setStartDate(request.getStartDate());
        if (request.getEndDate() != null)
            coupon.setEndDate(request.getEndDate());
        if (request.getIsActive() != null)
            coupon.setIsActive(request.getIsActive());
        if (request.getDrugId() != null) {
            Drug drug = drugRepo.findById(request.getDrugId()).orElseThrow(() -> new ResourceNotFoundException("Drug not found"));
            coupon.setDrugId(drug);
        }
        if (request.getPharmacyId() != null) {
            Pharmacy pharmacy = pharmacyRepo.findById(request.getPharmacyId()).orElseThrow(() -> new ResourceNotFoundException("Pharmacy not found"));
            coupon.setPharmacyId(pharmacy);
        }
        Coupon updated = couponRepo.save(coupon);
        CouponResponse response = new CouponResponse();
        BeanUtils.copyProperties(updated, response);
        if (updated.getDrugId() != null) {
            response.setDrugId(updated.getDrugId().getId());
            response.setDrugName(updated.getDrugId().getName());
        }
        if (updated.getPharmacyId() != null) {
            response.setPharmacyId(updated.getPharmacyId().getId());
            response.setPharmacyName(updated.getPharmacyId().getName());
        }
        return response;
    }

    public void dltCouponById(UUID id){
        Coupon coupon=couponRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Coupon not found with this id: "+id));
        couponRepo.deleteById(id);
    }

    public PriceQuoteResponse getPriceOnDiscount(UUID id, BigDecimal quantity){
        CouponResponse couponResponse =new CouponResponse();
        Coupon coupon=couponRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Coupon Not found with this id: "+id));
        if (!coupon.getIsActive())
            throw new InactiveCouponException("Coupon is not active");
        if(coupon.getDiscountType()== DiscountType.FLAT)
        {

            BigDecimal priceBefore=coupon.getCashPrice().multiply(quantity);
            BigDecimal priceAfterDiscount=coupon.getCashPrice().subtract(coupon.getDiscountValue());
            BigDecimal priceWithMinPrice=priceAfterDiscount.max(coupon.getMinPrice());
            BigDecimal priceAfter=priceWithMinPrice.multiply(quantity);
            return new PriceQuoteResponse(priceBefore, priceAfter, couponResponse);
        }
        BigDecimal priceBefore=coupon.getCashPrice().multiply(quantity);

        BigDecimal calculatePercentage=BigDecimal.ONE.subtract(coupon.getDiscountValue().divide(BigDecimal.valueOf(100),4, RoundingMode.HALF_UP));
        BigDecimal calculatingPrice=coupon.getCashPrice().multiply(calculatePercentage).max(coupon.getMinPrice());
        BigDecimal priceAfter=calculatingPrice.multiply(quantity);
        return new PriceQuoteResponse(priceBefore,priceAfter,couponResponse);

    }


}

