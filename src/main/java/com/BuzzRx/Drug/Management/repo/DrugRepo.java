package com.BuzzRx.Drug.Management.repo;

import com.BuzzRx.Drug.Management.model.Drug;
import com.BuzzRx.Drug.Management.response.DrugResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DrugRepo extends JpaRepository<Drug, UUID> {

    boolean existsByNdc(String ndc);

    public List<Drug> findByNameContainingIgnoreCase(String name);
    public Drug findByNdc(String ndc);

}
