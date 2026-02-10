package com.BuzzRx.Drug.Management.repo;

import com.BuzzRx.Drug.Management.model.Drug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DrugRepo extends JpaRepository<Drug, UUID> {
//    boolean existByNdc(String ndc);

}
