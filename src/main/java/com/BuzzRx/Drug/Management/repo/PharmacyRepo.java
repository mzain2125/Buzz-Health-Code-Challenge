package com.BuzzRx.Drug.Management.repo;

import com.BuzzRx.Drug.Management.model.Drug;
import com.BuzzRx.Drug.Management.model.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PharmacyRepo extends JpaRepository<Pharmacy, UUID> {
    boolean existsByNpi(String npi);
    public List<Pharmacy> findByName(String name);
    public List<Pharmacy> findByAddress(String address);
    public List<Pharmacy> findByCity(String city);
}
