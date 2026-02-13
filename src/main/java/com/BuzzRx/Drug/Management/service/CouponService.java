//package com.BuzzRx.Drug.Management.service;
//
//import com.BuzzRx.Drug.Management.model.Coupon;
//import com.BuzzRx.Drug.Management.model.Drug;
//import com.BuzzRx.Drug.Management.repo.CouponRepo;
//import com.BuzzRx.Drug.Management.request.CouponRequest;
//import com.BuzzRx.Drug.Management.response.CouponResponse;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class CouponService {
//    @Autowired
//    CouponRepo couponRepo;
//
//    public CouponResponse saveCoupon(CouponRequest couponRequest){
//        Coupon coupon=new Coupon();
//        BeanUtils.copyProperties(couponRequest,coupon);
//        couponRepo.save(coupon);
//        CouponResponse couponResponse=new CouponResponse();
//        BeanUtils.copyProperties(coupon,couponResponse);
//        couponResponse.setDrugName(coupon.getDrugId().getName());
//        return couponResponse;
//    }
//}




package com.BuzzRx.Drug.Management.service;

import com.BuzzRx.Drug.Management.model.Coupon;
import com.BuzzRx.Drug.Management.model.Drug;
import com.BuzzRx.Drug.Management.model.Pharmacy;
import com.BuzzRx.Drug.Management.repo.CouponRepo;
import com.BuzzRx.Drug.Management.repo.DrugRepo;
import com.BuzzRx.Drug.Management.repo.PharmacyRepo;
import com.BuzzRx.Drug.Management.request.CouponRequest;
import com.BuzzRx.Drug.Management.response.CouponResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        // Copy all simple fields except the relationships
        BeanUtils.copyProperties(couponRequest, coupon, "drugId", "pharmacyId");

        // Fetch Drug entity
        Drug drug = drugRepo.findById(couponRequest.getDrugId())
                .orElseThrow(() -> new RuntimeException("Drug not found with ID: " + couponRequest.getDrugId()));

        // Fetch Pharmacy entity
        Pharmacy pharmacy = pharmacyRepo.findById(couponRequest.getPharmacyId())
                .orElseThrow(() -> new RuntimeException("Pharmacy not found with ID: " + couponRequest.getPharmacyId()));

        // Set the relationships manually
        coupon.setDrugId(drug);
        coupon.setPharmacyId(pharmacy);

        // Save to DB
        couponRepo.save(coupon);

        // Prepare response
        CouponResponse response = new CouponResponse();
        BeanUtils.copyProperties(coupon, response);
        response.setDrugName(drug.getName());
        response.setPharmacyName(pharmacy.getName());

        return response;
    }

    public CouponResponse getCouponById(UUID id){

        Coupon coupon = couponRepo.findById(id).orElseThrow(() -> new RuntimeException("Coupon not found with id: " + id));
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
        Coupon coupon=couponRepo.findById(id).orElseThrow(()->new RuntimeException("coupon not found with id: "+id));
        CouponResponse response=new CouponResponse();
        BeanUtils.copyProperties(couponRequest, coupon, "drugId", "pharmacyId");
        Drug drug=drugRepo.findById(coupon.getDrugId().getId()).orElseThrow(()-> new RuntimeException(" not found"));
        Pharmacy pharmacy = pharmacyRepo.findById(couponRequest.getPharmacyId()).orElseThrow(() -> new RuntimeException("Pharmacy not found with ID: " + couponRequest.getPharmacyId()));
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

        Coupon coupon = couponRepo.findById(id).orElseThrow(() -> new RuntimeException("Coupon not found with id: " + id));
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
            Drug drug = drugRepo.findById(request.getDrugId()).orElseThrow(() -> new RuntimeException("Drug not found"));
            coupon.setDrugId(drug);
        }
        if (request.getPharmacyId() != null) {
            Pharmacy pharmacy = pharmacyRepo.findById(request.getPharmacyId()).orElseThrow(() -> new RuntimeException("Pharmacy not found"));
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


}

