package com.BuzzRx.Drug.Management.controller;

import com.BuzzRx.Drug.Management.repo.DrugRepo;
import com.BuzzRx.Drug.Management.request.DrugRequest;
import com.BuzzRx.Drug.Management.response.DrugResponse;
import com.BuzzRx.Drug.Management.service.DrugService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/drug")
public class DrugController {
    private static final Logger log= LoggerFactory.getLogger(DrugController.class);

    @Autowired
    private DrugService drugService;
    @Autowired
    private DrugRepo drugRepo;

    @PostMapping("/drug")
    public ResponseEntity<?> saveDrug(@Valid @RequestBody DrugRequest drugRequest){
//        if(drugRepo.existByNdc(drugRequest.getNdc())){
//           return ResponseEntity.badRequest().body("Drug ndc must be unique");
//        }
        log.info("Drug is created with ths Name={}",drugRequest.getName());
        DrugResponse drugResponse=drugService.saveDrug(drugRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(drugResponse);

    }

}
