package com.BuzzRx.Drug.Management.interfaces;

import com.BuzzRx.Drug.Management.request.PharmacyRequest;
import com.BuzzRx.Drug.Management.response.PharmacyResponse;

import java.util.List;
import java.util.UUID;

public interface PharmacyInterface {
    PharmacyResponse savePharmacy(PharmacyRequest pharmacyRequest);
    PharmacyResponse getPharmacyById(UUID id);
    List<PharmacyResponse> getPharmacyByName(String name);
    List<PharmacyResponse> getPharmacyByAddress(String address);
    List<PharmacyResponse> getPharmacyByCity(String city);
    PharmacyResponse putPharmacyById(UUID id, PharmacyRequest pharmacyRequest);
    PharmacyResponse patchById(UUID id,PharmacyRequest pharmacyRequest);

}
