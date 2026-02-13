package com.BuzzRx.Drug.Management.controller;

import com.BuzzRx.Drug.Management.request.PharmacyRequest;
import com.BuzzRx.Drug.Management.response.PharmacyResponse;
import com.BuzzRx.Drug.Management.service.PharmacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/pharmacy")
public class PharmacyController {
    @Autowired
    PharmacyService pharmacyService;

    @PostMapping
    public ResponseEntity<?> savePharmacy(@RequestBody PharmacyRequest pharmacyRequest){
        PharmacyResponse pharmacyResponse=pharmacyService.savePharmacy(pharmacyRequest);
        return ResponseEntity.ok(pharmacyResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?>getPharmacyById(@PathVariable UUID id){
        PharmacyResponse pharmacyResponse=pharmacyService.getPharmacyById(id);
        return ResponseEntity.ok(pharmacyResponse);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?>getPharmacyByName(@PathVariable String name){
        List<PharmacyResponse> pharmacyResponse=pharmacyService.getPharmacyByName(name);
        return ResponseEntity.ok(pharmacyResponse);
    }

    @GetMapping("/address/{address}")
    public ResponseEntity<?>getPharmacyByAddress(@PathVariable String address){
        List<PharmacyResponse> pharmacyResponse=pharmacyService.getPharmacyByAddress(address);
        return ResponseEntity.ok(pharmacyResponse);
    }

    @GetMapping("/city/{city}")
    public ResponseEntity<?>getPharmacyByCity(@PathVariable String city){
        List<PharmacyResponse> pharmacyResponse=pharmacyService.getPharmacyByCity(city);
        return ResponseEntity.ok(pharmacyResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?>putPharmacyById(@PathVariable UUID id,@RequestBody PharmacyRequest pharmacyRequest){
        PharmacyResponse pharmacyResponse=pharmacyService.putPharmacyById(id,pharmacyRequest);
        return ResponseEntity.ok(pharmacyResponse);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?>patchPharmacyById(@PathVariable UUID id,@RequestBody PharmacyRequest pharmacyRequest){
        PharmacyResponse pharmacyResponse=pharmacyService.patchById(id,pharmacyRequest);
        return ResponseEntity.ok(pharmacyResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?>dltById(@PathVariable UUID id){
        pharmacyService.dltById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<?> dltAll(){
        pharmacyService.dltAll();
        return ResponseEntity.ok("All Pharmacies Successfully Deleted");
    }
}
